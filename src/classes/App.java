package classes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        ExecutionService service = new ExecutionService();

        service.getCatalogManager().registerTable(
                "Orders",
                new SchemaInfo(
                        "Orders",
                        List.of(
                                new ColumnInfo("id", "INT", false),
                                new ColumnInfo("branch_id", "VARCHAR", false),
                                new ColumnInfo("amount", "DOUBLE", false)
                        ),
                        List.of(new ConstraintInfo("PRIMARY_KEY", "id"))
                )
        );

        service.getSessionMgr().createSession("u1", "127.0.0.1", "dev1");
        service.getRoleService().createRole("admin", List.of(new Permission("p1", "select_orders", "Orders", "SELECT", "allow select")));
        service.getRoleService().assignRole("u1", "admin");

        ResultOutput result = service.execute("SELECT * FROM Orders", service.getSessionMgr().latestAuthToken());
        System.out.println(result.payload);
    }
}

enum ASTNodeType {
    SELECT,
    INSERT,
    IDENTIFIER,
    LITERAL,
    BINARY,
    TABLE_REFERENCE
}

interface ASTNode {
    ASTNodeType getType();

    <T> T accept(ASTVisitor<T> visitor);

    String toString();
}

interface ExpressionNode extends ASTNode {
}

interface ASTVisitor<T> {
    T visitSelectStatement(SelectStatementNode node);

    T visitInsertStatement(InsertStatementNode node);

    T visitBinaryExpression(BinaryExpressionNode node);

    T visitIdentifier(IdentifierNode node);

    T visitLiteral(LiteralNode node);

    T visitTableReference(TableReferenceNode node);
}

abstract class AbstractASTNode implements ASTNode {
    @Override
    public String toString() {
        return getType().name();
    }
}

class ASTBuildResult {
    final boolean success;
    final ASTNode root;
    final String errorMessage;

    ASTBuildResult(boolean success, ASTNode root, String errorMessage) {
        this.success = success;
        this.root = root;
        this.errorMessage = errorMessage;
    }
}

class SelectStatementNode extends AbstractASTNode {
    TableReferenceNode fromClause;
    ExpressionNode whereClause;
    List<ASTNode> projectionList = new ArrayList<>();
    int limit = -1;
    int offset = 0;

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.SELECT;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitSelectStatement(this);
    }
}

class InsertStatementNode extends AbstractASTNode {
    TableReferenceNode targetTable;
    List<LiteralNode> values = new ArrayList<>();

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.INSERT;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitInsertStatement(this);
    }
}

class BinaryExpressionNode extends AbstractASTNode implements ExpressionNode {
    ExpressionNode left;
    String operator;
    ExpressionNode right;

    BinaryExpressionNode() {
    }

    BinaryExpressionNode(ExpressionNode left, String operator, ExpressionNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.BINARY;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitBinaryExpression(this);
    }
}

class IdentifierNode extends AbstractASTNode implements ExpressionNode {
    String name;

    IdentifierNode(String name) {
        this.name = name;
    }

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.IDENTIFIER;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitIdentifier(this);
    }
}

class LiteralNode extends AbstractASTNode implements ExpressionNode {
    Object value;
    String rawType;

    LiteralNode(Object value, String rawType) {
        this.value = value;
        this.rawType = rawType;
    }

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.LITERAL;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitLiteral(this);
    }
}

class TableReferenceNode extends AbstractASTNode {
    String tableName;
    String alias;

    TableReferenceNode(String tableName, String alias) {
        this.tableName = tableName;
        this.alias = alias;
    }

    @Override
    public ASTNodeType getType() {
        return ASTNodeType.TABLE_REFERENCE;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitTableReference(this);
    }
}

class PolicyRewriterVisitor implements ASTVisitor<ASTNode> {
    private final ExpressionNode policyCondition;

    PolicyRewriterVisitor(ExpressionNode policyCondition) {
        this.policyCondition = policyCondition;
    }

    @Override
    public ASTNode visitSelectStatement(SelectStatementNode node) {
        if (node.whereClause == null) {
            node.whereClause = policyCondition;
        } else {
            node.whereClause = new BinaryExpressionNode(node.whereClause, "AND", policyCondition);
        }
        return node;
    }

    @Override
    public ASTNode visitInsertStatement(InsertStatementNode node) {
        return node;
    }

    @Override
    public ASTNode visitBinaryExpression(BinaryExpressionNode node) {
        return node;
    }

    @Override
    public ASTNode visitIdentifier(IdentifierNode node) {
        return node;
    }

    @Override
    public ASTNode visitLiteral(LiteralNode node) {
        return node;
    }

    @Override
    public ASTNode visitTableReference(TableReferenceNode node) {
        return node;
    }
}

