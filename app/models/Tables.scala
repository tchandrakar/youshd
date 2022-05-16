package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Brands.schema, BrandsHistory.schema, Users.schema, UsersBrandLink.schema, UsersBrandLinkHistory.schema, UsersBrandUniqueLinkClickCount.schema, UsersHistory.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Brands
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(200,true)
   *  @param definedCommission Database column defined_commission SqlType(int4)
   *  @param createdAt Database column created_at SqlType(timestamp without time zone)
   *  @param updatedAt Database column updated_at SqlType(timestamp without time zone) */
  case class BrandsRow(id: Int, name: String, definedCommission: Int, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching BrandsRow objects using plain SQL queries */
  implicit def GetResultBrandsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[BrandsRow] = GR{
    prs => import prs._
    BrandsRow.tupled((<<[Int], <<[String], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table brands. Objects of this class serve as prototypes for rows in queries. */
  class Brands(_tableTag: Tag) extends profile.api.Table[BrandsRow](_tableTag, "brands") {
    def * = (id, name, definedCommission, createdAt, updatedAt) <> (BrandsRow.tupled, BrandsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(definedCommission), Rep.Some(createdAt), Rep.Some(updatedAt))).shaped.<>({r=>import r._; _1.map(_=> BrandsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(200,true) */
    val name: Rep[String] = column[String]("name", O.Length(200,varying=true))
    /** Database column defined_commission SqlType(int4) */
    val definedCommission: Rep[Int] = column[Int]("defined_commission")
    /** Database column created_at SqlType(timestamp without time zone) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp without time zone) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
  }
  /** Collection-like TableQuery object for table Brands */
  lazy val Brands = new TableQuery(tag => new Brands(tag))

  /** Entity class storing rows of table BrandsHistory
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param brandId Database column brand_id SqlType(int4)
   *  @param definedCommission Database column defined_commission SqlType(int4)
   *  @param updatedAt Database column updated_at SqlType(timestamp without time zone) */
  case class BrandsHistoryRow(id: Int, brandId: Int, definedCommission: Int, updatedAt: java.sql.Timestamp)
  /** GetResult implicit for fetching BrandsHistoryRow objects using plain SQL queries */
  implicit def GetResultBrandsHistoryRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp]): GR[BrandsHistoryRow] = GR{
    prs => import prs._
    BrandsHistoryRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table brands_history. Objects of this class serve as prototypes for rows in queries. */
  class BrandsHistory(_tableTag: Tag) extends profile.api.Table[BrandsHistoryRow](_tableTag, "brands_history") {
    def * = (id, brandId, definedCommission, updatedAt) <> (BrandsHistoryRow.tupled, BrandsHistoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(brandId), Rep.Some(definedCommission), Rep.Some(updatedAt))).shaped.<>({r=>import r._; _1.map(_=> BrandsHistoryRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column brand_id SqlType(int4) */
    val brandId: Rep[Int] = column[Int]("brand_id")
    /** Database column defined_commission SqlType(int4) */
    val definedCommission: Rep[Int] = column[Int]("defined_commission")
    /** Database column updated_at SqlType(timestamp without time zone) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")

    /** Foreign key referencing Brands (database name brands_history_brand_id_fkey) */
    lazy val brandsFk = foreignKey("brands_history_brand_id_fkey", brandId, Brands)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table BrandsHistory */
  lazy val BrandsHistory = new TableQuery(tag => new BrandsHistory(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param firstName Database column first_name SqlType(varchar), Length(50,true)
   *  @param lastName Database column last_name SqlType(varchar), Length(50,true)
   *  @param address Database column address SqlType(varchar), Length(500,true)
   *  @param emailId Database column email_id SqlType(varchar), Length(20,true)
   *  @param password Database column password SqlType(varchar), Length(200,true)
   *  @param createdAt Database column created_at SqlType(timestamp without time zone)
   *  @param updatedAt Database column updated_at SqlType(timestamp without time zone)
   *  @param isActive Database column is_active SqlType(bool), Default(true) */
  case class UsersRow(id: Int, firstName: String, lastName: String, address: String, emailId: String, password: String, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, isActive: Boolean = true)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Boolean]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * = (id, firstName, lastName, address, emailId, password, createdAt, updatedAt, isActive) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(firstName), Rep.Some(lastName), Rep.Some(address), Rep.Some(emailId), Rep.Some(password), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(isActive))).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column first_name SqlType(varchar), Length(50,true) */
    val firstName: Rep[String] = column[String]("first_name", O.Length(50,varying=true))
    /** Database column last_name SqlType(varchar), Length(50,true) */
    val lastName: Rep[String] = column[String]("last_name", O.Length(50,varying=true))
    /** Database column address SqlType(varchar), Length(500,true) */
    val address: Rep[String] = column[String]("address", O.Length(500,varying=true))
    /** Database column email_id SqlType(varchar), Length(20,true) */
    val emailId: Rep[String] = column[String]("email_id", O.Length(20,varying=true))
    /** Database column password SqlType(varchar), Length(200,true) */
    val password: Rep[String] = column[String]("password", O.Length(200,varying=true))
    /** Database column created_at SqlType(timestamp without time zone) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp without time zone) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column is_active SqlType(bool), Default(true) */
    val isActive: Rep[Boolean] = column[Boolean]("is_active", O.Default(true))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))

  /** Entity class storing rows of table UsersBrandLink
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(int4)
   *  @param brandId Database column brand_id SqlType(int4)
   *  @param uniqueLink Database column unique_link SqlType(varchar), Length(100,true)
   *  @param createdAt Database column created_at SqlType(timestamp without time zone)
   *  @param updatedAt Database column updated_at SqlType(timestamp without time zone)
   *  @param validity Database column validity SqlType(timestamp without time zone) */
  case class UsersBrandLinkRow(id: Int, userId: Int, brandId: Int, uniqueLink: String, createdAt: java.sql.Timestamp, updatedAt: java.sql.Timestamp, validity: java.sql.Timestamp)
  /** GetResult implicit for fetching UsersBrandLinkRow objects using plain SQL queries */
  implicit def GetResultUsersBrandLinkRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[UsersBrandLinkRow] = GR{
    prs => import prs._
    UsersBrandLinkRow.tupled((<<[Int], <<[Int], <<[Int], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table users_brand_link. Objects of this class serve as prototypes for rows in queries. */
  class UsersBrandLink(_tableTag: Tag) extends profile.api.Table[UsersBrandLinkRow](_tableTag, "users_brand_link") {
    def * = (id, userId, brandId, uniqueLink, createdAt, updatedAt, validity) <> (UsersBrandLinkRow.tupled, UsersBrandLinkRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(userId), Rep.Some(brandId), Rep.Some(uniqueLink), Rep.Some(createdAt), Rep.Some(updatedAt), Rep.Some(validity))).shaped.<>({r=>import r._; _1.map(_=> UsersBrandLinkRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(int4) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column brand_id SqlType(int4) */
    val brandId: Rep[Int] = column[Int]("brand_id")
    /** Database column unique_link SqlType(varchar), Length(100,true) */
    val uniqueLink: Rep[String] = column[String]("unique_link", O.Length(100,varying=true))
    /** Database column created_at SqlType(timestamp without time zone) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column updated_at SqlType(timestamp without time zone) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column validity SqlType(timestamp without time zone) */
    val validity: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("validity")

    /** Foreign key referencing Brands (database name users_brand_link_brand_id_fkey) */
    lazy val brandsFk = foreignKey("users_brand_link_brand_id_fkey", brandId, Brands)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Users (database name users_brand_link_user_id_fkey) */
    lazy val usersFk = foreignKey("users_brand_link_user_id_fkey", userId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Uniqueness Index over (uniqueLink) (database name users_brand_link_unique_link_index) */
    val index1 = index("users_brand_link_unique_link_index", uniqueLink, unique=true)
  }
  /** Collection-like TableQuery object for table UsersBrandLink */
  lazy val UsersBrandLink = new TableQuery(tag => new UsersBrandLink(tag))

  /** Entity class storing rows of table UsersBrandLinkHistory
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param usersBrandLinkId Database column users_brand_link_id SqlType(int4)
   *  @param validity Database column validity SqlType(timestamp without time zone)
   *  @param userUniqueLinkClickCount Database column user_unique_link_click_count SqlType(int4), Default(None)
   *  @param updatedAt Database column updated_at SqlType(timestamp without time zone)
   *  @param superseededBy Database column superseeded_by SqlType(int4), Default(None) */
  case class UsersBrandLinkHistoryRow(id: Int, usersBrandLinkId: Int, validity: java.sql.Timestamp, userUniqueLinkClickCount: Option[Int] = None, updatedAt: java.sql.Timestamp, superseededBy: Option[Int] = None)
  /** GetResult implicit for fetching UsersBrandLinkHistoryRow objects using plain SQL queries */
  implicit def GetResultUsersBrandLinkHistoryRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Option[Int]]): GR[UsersBrandLinkHistoryRow] = GR{
    prs => import prs._
    UsersBrandLinkHistoryRow.tupled((<<[Int], <<[Int], <<[java.sql.Timestamp], <<?[Int], <<[java.sql.Timestamp], <<?[Int]))
  }
  /** Table description of table users_brand_link_history. Objects of this class serve as prototypes for rows in queries. */
  class UsersBrandLinkHistory(_tableTag: Tag) extends profile.api.Table[UsersBrandLinkHistoryRow](_tableTag, "users_brand_link_history") {
    def * = (id, usersBrandLinkId, validity, userUniqueLinkClickCount, updatedAt, superseededBy) <> (UsersBrandLinkHistoryRow.tupled, UsersBrandLinkHistoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(usersBrandLinkId), Rep.Some(validity), userUniqueLinkClickCount, Rep.Some(updatedAt), superseededBy)).shaped.<>({r=>import r._; _1.map(_=> UsersBrandLinkHistoryRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column users_brand_link_id SqlType(int4) */
    val usersBrandLinkId: Rep[Int] = column[Int]("users_brand_link_id")
    /** Database column validity SqlType(timestamp without time zone) */
    val validity: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("validity")
    /** Database column user_unique_link_click_count SqlType(int4), Default(None) */
    val userUniqueLinkClickCount: Rep[Option[Int]] = column[Option[Int]]("user_unique_link_click_count", O.Default(None))
    /** Database column updated_at SqlType(timestamp without time zone) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column superseeded_by SqlType(int4), Default(None) */
    val superseededBy: Rep[Option[Int]] = column[Option[Int]]("superseeded_by", O.Default(None))

    /** Foreign key referencing UsersBrandLink (database name users_brand_link_history_users_brand_link_id_fkey) */
    lazy val usersBrandLinkFk = foreignKey("users_brand_link_history_users_brand_link_id_fkey", usersBrandLinkId, UsersBrandLink)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table UsersBrandLinkHistory */
  lazy val UsersBrandLinkHistory = new TableQuery(tag => new UsersBrandLinkHistory(tag))

  /** Entity class storing rows of table UsersBrandUniqueLinkClickCount
   *  @param userId Database column user_id SqlType(int4)
   *  @param usersBrandLinkId Database column users_brand_link_id SqlType(int4)
   *  @param clickedUserId Database column clicked_user_id SqlType(int4) */
  case class UsersBrandUniqueLinkClickCountRow(userId: Int, usersBrandLinkId: Int, clickedUserId: Int)
  /** GetResult implicit for fetching UsersBrandUniqueLinkClickCountRow objects using plain SQL queries */
  implicit def GetResultUsersBrandUniqueLinkClickCountRow(implicit e0: GR[Int]): GR[UsersBrandUniqueLinkClickCountRow] = GR{
    prs => import prs._
    UsersBrandUniqueLinkClickCountRow.tupled((<<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table users_brand_unique_link_click_count. Objects of this class serve as prototypes for rows in queries. */
  class UsersBrandUniqueLinkClickCount(_tableTag: Tag) extends profile.api.Table[UsersBrandUniqueLinkClickCountRow](_tableTag, "users_brand_unique_link_click_count") {
    def * = (userId, usersBrandLinkId, clickedUserId) <> (UsersBrandUniqueLinkClickCountRow.tupled, UsersBrandUniqueLinkClickCountRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(userId), Rep.Some(usersBrandLinkId), Rep.Some(clickedUserId))).shaped.<>({r=>import r._; _1.map(_=> UsersBrandUniqueLinkClickCountRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(int4) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column users_brand_link_id SqlType(int4) */
    val usersBrandLinkId: Rep[Int] = column[Int]("users_brand_link_id")
    /** Database column clicked_user_id SqlType(int4) */
    val clickedUserId: Rep[Int] = column[Int]("clicked_user_id")

    /** Primary key of UsersBrandUniqueLinkClickCount (database name users_brand_unique_link_click_count_pkey) */
    val pk = primaryKey("users_brand_unique_link_click_count_pkey", (userId, usersBrandLinkId, clickedUserId))

    /** Foreign key referencing Users (database name users_brand_unique_link_click_count_clicked_user_id_fkey) */
    lazy val usersFk1 = foreignKey("users_brand_unique_link_click_count_clicked_user_id_fkey", clickedUserId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Users (database name users_brand_unique_link_click_count_user_id_fkey) */
    lazy val usersFk2 = foreignKey("users_brand_unique_link_click_count_user_id_fkey", userId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing UsersBrandLink (database name users_brand_unique_link_click_count_users_brand_link_id_fkey) */
    lazy val usersBrandLinkFk = foreignKey("users_brand_unique_link_click_count_users_brand_link_id_fkey", usersBrandLinkId, UsersBrandLink)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)

    /** Index over (userId,clickedUserId) (database name users_brand_unique_link_click_count_user_id_clicked_user_id_ind) */
    val index1 = index("users_brand_unique_link_click_count_user_id_clicked_user_id_ind", (userId, clickedUserId))
  }
  /** Collection-like TableQuery object for table UsersBrandUniqueLinkClickCount */
  lazy val UsersBrandUniqueLinkClickCount = new TableQuery(tag => new UsersBrandUniqueLinkClickCount(tag))

  /** Entity class storing rows of table UsersHistory
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(int4)
   *  @param firstName Database column first_name SqlType(varchar), Length(50,true)
   *  @param lastName Database column last_name SqlType(varchar), Length(50,true)
   *  @param address Database column address SqlType(varchar), Length(500,true)
   *  @param emailId Database column email_id SqlType(varchar), Length(20,true)
   *  @param userActivity Database column user_activity SqlType(varchar), Length(2000,true), Default(user is created)
   *  @param updatedAt Database column updated_at SqlType(timestamp without time zone)
   *  @param superseededBy Database column superseeded_by SqlType(int4), Default(None) */
  case class UsersHistoryRow(id: Int, userId: Int, firstName: String, lastName: String, address: String, emailId: String, userActivity: String = "user is created", updatedAt: java.sql.Timestamp, superseededBy: Option[Int] = None)
  /** GetResult implicit for fetching UsersHistoryRow objects using plain SQL queries */
  implicit def GetResultUsersHistoryRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[Int]]): GR[UsersHistoryRow] = GR{
    prs => import prs._
    UsersHistoryRow.tupled((<<[Int], <<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[Int]))
  }
  /** Table description of table users_history. Objects of this class serve as prototypes for rows in queries. */
  class UsersHistory(_tableTag: Tag) extends profile.api.Table[UsersHistoryRow](_tableTag, "users_history") {
    def * = (id, userId, firstName, lastName, address, emailId, userActivity, updatedAt, superseededBy) <> (UsersHistoryRow.tupled, UsersHistoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(userId), Rep.Some(firstName), Rep.Some(lastName), Rep.Some(address), Rep.Some(emailId), Rep.Some(userActivity), Rep.Some(updatedAt), superseededBy)).shaped.<>({r=>import r._; _1.map(_=> UsersHistoryRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(int4) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column first_name SqlType(varchar), Length(50,true) */
    val firstName: Rep[String] = column[String]("first_name", O.Length(50,varying=true))
    /** Database column last_name SqlType(varchar), Length(50,true) */
    val lastName: Rep[String] = column[String]("last_name", O.Length(50,varying=true))
    /** Database column address SqlType(varchar), Length(500,true) */
    val address: Rep[String] = column[String]("address", O.Length(500,varying=true))
    /** Database column email_id SqlType(varchar), Length(20,true) */
    val emailId: Rep[String] = column[String]("email_id", O.Length(20,varying=true))
    /** Database column user_activity SqlType(varchar), Length(2000,true), Default(user is created) */
    val userActivity: Rep[String] = column[String]("user_activity", O.Length(2000,varying=true), O.Default("user is created"))
    /** Database column updated_at SqlType(timestamp without time zone) */
    val updatedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column superseeded_by SqlType(int4), Default(None) */
    val superseededBy: Rep[Option[Int]] = column[Option[Int]]("superseeded_by", O.Default(None))

    /** Foreign key referencing Users (database name users_history_user_id_fkey) */
    lazy val usersFk = foreignKey("users_history_user_id_fkey", userId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table UsersHistory */
  lazy val UsersHistory = new TableQuery(tag => new UsersHistory(tag))
}
