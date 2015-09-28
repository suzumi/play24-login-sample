package models

import play.api.db
import slick.lifted.{TableQuery, Tag, FlatShapeLevel}
import slick.driver.H2Driver.api._
import scala.concurrent.Future
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits.global

case class User(mail: String, password: String)

/**
 * テーブルスキーマの定義
 */
class UserTag(tag: Tag) extends Table[User](tag, "users") {
//  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def mail = column[String]("mail")
  def password = column[String]("password")
  def * = (mail, password) <> (User.tupled, User.unapply)
}

/**
 * DAOの定義
 */
object User {
  lazy val usersQuery = TableQuery[UserTag]

  def createTable(implicit s: Session) = usersQuery.schema.create

  def dropTable(implicit s: Session) = usersQuery.schema.drop

//  def authenticate(mail: String, password: String): Future[Option[User]] = {
//    findByEmail(mail).map { user =>
//      user match {
//        case Some(user) => Some(user)
//        case _ => None
//      }
//    }
//  }

  def findByEmail(mail: String)(implicit s: Session) = {
    usersQuery.filter(_.mail === mail).take(1)
  }

}