class SyntaxErrorException extends RuntimeException {
    SyntaxErrorException(String message) {
        super(message);
    }
}

class SecurityException extends RuntimeException {
    SecurityException(String message) {
        super(message);
    }
}

class InvalidASTException extends RuntimeException {
    InvalidASTException(String message) {
        super(message);
    }
}

class SyntaxErrorHandler {
    SyntaxErrorException handleError(Object errorTokens, String rawSql) {
        return new SyntaxErrorException(formatErrorMessage(1, 1) + ": " + rawSql);
    }

    String formatErrorMessage(int line, int col) {
        return "Syntax error at line " + line + ", col " + col;
    }
}

class ParserService {
    private final SyntaxErrorHandler errorHandler = new SyntaxErrorHandler();

    ASTBuildResult parserSQL(String sql) {
        try {
            String normalized = sql.trim();
            if (normalized.toUpperCase().startsWith("SELECT")) {
                return new ASTBuildResult(true, parseSelect(normalized), null);
            }
            if (normalized.toUpperCase().startsWith("INSERT")) {
                return new ASTBuildResult(true, parseInsert(normalized), null);
            }
            throw errorHandler.handleError(null, sql);
        } catch (SyntaxErrorException ex) {
            return new ASTBuildResult(false, null, ex.getMessage());
        } catch (RuntimeException ex) {
            return new ASTBuildResult(false, null, ex.getMessage());
        }
    }

    private SelectStatementNode parseSelect(String sql) {
        String upper = sql.toUpperCase();
        int fromIndex = upper.indexOf(" FROM ");
        if (fromIndex < 0) {
            throw errorHandler.handleError(null, sql);
        }
        String afterFrom = sql.substring(fromIndex + 6).trim();
        String tableName = afterFrom.split("\\s+")[0].replace(";", "");
        SelectStatementNode node = new SelectStatementNode();
        node.fromClause = new TableReferenceNode(tableName, null);
        node.projectionList.add(new IdentifierNode("*"));
        return node;
    }

    private InsertStatementNode parseInsert(String sql) {
        String upper = sql.toUpperCase();
        int intoIndex = upper.indexOf("INTO ");
        int valuesIndex = upper.indexOf(" VALUES ");
        if (intoIndex < 0 || valuesIndex < 0) {
            throw errorHandler.handleError(null, sql);
        }
        String tableName = sql.substring(intoIndex + 5, valuesIndex).trim();
        String valuesPart = sql.substring(valuesIndex + 8).trim();
        valuesPart = valuesPart.replaceFirst("^\\(", "").replaceFirst("\\);?$", "");

        InsertStatementNode node = new InsertStatementNode();
        node.targetTable = new TableReferenceNode(tableName, null);
        for (String value : valuesPart.split(",")) {
            node.values.add(parseLiteral(value.trim()));
        }
        return node;
    }

    private LiteralNode parseLiteral(String token) {
        if (token.startsWith("'") && token.endsWith("'")) {
            return new LiteralNode(token.substring(1, token.length() - 1), "STRING");
        }
        if (token.matches("-?\\d+")) {
            return new LiteralNode(Integer.parseInt(token), "INT");
        }
        if (token.matches("-?\\d+\\.\\d+")) {
            return new LiteralNode(Double.parseDouble(token), "DOUBLE");
        }
        return new LiteralNode(token, "IDENTIFIER");
    }
}

class ColumnInfo {
    final String name;
    final String dataType;
    final Boolean nullable;

    ColumnInfo(String name, String dataType, Boolean nullable) {
        this.name = name;
        this.dataType = dataType;
        this.nullable = nullable;
    }
}

class ConstraintInfo {
    final String constraintType;
    final String expression;

    ConstraintInfo(String constraintType, String expression) {
        this.constraintType = constraintType;
        this.expression = expression;
    }
}

class SchemaInfo {
    final String tableName;
    final List<ColumnInfo> columns;
    final List<ConstraintInfo> constraints;

    SchemaInfo(String tableName, List<ColumnInfo> columns, List<ConstraintInfo> constraints) {
        this.tableName = tableName;
        this.columns = new ArrayList<>(columns);
        this.constraints = new ArrayList<>(constraints);
    }

    ColumnInfo findColumn(String name) {
        for (ColumnInfo column : columns) {
            if (column.name.equalsIgnoreCase(name)) {
                return column;
            }
        }
        return null;
    }
}

class TableStatistics {
    int rowCount;
    int pageCount;
    Map<String, String> indexes = new HashMap<>();
}

class PhysicalPlanTree {
    PhysicalOperatorNode root;

    PhysicalPlanTree(PhysicalOperatorNode root) {
        this.root = root;
    }
}

