Scan Strategy Test Sequences

1. ScanContext_Constructor_ShouldCreateContext
```mermaid
sequenceDiagram
    actor Test
    participant Context as ScanContext

    Test->>Context: new ScanContext(table, predicate, statistics, indexes)
    Context-->>Test: context
    Test->>Test: assertNotNull(context)
```
2. ScanContext_Constructor_ShouldStoreTable
```mermaid

sequenceDiagram
    actor Test
    participant Context as ScanContext

    Test->>Context: new ScanContext(table, predicate, statistics, indexes)
    Test->>Context: getTable()
    Context-->>Test: table
    Test->>Test: assertSame(expectedTable, table)
```
3. ScanContext_Constructor_ShouldStorePredicate
```mermaid

sequenceDiagram
    actor Test
    participant Context as ScanContext

    Test->>Context: new ScanContext(table, predicate, statistics, indexes)
    Test->>Context: getPredicate()
    Context-->>Test: predicate
    Test->>Test: assertSame(expectedPredicate, predicate)
```
4. ScanContext_Constructor_ShouldStoreStatistics
```mermaid

sequenceDiagram
    actor Test
    participant Context as ScanContext

    Test->>Context: new ScanContext(table, predicate, statistics, indexes)
    Test->>Context: getStatistics()
    Context-->>Test: statistics
    Test->>Test: assertSame(expectedStatistics, statistics)
```
5. ScanContext_Constructor_ShouldStoreIndexes
```mermaid

sequenceDiagram
    actor Test
    participant Context as ScanContext

    Test->>Context: new ScanContext(table, predicate, statistics, indexes)
    Test->>Context: getIndexes()
    Context-->>Test: indexes
    Test->>Test: assertEquals(expectedIndexes, indexes)
```
6. ScanContext_HasPredicate_ShouldReturnTrueWhenPredicateExists
```mermaid

sequenceDiagram
    actor Test
    participant Context as ScanContext

    Test->>Context: hasPredicate()
    Context-->>Test: true
    Test->>Test: assertTrue(result)
```
7. ScanContext_HasPredicate_ShouldReturnFalseWhenPredicateMissing
```mermaid

sequenceDiagram
    actor Test
    participant Context as ScanContext

    Test->>Context: hasPredicate()
    Context-->>Test: false
    Test->>Test: assertFalse(result)
```
8. ScanContext_HasIndexes_ShouldReturnTrueWhenIndexesExist
```mermaid

sequenceDiagram
    actor Test
    participant Context as ScanContext

    Test->>Context: hasIndexes()
    Context-->>Test: true
    Test->>Test: assertTrue(result)
```
9. ScanContext_HasIndexes_ShouldReturnFalseWhenIndexesEmpty
```mermaid

sequenceDiagram
    actor Test
    participant Context as ScanContext

    Test->>Context: hasIndexes()
    Context-->>Test: false
    Test->>Test: assertFalse(result)
```
10. SequentialScan_Supports_ShouldReturnTrueForValidContext
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as SequentialScanStrategy
    participant Context as ScanContext

    Test->>Strategy: supports(context)
    Strategy->>Context: getTable()
    Context-->>Strategy: table
    Strategy-->>Test: true
    Test->>Test: assertTrue(result)
```
11. SequentialScan_EstimateCost_ShouldUseTablePageCount
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as SequentialScanStrategy
    participant Context as ScanContext
    participant Stats as TableStats

    Test->>Strategy: estimateCost(context)
    Strategy->>Context: getStatistics()
    Context-->>Strategy: statistics
    Strategy->>Stats: getPageCount()
    Stats-->>Strategy: pageCount
    Strategy-->>Test: estimatedCost
    Test->>Test: assertEquals(pageCount, estimatedCost)
```
12. SequentialScan_CreateOperator_ShouldReturnTableScanOperator
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as SequentialScanStrategy
    participant Context as ScanContext
    participant Operator as TableScanOperator

    Test->>Strategy: createOperator(context)
    Strategy->>Context: getTable()
    Context-->>Strategy: table
    Strategy->>Operator: new TableScanOperator(table)
    Operator-->>Strategy: operator
    Strategy-->>Test: operator
    Test->>Test: assertInstanceOf(TableScanOperator)
