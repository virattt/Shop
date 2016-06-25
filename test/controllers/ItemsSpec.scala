package controllers

import play.api.http.MimeTypes
import play.api.libs.json.Json
import play.api.test.{FakeRequest, WithApplication, PlaySpecification}

/**
 * Created by virat on 6/13/16.
 */
class ItemsSpec extends PlaySpecification {
    "Items controller" should {
        "list items" in new WithApplication {
            route(FakeRequest(controllers.routes.Items.list()).withHeaders(ACCEPT -> MimeTypes.JSON)) match {
                case Some(response) => status(response) must equalTo(OK)
                    contentAsJson(response) must equalTo(Json.arr())
                case None => failure
            }
        }
    }
}
