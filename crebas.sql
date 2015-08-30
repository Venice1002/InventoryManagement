/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     2015/8/30 9:34:12                            */
/*==============================================================*/


if exists (select 1
            from  sysobjects
           where  id = object_id('Customer')
            and   type = 'U')
   drop table Customer
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Destory')
            and   name  = 'Relationship_4_FK'
            and   indid > 0
            and   indid < 255)
   drop index Destory.Relationship_4_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Destory')
            and   type = 'U')
   drop table Destory
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Enter')
            and   name  = 'Relationship_8_FK'
            and   indid > 0
            and   indid < 255)
   drop index Enter.Relationship_8_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Enter')
            and   name  = 'Relationship_7_FK'
            and   indid > 0
            and   indid < 255)
   drop index Enter.Relationship_7_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Enter')
            and   type = 'U')
   drop table Enter
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Exchange')
            and   name  = 'Relationship_6_FK'
            and   indid > 0
            and   indid < 255)
   drop index Exchange.Relationship_6_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Exchange')
            and   type = 'U')
   drop table Exchange
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('"Exit"')
            and   name  = 'Relationship_10_FK'
            and   indid > 0
            and   indid < 255)
   drop index "Exit".Relationship_10_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('"Exit"')
            and   name  = 'Relationship_9_FK'
            and   indid > 0
            and   indid < 255)
   drop index "Exit".Relationship_9_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"Exit"')
            and   type = 'U')
   drop table "Exit"
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Goods')
            and   type = 'U')
   drop table Goods
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('GoodsManagement')
            and   name  = 'GoodsManagement2_FK'
            and   indid > 0
            and   indid < 255)
   drop index GoodsManagement.GoodsManagement2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('GoodsManagement')
            and   name  = 'GoodsManagement_FK'
            and   indid > 0
            and   indid < 255)
   drop index GoodsManagement.GoodsManagement_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('GoodsManagement')
            and   type = 'U')
   drop table GoodsManagement
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('HouseManagement')
            and   name  = 'HouseManagement2_FK'
            and   indid > 0
            and   indid < 255)
   drop index HouseManagement.HouseManagement2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('HouseManagement')
            and   name  = 'HouseManagement_FK'
            and   indid > 0
            and   indid < 255)
   drop index HouseManagement.HouseManagement_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('HouseManagement')
            and   type = 'U')
   drop table HouseManagement
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('"Return"')
            and   name  = 'Relationship_12_FK'
            and   indid > 0
            and   indid < 255)
   drop index "Return".Relationship_12_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('"Return"')
            and   name  = 'Relationship_11_FK'
            and   indid > 0
            and   indid < 255)
   drop index "Return".Relationship_11_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"Return"')
            and   type = 'U')
   drop table "Return"
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Store')
            and   name  = 'Relationship_2_FK'
            and   indid > 0
            and   indid < 255)
   drop index Store.Relationship_2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('Store')
            and   name  = 'Relationship_1_FK'
            and   indid > 0
            and   indid < 255)
   drop index Store.Relationship_1_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Store')
            and   type = 'U')
   drop table Store
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Supplier')
            and   type = 'U')
   drop table Supplier
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Warehouse')
            and   type = 'U')
   drop table Warehouse
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Worker')
            and   type = 'U')
   drop table Worker
go

/*==============================================================*/
/* Table: Customer                                              */
/*==============================================================*/
create table Customer (
   customerId           int                  not null,
   customerName         varchar(20)          null,
   customerAddress      varchar(40)          null,
   customerPhone        bigint               null,
   constraint PK_CUSTOMER primary key nonclustered (customerId)
)
go

/*==============================================================*/
/* Table: Destory                                               */
/*==============================================================*/
create table Destory (
   destoryId            int                  not null,
   houseId              int                  null,
   goodsId              int                  null,
   batchId              int                  null,
   destoryTime          datetime             null,
   destoryAmount        float                null,
   destoryNote          varchar(100)         null,
   workerId             int                  null,
   constraint PK_DESTORY primary key nonclustered (destoryId)
)
go