```
13. SequentialScan_GetType_ShouldReturnSequentialScan
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as SequentialScanStrategy

    Test->>Strategy: getType()
    Strategy-->>Test: SEQUENTIAL_SCAN
    Test->>Test: assertEquals(SEQUENTIAL_SCAN, result)
```
14. IndexScan_Supports_ShouldReturnTrueWhenMatchingIndexExists
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as IndexScanStrategy
    participant Context as ScanContext

    Test->>Strategy: supports(context)
    Strategy->>Context: hasPredicate()
    Context-->>Strategy: true
    Strategy->>Context: hasIndexes()
    Context-->>Strategy: true
    Strategy->>Strategy: findMatchingIndex(context)
    Strategy-->>Strategy: matchingIndex
    Strategy-->>Test: true
    Test->>Test: assertTrue(result)
```
15. IndexScan_Supports_ShouldReturnFalseWhenNoIndexesExist
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as IndexScanStrategy
    participant Context as ScanContext

    Test->>Strategy: supports(context)
    Strategy->>Context: hasIndexes()
    Context-->>Strategy: false
    Strategy-->>Test: false
    Test->>Test: assertFalse(result)
```
16. IndexScan_Supports_ShouldReturnFalseWhenPredicateMissing
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as IndexScanStrategy
    participant Context as ScanContext

    Test->>Strategy: supports(context)
    Strategy->>Context: hasPredicate()
    Context-->>Strategy: false
    Strategy-->>Test: false
    Test->>Test: assertFalse(result)
```
17. IndexScan_Supports_ShouldReturnFalseWhenNoMatchingIndexExists
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as IndexScanStrategy
    participant Context as ScanContext

    Test->>Strategy: supports(context)
    Strategy->>Context: hasPredicate()
    Context-->>Strategy: true
    Strategy->>Context: hasIndexes()
    Context-->>Strategy: true
    Strategy->>Strategy: findMatchingIndex(context)
    Strategy-->>Strategy: null
    Strategy-->>Test: false
    Test->>Test: assertFalse(result)
```
18. IndexScan_EstimateCost_ShouldReturnIndexScanCost
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as IndexScanStrategy
    participant Context as ScanContext
    participant Stats as TableStats

    Test->>Strategy: estimateCost(context)
    Strategy->>Context: getStatistics()
    Context-->>Strategy: statistics
    Strategy->>Stats: getRowCount()
    Stats-->>Strategy: rowCount
    Strategy->>Strategy: calculate index scan cost
    Strategy-->>Test: estimatedCost
    Test->>Test: assertEquals(expectedCost, estimatedCost)
```
19. IndexScan_CreateOperator_ShouldReturnIndexScanOperator
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as IndexScanStrategy
    participant Context as ScanContext
    participant Operator as IndexScanOperator

    Test->>Strategy: createOperator(context)
    Strategy->>Context: getTable()
    Context-->>Strategy: table
    Strategy->>Context: getPredicate()
    Context-->>Strategy: predicate
    Strategy->>Operator: new IndexScanOperator(table, selectedIndex, predicate)
    Operator-->>Strategy: operator
    Strategy-->>Test: operator
    Test->>Test: assertInstanceOf(IndexScanOperator)
```
20. IndexScan_CreateOperator_ShouldUseSelectedIndex
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as IndexScanStrategy
    participant Context as ScanContext
    participant Operator as IndexScanOperator

    Test->>Strategy: supports(context)
    Strategy-->>Test: true
    Test->>Strategy: createOperator(context)
    Strategy->>Operator: new IndexScanOperator(table, selectedIndex, predicate)
    Operator-->>Strategy: operator
    Strategy-->>Test: operator
    Test->>Operator: getIndex()
    Operator-->>Test: selectedIndex
    Test->>Test: assertSame(expectedIndex, selectedIndex)
```
21. IndexScan_GetType_ShouldReturnIndexScan
```mermaid

sequenceDiagram
    actor Test
    participant Strategy as IndexScanStrategy

    Test->>Strategy: getType()
    Strategy-->>Test: INDEX_SCAN
    Test->>Test: assertEquals(INDEX_SCAN, result)
```
22. Selector_Constructor_ShouldStoreStrategies
```mermaid

