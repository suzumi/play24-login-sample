package models

import slick.lifted.{TableQuery, Tag, FlatShapeLevel}
//import slick.model.Table
import slick.driver.H2Driver.simple._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class User(id: Int, mail: String, password: String)

/**
 * テーブルスキーマの定義
 */
class Users(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def mail = column[String]("mail")
  def password = column[String]("password")
  def * = (id, mail, password) <> (User.tupled, User.unapply)
}

/**
 * DAOの定義
 */
object Users {
  lazy val users = TableQuery[Users]

//  def authenticate(mail: String, password: String): Future[Option[User]] = {
//    findByEmail(mail).map { user =>
//      user match {
//        case Some(user) => Some(user)
//        case _ => None
//      }
//    }
//  }

//  def findByEmail(mail: String) = {
//    users.filter(_.mail === mail).map { user =>
//      user match {
//        case user: Users => Some(user)
//        case _ => None
//      }
//    }
//  }

}