/*==============================================================*/
/* Index: Relationship_4_FK                                     */
/*==============================================================*/
create index Relationship_4_FK on Destory (
houseId ASC,
goodsId ASC,
batchId ASC
)
go

/*==============================================================*/
/* Table: Enter                                                 */
/*==============================================================*/
create table Enter (
   enterId              int                  not null,
   houseId              int                  null,
   goodsId              int                  null,
   supplierId           int                  null,
   enterAmount          float                null,
   enterTime            datetime             null,
   enterPrice           money                null,
   batchId              int                  null,
   enterNote            varchar(100)         null,
   workerId             int                  null,
   constraint PK_ENTER primary key nonclustered (enterId)
)
go

/*==============================================================*/
/* Index: Relationship_7_FK                                     */
/*==============================================================*/
create index Relationship_7_FK on Enter (
houseId ASC,
goodsId ASC,
batchId ASC
)
go

/*==============================================================*/
/* Index: Relationship_8_FK                                     */
/*==============================================================*/
create index Relationship_8_FK on Enter (
supplierId ASC
)
go

/*==============================================================*/
/* Table: Exchange                                              */
/*==============================================================*/
create table Exchange (
   exchangeId           int                  not null,
   houseId              int                  null,
   goodsId              int                  null,
   batchId              int                  null,
   exchangeAmount       float                null,
   exchangeTime         datetime             null,
   exchangeNote         varchar(100)         null,
   workerId             int                  null,
   constraint PK_EXCHANGE primary key nonclustered (exchangeId)
)
go

/*==============================================================*/
/* Index: Relationship_6_FK                                     */
/*==============================================================*/
create index Relationship_6_FK on Exchange (
houseId ASC,
goodsId ASC,
batchId ASC
)
go

/*==============================================================*/
/* Table: "Exit"                                                */
/*==============================================================*/
create table "Exit" (
   exitId               int                  not null,
   houseId              int                  null,
   goodsId              int                  null,
   batchId              int                  null,
   customerId           int                  null,
   exitAmount           float                null,
   exitTime             datetime             null,
   exitPrice            money                null,
   exitNote             varchar(100)         null,
   workerId             int                  null,
   constraint PK_EXIT primary key nonclustered (exitId)
)
go

/*==============================================================*/
/* Index: Relationship_9_FK                                     */
/*==============================================================*/
create index Relationship_9_FK on "Exit" (
houseId ASC,
goodsId ASC,
batchId ASC
)
go

/*==============================================================*/
/* Index: Relationship_10_FK                                    */
/*==============================================================*/
create index Relationship_10_FK on "Exit" (
customerId ASC
)
go

/*==============================================================*/
/* Table: Goods                                                 */
/*==============================================================*/
create table Goods (
   goodsId              int                  not null,
   goodsName            varchar(20)          null,
   goodsType            varchar(20)          null,
   produceDate          datetime             null,
   limitedDate          datetime             null,
   goodsNote            varchar(100)         null,
   unit                 varchar(20)          null,
   manufacturer         varchar(20)          null,
   goodsLevel           int                  null,
   goodsVolume          varchar(20)          null,
   constraint PK_GOODS primary key nonclustered (goodsId)
)
go

/*==============================================================*/
/* Table: GoodsManagement                                       */
/*==============================================================*/
create table GoodsManagement (
   goodsId              int                  not null,
   workerId             int                  not null,
   goodsManageId        int                  null,
   goodsManageTime      datetime             null,
   goodsManageNote      varchar(100)         null,
   constraint PK_GOODSMANAGEMENT primary key (goodsId, workerId)
)
go

/*==============================================================*/
/* Index: GoodsManagement_FK                                    */
/*==============================================================*/
create index GoodsManagement_FK on GoodsManagement (
goodsId ASC
)
go

