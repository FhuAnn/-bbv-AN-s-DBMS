TableScanOperator Test Sequence Diagrams

1. Init_ShouldPrepareTableScan
```mermaid
sequenceDiagram
    actor Test as TableScanOperatorTests
    participant Scan as TableScanOperator
    participant Storage as StorageEngine
    participant Table as TableMetadata
    participant Page as Page

    Test->>Scan: new TableScanOperator(storageEngine, table)
    Test->>Scan: init()
    Scan->>Table: getStorageInfo()
    Table-->>Scan: storageInfo
    Scan->>Storage: readPage(firstPageId)
    Storage-->>Scan: firstPage
    Scan->>Page: pin()
    Page-->>Scan: pinned
    Scan->>Scan: initialize currentPageId and currentSlot
    Test->>Scan: isInitialized()
    Scan-->>Test: true
```
2. Next_ShouldReturnFirstRow
```mermaid
sequenceDiagram
    actor Test as TableScanOperatorTests
    participant Scan as TableScanOperator
    participant Page as Page
    participant RecordMgr as RecordManager

    Test->>Scan: init()
    Test->>Scan: next()
    Scan->>Page: getRecord(currentSlot)
    Page->>RecordMgr: decode record
    RecordMgr-->>Page: firstRow
    Page-->>Scan: firstRow
    Scan->>Scan: increment currentSlot
    Scan-->>Test: firstRow
    Test->>Test: assertEquals(expectedFirstRow, result)
```
3. Next_ShouldReturnRowsSequentially
```mermaid
sequenceDiagram
    actor Test as TableScanOperatorTests
    participant Scan as TableScanOperator
    participant Page as Page

    Test->>Scan: init()

    Test->>Scan: next()
    Scan->>Page: getRecord(slot 0)
    Page-->>Scan: firstRow
    Scan-->>Test: firstRow

    Test->>Scan: next()
    Scan->>Page: getRecord(slot 1)
    Page-->>Scan: secondRow
    Scan-->>Test: secondRow

    Test->>Scan: next()
    Scan->>Page: getRecord(slot 2)
    Page-->>Scan: thirdRow
    Scan-->>Test: thirdRow

    Test->>Test: assert rows preserve scan order
```
4. Next_ShouldReturnNullWhenNoRowsRemain
```mermaid
sequenceDiagram
    actor Test as TableScanOperatorTests
    participant Scan as TableScanOperator
    participant Page as Page
    participant Storage as StorageEngine

    Test->>Scan: init()

    loop Consume all rows
        Test->>Scan: next()
        Scan-->>Test: Row
    end

    Test->>Scan: next()
    Scan->>Page: hasNextRecord()
    Page-->>Scan: false
    Scan->>Storage: hasNextPage()
    Storage-->>Scan: false
    Scan-->>Test: null
    Test->>Test: assertNull(result)
```
5. Close_ShouldReleaseCurrentPage
```mermaid
sequenceDiagram
    actor Test as TableScanOperatorTests
    participant Scan as TableScanOperator
    participant Page as Page
    participant Storage as StorageEngine

    Test->>Scan: init()
    Test->>Scan: close()
    Scan->>Page: unpin()
    Page-->>Scan: unpinned
    Scan->>Storage: releasePage(pageId)
    Storage-->>Scan: released
    Scan->>Scan: clear current page state
    Scan-->>Test: closed
    Test->>Scan: isClosed()
    Scan-->>Test: true
```

6. NextBeforeInit_ShouldRejectOperation
```mermaid

sequenceDiagram
    actor Test as TableScanOperatorTests
    participant Scan as TableScanOperator

    Test->>Scan: new TableScanOperator(storageEngine, table)
    Test->>Scan: next()
    Scan->>Scan: verify initialized state
    Scan-->>Test: IllegalStateException
    Test->>Test: assertThrows(IllegalStateException)
```
7. NextAfterClose_ShouldRejectOperation
```mermaid
sequenceDiagram
    actor Test as TableScanOperatorTests
    participant Scan as TableScanOperator

    Test->>Scan: init()
    Test->>Scan: close()
    Test->>Scan: next()
    Scan->>Scan: verify closed state
    Scan-->>Test: IllegalStateException
    Test->>Test: assertThrows(IllegalStateException)
    ```