abstract class LogicalOperator {
    final List<LogicalOperator> children = new ArrayList<>();
}

class LogicalGet extends LogicalOperator {
    final String tableName;

    LogicalGet(String tableName) {
        this.tableName = tableName;
    }
}

class LogicalFilter extends LogicalOperator {
    final ExpressionNode predicate;

    LogicalFilter(ExpressionNode predicate) {
        this.predicate = predicate;
    }
}

class LogicalInsert extends LogicalOperator {
    final String tableName;
    final List<Tuple> rows;

    LogicalInsert(String tableName, List<Tuple> rows) {
        this.tableName = tableName;
        this.rows = rows;
    }
}

abstract class PhysicalOperatorNode {
    final List<PhysicalOperatorNode> children = new ArrayList<>();
    double estimatedCost;
}

class PhysicalSeqScan extends PhysicalOperatorNode {
    final String tableId;

    PhysicalSeqScan(String tableId) {
        this.tableId = tableId;
    }
}

class PhysicalIndexScan extends PhysicalOperatorNode {
    final String indexId;
    final Object scanKey;

    PhysicalIndexScan(String indexId, Object scanKey) {
        this.indexId = indexId;
        this.scanKey = scanKey;
    }
}

class PhysicalFilter extends PhysicalOperatorNode {
    final ExpressionNode predicate;

    PhysicalFilter(ExpressionNode predicate) {
        this.predicate = predicate;
    }
}

class PhysicalInsert extends PhysicalOperatorNode {
    final String tableId;
    final List<Tuple> rows;

    PhysicalInsert(String tableId, List<Tuple> rows) {
        this.tableId = tableId;
        this.rows = rows;
    }
}

class CostModel {
    double calculateCost(PhysicalOperatorNode node, TableStatistics stats) {
        return Math.max(1.0, stats.rowCount + stats.pageCount);
    }
}

class CardinalityEstimator {
    double estimateSelectivity(ExpressionNode predicate, TableStatistics stats) {
        return 0.5d;
    }
}

class QueryOptimizer {
    private final CatalogManager catalog;
    private final CostModel costModel;
    private final CardinalityEstimator cardinalityEstimator;

    QueryOptimizer(CatalogManager catalog, CostModel costModel, CardinalityEstimator cardinalityEstimator) {
        this.catalog = catalog;
        this.costModel = costModel;
        this.cardinalityEstimator = cardinalityEstimator;
    }

    LogicalOperator generateLogicalPlan(ASTNode ast) {
        if (ast instanceof SelectStatementNode select) {
            LogicalOperator root = new LogicalGet(select.fromClause.tableName);
            if (select.whereClause != null) {
                LogicalFilter filter = new LogicalFilter(select.whereClause);
                filter.children.add(root);
                root = filter;
            }
            return root;
        }
        if (ast instanceof InsertStatementNode insert) {
            List<Tuple> rows = new ArrayList<>();
            Tuple tuple = new Tuple();
            for (int i = 0; i < insert.values.size(); i++) {
                tuple.values.put("c" + i, insert.values.get(i).value);
            }
            rows.add(tuple);
            return new LogicalInsert(insert.targetTable.tableName, rows);
        }
        throw new InvalidASTException("Unsupported AST root: " + ast.getType());
    }

    LogicalOperator optimizeLogicalPlan(LogicalOperator plan) {
        return plan;
    }

    PhysicalPlanTree optimizePhysicalPlan(LogicalOperator plan) {
        if (plan instanceof LogicalInsert insert) {
            return new PhysicalPlanTree(new PhysicalInsert(insert.tableName, insert.rows));
        }
        if (plan instanceof LogicalFilter filter && !filter.children.isEmpty() && filter.children.get(0) instanceof LogicalGet get) {
            PhysicalFilter physicalFilter = new PhysicalFilter(filter.predicate);
            physicalFilter.children.add(new PhysicalSeqScan(get.tableName));
            return new PhysicalPlanTree(physicalFilter);
        }
        if (plan instanceof LogicalGet get) {
            return new PhysicalPlanTree(new PhysicalSeqScan(get.tableName));
        }
        throw new InvalidASTException("Cannot optimize logical plan");
    }

    LogicalOperator applyPredicatePushdown(LogicalOperator plan) {
        return plan;
    }

    LogicalOperator applyProjectionPruning(LogicalOperator plan) {
        return plan;
    }
}

class PlanCacheManager {
    private final Map<String, PhysicalPlanTree> cacheMap = new HashMap<>();

    void put(String sqlHash, PhysicalPlanTree plan) {
        cacheMap.put(sqlHash, plan);
    }

    PhysicalPlanTree get(String sqlHash) {
        return cacheMap.get(sqlHash);
    }
}

