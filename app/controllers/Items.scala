package controllers

import models.ch1.CreateItem
import models.ch1.CreateItem.form
import models.ch2.{Item, Shop}
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.mvc.{Action, Controller}

/**
 * Created by virat on 5/29/16.
 */
object Items extends Controller {

    val shop = Shop

    /**
     * Same as implicit val writesItem below
     */
    implicit val writesItem = Json.writes[Item]

    /**
     * Same as implicit val readsCreateItem below
     */
    implicit val readsCreateItem = (
        (__ \ "name").read(Reads.minLength[String](1)) and
        (__ \ "price").read(Reads.min[Double](0))
        )(CreateItem.apply _)

    val create = Action(parse.urlFormEncoded) {
        implicit request => {
            form.bindFromRequest().fold(
                formWithErrors => render {
                    case Accepts.Html() => BadRequest(views.html.createForm(formWithErrors))
                    case Accepts.Json() => BadRequest(formWithErrors.errorsAsJson)
                },
                createItem => {
                    shop.create(createItem.name, createItem.price) match {
                        case Some(item) => render {
                            case Accepts.Html() => Redirect(routes.Items.details(item.id))
                            case Accepts.Json() => Ok(Json.toJson(item))
                        }
                        case None => InternalServerError
                    }
                }
            )
        }
    }

    val createForm = Action {
        Ok(views.html.createForm(form))
    }

    val list = Action { implicit request =>
        val items = shop.list()
        render {
            case Accepts.Html() => Ok(views.html.list(items))
            case Accepts.Json() => Ok(Json.toJson(items))
        }
    }

    def details(id: Long) = Action {
        shop.get(id) match {
            case Some(item) =>
                Ok(views.html.details(item))

            case None => NotFound
        }
    }

    def update(id: Long) = Action {
        NotImplemented
    }

    def delete(id: Long) = Action {
        NotImplemented
    }
}
