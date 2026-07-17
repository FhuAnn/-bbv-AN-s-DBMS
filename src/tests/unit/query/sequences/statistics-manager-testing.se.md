# StatisticsManager Testing - Main Functional Sequences

---

## 1. Collect Statistics

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Table
participant Index

Test->>StatisticsManager: collectStatistics(table)
StatisticsManager->>Table: scanRows()
Table-->>StatisticsManager: rows
StatisticsManager->>Index: gatherIndexStats()
Index-->>StatisticsManager: indexStats
StatisticsManager-->>Test: stats
```

---

## 2. Update Histogram

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Histogram

Test->>StatisticsManager: updateHistogram(column)
StatisticsManager->>Histogram: rebuild()
Histogram-->>StatisticsManager: histogram
StatisticsManager-->>Test: success
```

---

## 3. Estimate Cardinality

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Histogram

Test->>StatisticsManager: estimateCardinality(predicate)
StatisticsManager->>Histogram: estimate()
Histogram-->>StatisticsManager: cardinality
StatisticsManager-->>Test: estimate
```

---

## 4. Refresh Statistics

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant CatalogManager

Test->>StatisticsManager: refreshStatistics()
StatisticsManager->>CatalogManager: getTableStatistics()
CatalogManager-->>StatisticsManager: stats
StatisticsManager-->>Test: refreshed
```

---

## 5. Delete Statistics

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant CatalogManager

Test->>StatisticsManager: deleteStatistics(tableName)
StatisticsManager->>CatalogManager: removeStatistics(tableName)
CatalogManager-->>StatisticsManager: removed
StatisticsManager-->>Test: success
```

---

## 6. Update Row Count

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Table

Test->>StatisticsManager: updateRowCount(tableName,rowCount)
StatisticsManager->>Table: setRowCount(rowCount)
Table-->>StatisticsManager: updated
StatisticsManager-->>Test: success
```

---

## 7. Estimate Distinct Values

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Histogram

Test->>StatisticsManager: estimateDistinctValues(columnName)
StatisticsManager->>Histogram: estimateDistinct(columnName)
Histogram-->>StatisticsManager: count
StatisticsManager-->>Test: count
```

---

## 8. Estimate Null Fraction

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Histogram

Test->>StatisticsManager: estimateNullFraction(columnName)
StatisticsManager->>Histogram: estimateNulls(columnName)
Histogram-->>StatisticsManager: fraction
StatisticsManager-->>Test: fraction
```

---

## 9. Estimate Selectivity

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Histogram

Test->>StatisticsManager: estimateSelectivity(predicate)
StatisticsManager->>Histogram: selectivity(predicate)
Histogram-->>StatisticsManager: selectivity
StatisticsManager-->>Test: selectivity
```

---

## 10. Analyze Table

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Table

Test->>StatisticsManager: analyzeTable(tableName)
StatisticsManager->>Table: scanRows(tableName)
Table-->>StatisticsManager: rows
StatisticsManager-->>Test: analyzed
```

---

## 11. Estimate Row Count

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Table

Test->>StatisticsManager: estimateRowCount(tableName)
StatisticsManager->>Table: countRows(tableName)
Table-->>StatisticsManager: rowCount
StatisticsManager-->>Test: rowCount
```

---

## 12. Estimate Data Distribution

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Histogram

Test->>StatisticsManager: estimateDataDistribution(columnName)
StatisticsManager->>Histogram: profileDistribution(columnName)
Histogram-->>StatisticsManager: distribution
StatisticsManager-->>Test: distribution
```

---

## 13. Analyze Column

```mermaid
sequenceDiagram
actor Test
participant StatisticsManager
participant Column

Test->>StatisticsManager: analyzeColumn(columnName)
StatisticsManager->>Column: inspectStatistics(columnName)
Column-->>StatisticsManager: stats
StatisticsManager-->>Test: stats
```