interface ExecutionOperator {
    void init();

    Tuple next();

    void close();
}

class Tuple {
    final Map<String, Object> values = new LinkedHashMap<>();

    Tuple copy() {
        Tuple tuple = new Tuple();
        tuple.values.putAll(values);
        return tuple;
    }
}

class BufferPoolManager {
    private final Map<String, Page> pages = new HashMap<>();
    private final Map<String, List<Tuple>> tables = new HashMap<>();

    Page fetchPage(String pageId) {
        return pages.computeIfAbsent(pageId, Page::new);
    }

    void pinPage(String pageId) {
        fetchPage(pageId);
    }

    void unpinPage(String pageId, boolean isDirty) {
        Page page = fetchPage(pageId);
        page.isDirty = page.isDirty || isDirty;
    }

    List<Tuple> getTableRows(String tableId) {
        return tables.computeIfAbsent(tableId, key -> new ArrayList<>());
    }

    void insertRow(String tableId, Tuple tuple) {
        getTableRows(tableId).add(tuple.copy());
        Page page = fetchPage(tableId + "_page_0");
        page.slots.add(tuple.copy());
        page.isDirty = true;
    }
}

class Page {
    final String pageId;
    boolean isDirty;
    final List<Tuple> slots = new ArrayList<>();

    Page(String pageId) {
        this.pageId = pageId;
    }
}

class SequentialScanOperator implements ExecutionOperator {
    private final String tableId;
    private final BufferPoolManager bufferPoolManager;
    private int currentSlotId;

    SequentialScanOperator(String tableId, BufferPoolManager bufferPoolManager) {
        this.tableId = tableId;
        this.bufferPoolManager = bufferPoolManager;
    }

    @Override
    public void init() {
        currentSlotId = 0;
        bufferPoolManager.pinPage(tableId + "_page_0");
    }

    @Override
    public Tuple next() {
        List<Tuple> rows = bufferPoolManager.getTableRows(tableId);
        if (currentSlotId >= rows.size()) {
            return null;
        }
        return rows.get(currentSlotId++).copy();
    }

    @Override
    public void close() {
        bufferPoolManager.unpinPage(tableId + "_page_0", false);
    }
}

class IndexScanOperator implements ExecutionOperator {
    private final String indexId;
    private final Object searchKey;
    private final BufferPoolManager bufferPoolManager;
    private int currentSlotId;
    private List<Tuple> rows = Collections.emptyList();

    IndexScanOperator(String indexId, Object searchKey, BufferPoolManager bufferPoolManager) {
        this.indexId = indexId;
        this.searchKey = searchKey;
        this.bufferPoolManager = bufferPoolManager;
    }

    @Override
    public void init() {
        currentSlotId = 0;
        rows = bufferPoolManager.getTableRows(indexId);
    }

    @Override
    public Tuple next() {
        while (currentSlotId < rows.size()) {
            Tuple tuple = rows.get(currentSlotId++).copy();
            if (Objects.equals(tuple.values.get("key"), searchKey)) {
                return tuple;
            }
        }
        return null;
    }

    @Override
    public void close() {
    }
}

class FilterOperator implements ExecutionOperator {
    private final ExecutionOperator childOperator;
    private final ExpressionNode predicate;

    FilterOperator(ExecutionOperator childOperator, ExpressionNode predicate) {
        this.childOperator = childOperator;
        this.predicate = predicate;
    }

    @Override
    public void init() {
        childOperator.init();
    }

    @Override
    public Tuple next() {
        Tuple tuple;
        while ((tuple = childOperator.next()) != null) {
            if (ExpressionEvaluator.evaluate(tuple, predicate)) {
                return tuple;
            }
        }
        return null;
    }

    @Override
    public void close() {
        childOperator.close();
    }
}

class ExpressionEvaluator {
    static boolean evaluate(Tuple tuple, ExpressionNode node) {
        if (node == null) {
            return true;
        }
        if (node instanceof BinaryExpressionNode binary) {
            if ("AND".equalsIgnoreCase(binary.operator)) {
                return evaluate(tuple, binary.left) && evaluate(tuple, binary.right);
            }
            Object left = valueOf(tuple, binary.left);
            Object right = valueOf(tuple, binary.right);
            if ("=".equals(binary.operator)) {
                return Objects.equals(left, right);
            }
        }
        return true;
    }

    private static Object valueOf(Tuple tuple, ExpressionNode node) {
        if (node instanceof IdentifierNode identifier) {
            return tuple.values.get(identifier.name);
        }
        if (node instanceof LiteralNode literal) {
            return literal.value;
        }
        return null;
    }
}

