# DBMS Core Architecture

```mermaid
classDiagram
direction LR

class DatabaseServer{
    +serverId
    +version
    +status
    +start()
    +stop()
    +restart()
}

class DatabaseManager{
    +createDatabase()
    +dropDatabase()
    +getDatabase()
    +listDatabases()
}

class Database{
    +databaseId
    +name
    +owner
    +open()
    +close()
}

class Schema{
    +schemaId
    +name
    +createTable()
    +dropTable()
}

class Table{
    +tableId
    +name
    +insert()
    +update()
    +delete()
}

class Column{
    +columnId
    +name
    +dataType
    +nullable
}

class Row{
    +rowId
    +values
    +version
}

class DataType{
    <<enumeration>>
}

class Constraint{
    <<abstract>>
    +validate()
}

class PrimaryKey{
    +columns
}

class ForeignKey{
    +referenceTable
}

class UniqueConstraint

class CheckConstraint

class Index{
    <<abstract>>
    +search()
    +insertKey()
    +deleteKey()
}

class BTreeIndex

class HashIndex

class BitmapIndex

class Partition{
    +partitionKey
}

class View{
    +queryDefinition
}

class StoredProcedure{
    +execute()
}

class Trigger{
    +fire()
}

class Sequence{
    +nextValue()
}

class Transaction{
    +transactionId
    +begin()
    +commit()
    +rollback()
}

class TransactionManager{
    +beginTransaction()
    +commit()
    +rollback()
}

class LockManager{
    +acquireLock()
    +releaseLock()
}

class MVCCManager{
    +createVersion()
    +garbageCollect()
}

class BufferPool{
    +pinPage()
    +flushPage()
}

class Page{
    +pageId
}

class StorageEngine{
    +readPage()
    +writePage()
}

class FileManager{
    +read()
    +write()
}

class WALManager{
    +appendLog()
    +flush()
}

class RecoveryManager{
    +recover()
}

class CatalogManager{
    +registerTable()
    +findTable()
}

class SQLParser{
    +parse()
}

class Lexer{
    +tokenize()
}

class AST{
    +root
}

class QueryOptimizer{
    +optimize()
}

class LogicalPlan{
    +operators
}

class PhysicalPlan{
    +operators
}

class QueryExecutor{
    +execute()
}

class StatisticsManager{
    +collect()
}

class SecurityManager{
    +authenticate()
    +authorize()
}

class User

class Role

class Permission

DatabaseServer --> DatabaseManager
DatabaseServer --> TransactionManager
DatabaseServer --> StorageEngine
DatabaseServer --> CatalogManager
DatabaseServer --> SecurityManager

DatabaseManager --> Database
Database --> Schema
Schema --> Table
Schema --> View
Schema --> StoredProcedure
Schema --> Sequence

Table --> Column
Table --> Row
Table --> Index
Table --> Constraint
Table --> Partition
Table --> Trigger

Constraint <|-- PrimaryKey
Constraint <|-- ForeignKey
Constraint <|-- UniqueConstraint
Constraint <|-- CheckConstraint

Index <|-- BTreeIndex
Index <|-- HashIndex
Index <|-- BitmapIndex

Column --> DataType
ForeignKey --> Table

TransactionManager --> Transaction
TransactionManager --> LockManager
TransactionManager --> MVCCManager
TransactionManager --> WALManager

StorageEngine --> BufferPool
StorageEngine --> FileManager
BufferPool --> Page

RecoveryManager --> WALManager

CatalogManager --> Table
CatalogManager --> Index
CatalogManager --> Schema

SQLParser --> Lexer
SQLParser --> AST
AST --> LogicalPlan
QueryOptimizer --> LogicalPlan
QueryOptimizer --> PhysicalPlan
QueryExecutor --> PhysicalPlan
QueryExecutor --> Transaction

StatisticsManager --> Table
QueryOptimizer --> StatisticsManager

SecurityManager --> User
SecurityManager --> Role
Role --> Permission
```