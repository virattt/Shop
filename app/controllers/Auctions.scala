package controllers

import play.api.mvc.{Action, Controller}

/**
 * Created by virat on 6/26/16.
 */
object Auctions extends Controller {

    def room(id: Long) = Action {
        models.ch2.Shop.get(id) match {
            case Some(item) => Ok(views.html.auctionRoom(item))
            case None => NotFound
        }
    }

    def bid(id: Long) = Action {
        NotImplemented
    }

    def notifications(id: Long) = Action {
        NotImplemented
    }

}
