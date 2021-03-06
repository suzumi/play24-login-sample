package controllers

import jp.t2v.lab.play2.auth.LoginLogout
import models.{LoginForm, User}
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc._
import play.db.NamedDatabase
import slick.driver
import slick.driver.H2Driver.simple._
import slick.driver.H2Driver.api._
import slick.driver.JdbcProfile
import scala.concurrent.Future
import jp.t2v.lab.play2.auth.AuthElement
import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller with LoginLogout with AuthConfigImpl {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val loginForm = Form {
    mapping("mail" -> email, "password" -> text)(LoginForm.apply)(LoginForm.unapply)
  }

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

//  def authenticate = TODO

//  def createTable = Action {
//    Users.createTable
//    Ok(views.html.login(loginForm))
//  }
//
//  def dropTable = Action {
//    Users.dropTable
//    Ok(views.html.login(loginForm))
//  }

//  /**
//   * ログアウト処理では任意の処理を行った後、
//   * gotoLogoutSucceeded メソッドを呼び出した結果を返して下さい。
//   *
//   * gotoLogoutSucceeded メソッドは Future[Result] を返します。
//   * 以下のようにflashingなどを追加することもできます。
//   *
//   *   gotoLogoutSucceeded.map(_.flashing(
//   *     "success" -> "You've been logged out"
//   *   ))
//   */
//  def logout = Action.async { implicit request =>
//    // do something...
//    gotoLogoutSucceeded
//  }
//
  /**
   * ログイン処理では認証が成功した場合、
   * gotoLoginSucceeded メソッドを呼び出した結果を返して下さい。
   *
   * gotoLoginSucceeded メソッドも gotoLogoutSucceeded と同じく Future[Result] を返します。
   * 任意の処理を追加することも可能です。
   */
  def authenticate = Action.async { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(views.html.login(formWithErrors))),
      user => gotoLoginSucceeded(user.mail)
    )
  }
}