sequenceDiagram
    actor Test
    participant Selector as ScanStrategySelector

    Test->>Selector: new ScanStrategySelector(strategies)
    Selector-->>Test: selector
    Test->>Selector: getStrategies()
    Selector-->>Test: strategies
    Test->>Test: assertEquals(expectedStrategies, strategies)
```
23. Selector_Select_ShouldChooseLowestCostStrategy
```mermaid

sequenceDiagram
    actor Test
    participant Selector as ScanStrategySelector
    participant First as ScanStrategy
    participant Second as ScanStrategy

    Test->>Selector: select(context)
    Selector->>First: supports(context)
    First-->>Selector: true
    Selector->>First: estimateCost(context)
    First-->>Selector: 100
    Selector->>Second: supports(context)
    Second-->>Selector: true
    Selector->>Second: estimateCost(context)
    Second-->>Selector: 20
    Selector-->>Test: secondStrategy
    Test->>Test: assertSame(secondStrategy, result)
```
24. Selector_Select_ShouldIgnoreUnsupportedStrategy
```mermaid

sequenceDiagram
    actor Test
    participant Selector as ScanStrategySelector
    participant Unsupported as ScanStrategy
    participant Supported as ScanStrategy

    Test->>Selector: select(context)
    Selector->>Unsupported: supports(context)
    Unsupported-->>Selector: false
    Note over Selector,Unsupported: estimateCost is not called
    Selector->>Supported: supports(context)
    Supported-->>Selector: true
    Selector->>Supported: estimateCost(context)
    Supported-->>Selector: cost
    Selector-->>Test: supportedStrategy
    Test->>Test: assertSame(supportedStrategy, result)
```
25. Selector_Select_ShouldReturnSequentialScanWhenIndexUnsupported
```mermaid

sequenceDiagram
    actor Test
    participant Selector as ScanStrategySelector
    participant Sequential as SequentialScanStrategy
    participant Index as IndexScanStrategy

    Test->>Selector: select(context)
    Selector->>Sequential: supports(context)
    Sequential-->>Selector: true
    Selector->>Sequential: estimateCost(context)
    Sequential-->>Selector: sequentialCost
    Selector->>Index: supports(context)
    Index-->>Selector: false
    Selector-->>Test: SequentialScanStrategy
    Test->>Test: assertSame(sequentialStrategy, result)
```
26. Selector_Select_ShouldReturnIndexScanWhenIndexCostIsLower
```mermaid

sequenceDiagram
    actor Test
    participant Selector as ScanStrategySelector
    participant Sequential as SequentialScanStrategy
    participant Index as IndexScanStrategy

    Test->>Selector: select(context)
    Selector->>Sequential: supports(context)
    Sequential-->>Selector: true
    Selector->>Sequential: estimateCost(context)
    Sequential-->>Selector: 100
    Selector->>Index: supports(context)
    Index-->>Selector: true
    Selector->>Index: estimateCost(context)
    Index-->>Selector: 10
    Selector-->>Test: IndexScanStrategy
    Test->>Test: assertSame(indexStrategy, result)
```
27. Selector_Select_ShouldRejectWhenNoStrategySupportsContext
```mermaid

sequenceDiagram
    actor Test
    participant Selector as ScanStrategySelector
    participant First as ScanStrategy
    participant Second as ScanStrategy

    Test->>Selector: select(context)
    Selector->>First: supports(context)
    First-->>Selector: false
    Selector->>Second: supports(context)
    Second-->>Selector: false
    Selector-->>Test: throw IllegalStateException
    Test->>Test: assertThrows(IllegalStateException)
```
28. QueryOptimizer_CreateScanOperator_ShouldDelegateToSelectedStrategy
```mermaid

sequenceDiagram
    actor Test
    participant Optimizer as QueryOptimizer
    participant Selector as ScanStrategySelector
    participant Strategy as ScanStrategy
    participant Operator as ExecutionOperator

    Test->>Optimizer: createScanOperator(context)
    Optimizer->>Selector: select(context)
    Selector-->>Optimizer: strategy
    Optimizer->>Strategy: createOperator(context)
    Strategy-->>Optimizer: operator
    Optimizer-->>Test: operator
    Test->>Test: assertSame(expectedOperator, operator)
```