class NestedLoopJoinOperator implements ExecutionOperator {
    private final ExecutionOperator outerChild;
    private final ExecutionOperator innerChild;
    private final ExpressionNode joinPredicate;
    private Tuple currentOuter;

    NestedLoopJoinOperator(ExecutionOperator outerChild, ExecutionOperator innerChild, ExpressionNode joinPredicate) {
        this.outerChild = outerChild;
        this.innerChild = innerChild;
        this.joinPredicate = joinPredicate;
    }

    @Override
    public void init() {
        outerChild.init();
        innerChild.init();
        currentOuter = outerChild.next();
    }

    @Override
    public Tuple next() {
        while (currentOuter != null) {
            Tuple inner;
            while ((inner = innerChild.next()) != null) {
                Tuple joined = new Tuple();
                joined.values.putAll(currentOuter.values);
                joined.values.putAll(inner.values);
                if (joinPredicate == null || ExpressionEvaluator.evaluate(joined, joinPredicate)) {
                    return joined;
                }
            }
            innerChild.close();
            innerChild.init();
            currentOuter = outerChild.next();
        }
        return null;
    }

    @Override
    public void close() {
        outerChild.close();
        innerChild.close();
    }
}

class EmptyOperator implements ExecutionOperator {
    @Override
    public void init() {
    }

    @Override
    public Tuple next() {
        return null;
    }

    @Override
    public void close() {
    }
}

class InsertExecutionOperator implements ExecutionOperator {
    private final String tableId;
    private final List<Tuple> valuesToInsert;
    private final BufferPoolManager bufferPoolManager;
    private boolean executed;

    InsertExecutionOperator(String tableId, List<Tuple> valuesToInsert, BufferPoolManager bufferPoolManager) {
        this.tableId = tableId;
        this.valuesToInsert = valuesToInsert;
        this.bufferPoolManager = bufferPoolManager;
    }

    @Override
    public void init() {
        executed = false;
    }

    @Override
    public Tuple next() {
        if (executed) {
            return null;
        }
        for (Tuple tuple : valuesToInsert) {
            bufferPoolManager.insertRow(tableId, tuple);
        }
        executed = true;
        Tuple affected = new Tuple();
        affected.values.put("rowCount", valuesToInsert.size());
        return affected;
    }

    @Override
    public void close() {
    }
}

class ExecutionPlanner {
    private final PlanCacheManager cacheManager;
    private final BufferPoolManager bufferPoolManager;

    ExecutionPlanner(PlanCacheManager cacheManager, BufferPoolManager bufferPoolManager) {
        this.cacheManager = cacheManager;
        this.bufferPoolManager = bufferPoolManager;
    }

    ExecutionOperator buildExecutionTree(PhysicalPlanTree plan) {
        return buildOperator(plan.root);
    }

    PhysicalPlanTree getPlanFromCache(String sqlHash) {
        return cacheManager.get(sqlHash);
    }

    private ExecutionOperator buildOperator(PhysicalOperatorNode node) {
        if (node instanceof PhysicalInsert insert) {
            return new InsertExecutionOperator(insert.tableId, insert.rows, bufferPoolManager);
        }
        if (node instanceof PhysicalFilter filter) {
            ExecutionOperator child = buildOperator(filter.children.get(0));
            return new FilterOperator(child, filter.predicate);
        }
        if (node instanceof PhysicalSeqScan seqScan) {
            return new SequentialScanOperator(seqScan.tableId, bufferPoolManager);
        }
        if (node instanceof PhysicalIndexScan indexScan) {
            return new IndexScanOperator(indexScan.indexId, indexScan.scanKey, bufferPoolManager);
        }
        throw new InvalidASTException("Unsupported physical operator: " + node.getClass().getSimpleName());
    }
}

