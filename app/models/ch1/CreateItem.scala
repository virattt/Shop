package models.ch1


import play.api.data.Form
import play.api.data.Forms.{mapping, text, of}
import play.api.data.format.Formats.doubleFormat
import play.api.data.validation.Constraints._

/**
 * Created by virat on 6/2/16.
 */
case class CreateItem(name: String, price: Double)

object CreateItem {
    val form = Form(mapping(
        "name" -> text.verifying(nonEmpty),
        "price" -> of[Double].verifying(min(0.0, strict = true))
    )(CreateItem.apply)(CreateItem.unapply))
}
