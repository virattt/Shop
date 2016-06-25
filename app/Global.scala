import models.ch2.Shop
import play.api.{Application, GlobalSettings}

/**
 * Created by virat on 6/13/16.
 */
object Global extends GlobalSettings {
    override def onStart(app: Application): Unit = {
        super.onStart(app)
        if (Shop.list.isEmpty) {
            Shop.create("Play Framework Essentials", 42)
        }
    }
}