class ResultFormatter {
    String formatAsJSON(List<Tuple> tuples) {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < tuples.size(); i++) {
            Tuple tuple = tuples.get(i);
            builder.append("{");
            int j = 0;
            for (Map.Entry<String, Object> entry : tuple.values.entrySet()) {
                if (j++ > 0) {
                    builder.append(",");
                }
                builder.append('"').append(escape(entry.getKey())).append('"').append(":");
                Object value = entry.getValue();
                if (value instanceof Number || value instanceof Boolean) {
                    builder.append(value);
                } else {
                    builder.append('"').append(escape(String.valueOf(value))).append('"');
                }
            }
            builder.append("}");
            if (i < tuples.size() - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    String formatAsCSV(List<Tuple> tuples) {
        if (tuples.isEmpty()) {
            return "";
        }
        List<String> headers = new ArrayList<>(tuples.get(0).values.keySet());
        StringBuilder builder = new StringBuilder(String.join(",", headers)).append('\n');
        for (Tuple tuple : tuples) {
            List<String> cells = new ArrayList<>();
            for (String header : headers) {
                cells.add(String.valueOf(tuple.values.get(header)));
            }
            builder.append(String.join(",", cells)).append('\n');
        }
        return builder.toString().trim();
    }

    private String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}

class ResultOutput {
    final Boolean success;
    final String message;
    final int rowCount;
    final String payload;

    ResultOutput(Boolean success, String message, int rowCount, String payload) {
        this.success = success;
        this.message = message;
        this.rowCount = rowCount;
        this.payload = payload;
    }
}

class SessionContext {
    final String sessionId;
    final String userId;
    final String authToken;
    final String ipAddress;
    final Instant expiresAt;
    Instant lastActiveAt;

    SessionContext(String sessionId, String userId, String authToken, String ipAddress, Instant expiresAt) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.authToken = authToken;
        this.ipAddress = ipAddress;
        this.expiresAt = expiresAt;
        this.lastActiveAt = Instant.now();
    }
}

class TokenSet {
    final String authToken;
    final String refreshToken;
    final Instant expiresAt;

    TokenSet(String authToken, String refreshToken, Instant expiresAt) {
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }
}

class SessionMgr {
    final Map<String, SessionContext> activeSessions = new HashMap<>();
    private String latestAuthToken;

    TokenSet createSession(String userId, String ip, String device) {
        String authToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        SessionContext context = new SessionContext(UUID.randomUUID().toString(), userId, authToken, ip, Instant.now().plusSeconds(3600));
        activeSessions.put(authToken, context);
        latestAuthToken = authToken;
        return new TokenSet(authToken, refreshToken, context.expiresAt);
    }

    boolean validateSession(String authToken) {
        SessionContext context = activeSessions.get(authToken);
        if (context == null) {
            return false;
        }
        if (context.expiresAt.isBefore(Instant.now())) {
            activeSessions.remove(authToken);
            return false;
        }
        context.lastActiveAt = Instant.now();
        return true;
    }

    TokenSet refreshSession(String refreshToken) {
        if (latestAuthToken == null || !activeSessions.containsKey(latestAuthToken)) {
            throw new SecurityException("No active session to refresh");
        }
        SessionContext old = activeSessions.remove(latestAuthToken);
        return createSession(old.userId, old.ipAddress, "refreshed");
    }

    void revokeSession(String authToken) {
        activeSessions.remove(authToken);
    }

    String latestAuthToken() {
        return latestAuthToken;
    }
}

class AuthService {
    String hashPassword(String rawPassword) {
        return Integer.toHexString(rawPassword.hashCode());
    }

    boolean verifyPassword(String raw, String hashed) {
        return Objects.equals(hashPassword(raw), hashed);
    }

    TokenSet generateAuthAndRefreshToken(String userId) {
        return new TokenSet("auth-" + userId, "refresh-" + userId, Instant.now().plusSeconds(3600));
    }

    boolean verifyToken(String authToken) {
        return authToken != null && !authToken.isBlank();
    }
}

interface IRoleService {
    boolean checkAccess(String userId, String resource, String action);
}

class Permission {
    final String permId;
    final String permissionName;
    final String resource;
    final String action;
    final String description;

    Permission(String permId, String permissionName, String resource, String action, String description) {
        this.permId = permId;
        this.permissionName = permissionName;
        this.resource = resource;
        this.action = action;
        this.description = description;
    }
}

class Role {
    final String id;
    final String roleName;
    final List<Permission> permissions;

    Role(String id, String roleName, List<Permission> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.permissions = new ArrayList<>(permissions);
    }
}

class UserRole {
    final String userId;
    final String roleId;
    final Instant createdAt;
    final String ownerId;

    UserRole(String userId, String roleId, String ownerId) {
        this.userId = userId;
        this.roleId = roleId;
        this.ownerId = ownerId;
        this.createdAt = Instant.now();
    }
}

class UserRoleRepository {
    private final List<UserRole> userRoles = new ArrayList<>();

    List<UserRole> findByUserId(String userId) {
        List<UserRole> result = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            if (userRole.userId.equals(userId)) {
                result.add(userRole);
            }
        }
        return result;
    }

    void save(UserRole userRole) {
        userRoles.add(userRole);
    }

    void delete(String userId, String roleId) {
        userRoles.removeIf(userRole -> userRole.userId.equals(userId) && userRole.roleId.equals(roleId));
    }
}

class PermissionRepository {
    private final Map<String, List<Permission>> permissionsByRole = new HashMap<>();

    List<Permission> findByRoleId(String roleId) {
        return permissionsByRole.getOrDefault(roleId, List.of());
    }