/*==============================================================*/
/* Index: GoodsManagement2_FK                                   */
/*==============================================================*/
create index GoodsManagement2_FK on GoodsManagement (
workerId ASC
)
go

/*==============================================================*/
/* Table: HouseManagement                                       */
/*==============================================================*/
create table HouseManagement (
   houseId              int                  not null,
   workerId             int                  not null,
   houseManageId        int                  not null,
   houseManageTime      datetime             null,
   houseManageNote      varchar(100)         null,
   constraint PK_HOUSEMANAGEMENT primary key (houseId, workerId)
)
go

/*==============================================================*/
/* Index: HouseManagement_FK                                    */
/*==============================================================*/
create index HouseManagement_FK on HouseManagement (
houseId ASC
)
go

/*==============================================================*/
/* Index: HouseManagement2_FK                                   */
/*==============================================================*/
create index HouseManagement2_FK on HouseManagement (
workerId ASC
)
go

/*==============================================================*/
/* Table: "Return"                                              */
/*==============================================================*/
create table "Return" (
   returnId             int                  not null,
   customerId           int                  null,
   houseId              int                  null,
   goodsId              int                  null,
   batchId              int                  null,
   returnAmount         float                null,
   returnTime           datetime             null,
   returnNote           varchar(100)         null,
   workerId             int                  null,
   constraint PK_RETURN primary key nonclustered (returnId)
)
go

/*==============================================================*/
/* Index: Relationship_11_FK                                    */
/*==============================================================*/
create index Relationship_11_FK on "Return" (
houseId ASC,
goodsId ASC,
batchId ASC
)
go

/*==============================================================*/
/* Index: Relationship_12_FK                                    */
/*==============================================================*/
create index Relationship_12_FK on "Return" (
customerId ASC
)
go

/*==============================================================*/
/* Table: Store                                                 */
/*==============================================================*/
create table Store (
   storeId              int                  not null,
   houseId              int                  not null,
   goodsId              int                  not null,
   storeAmount          float                null,
   batchId              int                  not null,
   unit                 varchar(20)          null,
   storePrice           money                null,
   constraint PK_STORE primary key nonclustered (houseId, goodsId, batchId)
)
go

/*==============================================================*/
/* Index: Relationship_1_FK                                     */
/*==============================================================*/
create index Relationship_1_FK on Store (
houseId ASC
)
go

/*==============================================================*/
/* Index: Relationship_2_FK                                     */
/*==============================================================*/
create index Relationship_2_FK on Store (
goodsId ASC
)
go

/*==============================================================*/
/* Table: Supplier                                              */
/*==============================================================*/
create table Supplier (
   supplierId           int                  not null,
   supplierName         varchar(20)          null,
   supplierAddress      varchar(40)          null,
   supplierPhone        bigint               null,
   constraint PK_SUPPLIER primary key nonclustered (supplierId)
)
go

/*==============================================================*/
/* Table: Warehouse                                             */
/*==============================================================*/
create table Warehouse (
   houseId              int                  not null,
   houseName            varchar(20)          null,
   houseAddress         varchar(40)          null,
   linkman              varchar(20)          null,
   linkPhone            bigint               null,
   totalAmount          float                null,
   houseType            varchar(20)          null,
   totalPrice           money                null,
   hosueState           varchar(20)          null,
   lastCheckDate        datetime             null,
   houseNote            varchar(100)         null,
   houseLevel           int                  null,
   constraint PK_WAREHOUSE primary key nonclustered (houseId)
)
go

/*==============================================================*/
/* Table: Worker                                                */
/*==============================================================*/
create table Worker (
   workerId             int                  not null,
   workerName           varchar(20)          null,
   workerPwd            varchar(20)          not null,
   workerPermission     int                  null,
   workerDepartment     varchar(20)          null,
   workerPhone          bigint               null,
   constraint PK_WORKER primary key nonclustered (workerId)
)
go