    void save(String roleId, Permission permission) {
        permissionsByRole.computeIfAbsent(roleId, key -> new ArrayList<>()).add(permission);
    }
}

class RoleService implements IRoleService {
    private final UserRoleRepository userRoleRepo = new UserRoleRepository();
    private final PermissionRepository permRepo = new PermissionRepository();
    private final Map<String, Role> roles = new HashMap<>();

    void createRole(String name, List<Permission> permissions) {
        Role role = new Role(UUID.randomUUID().toString(), name, permissions);
        roles.put(name, role);
        for (Permission permission : permissions) {
            permRepo.save(role.id, permission);
        }
    }

    void assignRole(String userID, String roleID) {
        Role role = roles.get(roleID);
        if (role == null) {
            role = roles.getOrDefault("admin", null);
        }
        if (role == null) {
            throw new SecurityException("Role not found: " + roleID);
        }
        userRoleRepo.save(new UserRole(userID, role.id, userID));
    }

    void unassignRole(String userID, String roleID) {
        userRoleRepo.delete(userID, roleID);
    }

    @Override
    public boolean checkAccess(String userId, String resource, String action) {
        for (UserRole userRole : userRoleRepo.findByUserId(userId)) {
            Role role = roles.values().stream().filter(item -> item.id.equals(userRole.roleId)).findFirst().orElse(null);
            if (role == null) {
                continue;
            }
            for (Permission permission : permRepo.findByRoleId(role.id)) {
                if (permission.resource.equalsIgnoreCase(resource) && permission.action.equalsIgnoreCase(action)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Policy {
    final String policyId;
    final String policyName;
    final String targetTableId;
    final String actionType;
    ExpressionNode conditionExpression;
    boolean isEnabled;
    final String assignmentType;

    Policy(String policyName, String targetTableId, String actionType, ExpressionNode conditionExpression, boolean isEnabled, String assignmentType) {
        this.policyId = UUID.randomUUID().toString();
        this.policyName = policyName;
        this.targetTableId = targetTableId;
        this.actionType = actionType;
        this.conditionExpression = conditionExpression;
        this.isEnabled = isEnabled;
        this.assignmentType = assignmentType;
    }
}

class AuditLog {
    final String logId = UUID.randomUUID().toString();
    final String actorID;
    final String action;
    final String targetId;
    final String timestamp;
    final String ipAddress;

    AuditLog(String actorID, String action, String targetId, String ipAddress) {
        this.actorID = actorID;
        this.action = action;
        this.targetId = targetId;
        this.timestamp = Instant.now().toString();
        this.ipAddress = ipAddress;
    }

    void logging(String info) {
    }

    List<AuditLog> getLogging(Object options) {
        return List.of(this);
    }
}

class PolicyService {
    private final Map<String, List<Policy>> policyCache = new HashMap<>();

    Policy createPolicy(String name, String target, ExpressionNode conditions) {
        Policy policy = new Policy(name, target, "SELECT", conditions, true, "ROW_LEVEL");
        policyCache.computeIfAbsent(target, key -> new ArrayList<>()).add(policy);
        return policy;
    }

    void assignPolicy(String policyId, String targetId, String type) {
    }

    void togglePolicyStatus(String policyId, boolean isEnabled) {
        for (List<Policy> policies : policyCache.values()) {
            for (Policy policy : policies) {
                if (policy.policyId.equals(policyId)) {
                    policy.isEnabled = isEnabled;
                }
            }
        }
    }

    List<Policy> getEnabledPoliciesForTable(String tableName, String actionType) {
        List<Policy> result = new ArrayList<>();
        for (Policy policy : policyCache.getOrDefault(tableName, List.of())) {
            if (policy.isEnabled && policy.actionType.equalsIgnoreCase(actionType)) {
                result.add(policy);
            }
        }
        return result;
    }
}

class User {
    String userID;
    String username;
    String email;
    String hashedPassword;

    User createNewUser(String username, String email, String rawPassword) {
        User user = new User();
        user.userID = UUID.randomUUID().toString();
        user.username = username;
        user.email = email;
        user.hashedPassword = Integer.toHexString(rawPassword.hashCode());
        return user;
    }

    User getUserData(String username) {
        return this;
    }

    Boolean checkUserExist(String username) {
        return Objects.equals(this.username, username);
    }

    Boolean updateUserInfo() {
        return true;
    }
}

class CatalogManager {
    private final Map<String, SchemaInfo> schemas = new HashMap<>();
    private final Map<String, TableStatistics> statistics = new HashMap<>();
    private final BufferPoolManager bufferPoolManager = new BufferPoolManager();

    void registerTable(String tableName, SchemaInfo schemaInfo) {
        schemas.put(tableName, schemaInfo);
        TableStatistics stats = new TableStatistics();
        stats.rowCount = 0;
        stats.pageCount = 1;
        statistics.put(tableName, stats);
        bufferPoolManager.getTableRows(tableName);
    }

    TableStatistics getTableStatistics(String tableName) {
        return statistics.computeIfAbsent(tableName, key -> new TableStatistics());
    }

    SchemaInfo getTableSchema(String tableName) {
        SchemaInfo schemaInfo = schemas.get(tableName);
        if (schemaInfo == null) {
            throw new InvalidASTException("Unknown table: " + tableName);
        }
        return schemaInfo;
    }

    BufferPoolManager getBufferPoolManager() {
        return bufferPoolManager;
    }
}

class QueryValidation {
    private final IRoleService roleService;
    private final CatalogManager catalog;

    QueryValidation(IRoleService roleService, CatalogManager catalog) {
        this.roleService = roleService;
        this.catalog = catalog;
    }

    Void validateQuery(ASTBuildResult astBuild, String userID) {
        if (!astBuild.success || astBuild.root == null) {
            throw new InvalidASTException(astBuild.errorMessage == null ? "Invalid AST" : astBuild.errorMessage);
        }
        String resource = tableNameOf(astBuild.root);
        String action = astBuild.root instanceof InsertStatementNode ? "INSERT" : "SELECT";
        catalog.getTableSchema(resource);
        if (!roleService.checkAccess(userID, resource, action)) {
            throw new SecurityException("Unauthorized access");
        }
        return null;
    }

    private String tableNameOf(ASTNode node) {
        if (node instanceof SelectStatementNode select) {
            return select.fromClause.tableName;
        }
        if (node instanceof InsertStatementNode insert) {
            return insert.targetTable.tableName;
        }
        throw new InvalidASTException("Cannot resolve table name");
    }
}

class ExecutionService {
    private final ParserService parser = new ParserService();
    private final CatalogManager catalog = new CatalogManager();
    private final RoleService roleService = new RoleService();
    private final PolicyService policyService = new PolicyService();
    private final QueryValidation validator = new QueryValidation(roleService, catalog);
    private final QueryOptimizer optimizer = new QueryOptimizer(catalog, new CostModel(), new CardinalityEstimator());
    private final ExecutionPlanner planner = new ExecutionPlanner(new PlanCacheManager(), catalog.getBufferPoolManager());
    private final ResultFormatter formatter = new ResultFormatter();
    private final SessionMgr sessionMgr = new SessionMgr();
    private final AuditLog auditLog = new AuditLog("system", "BOOT", "dbms", "127.0.0.1");
    private final AuthService authService = new AuthService();

    ResultOutput execute(String sql, String authToken) {
        if (!sessionMgr.validateSession(authToken)) {
            return new ResultOutput(false, "Session expired or invalid", 0, "");
        }

        ASTBuildResult astBuild = parser.parserSQL(sql);
        if (!astBuild.success) {
            auditLog.logging("Parse Failure: Syntax Error");
            return new ResultOutput(false, astBuild.errorMessage, 0, "");
        }

        String userId = sessionMgr.activeSessions.get(authToken).userId;
        validator.validateQuery(astBuild, userId);

        ASTNode ast = astBuild.root;
        if (ast instanceof SelectStatementNode select) {
            List<Policy> policies = policyService.getEnabledPoliciesForTable(select.fromClause.tableName, "SELECT");
            for (Policy policy : policies) {
                ast = ast.accept(new PolicyRewriterVisitor(policy.conditionExpression));
            }
        }

        LogicalOperator logicalPlan = optimizer.generateLogicalPlan(ast);
        LogicalOperator optimizedLogical = optimizer.optimizeLogicalPlan(logicalPlan);
        PhysicalPlanTree physicalPlan = optimizer.optimizePhysicalPlan(optimizedLogical);
        ExecutionOperator root = planner.buildExecutionTree(physicalPlan);

        List<Tuple> results = new ArrayList<>();
        root.init();
        Tuple tuple;
        while ((tuple = root.next()) != null) {
            results.add(tuple);
        }
        root.close();

        if (ast instanceof InsertStatementNode) {
            return new ResultOutput(true, "Insert success", results.isEmpty() ? 1 : (Integer) results.get(0).values.getOrDefault("rowCount", 1), formatter.formatAsJSON(results));
        }
        return new ResultOutput(true, "Query success", results.size(), formatter.formatAsJSON(results));
    }

    ParserService getParser() {
        return parser;
    }

    CatalogManager getCatalogManager() {
        return catalog;
    }

    RoleService getRoleService() {
        return roleService;
    }

    SessionMgr getSessionMgr() {
        return sessionMgr;
    }
}