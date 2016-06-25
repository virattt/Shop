package models.ch2

import org.specs2.mutable.Specification
import play.api.test.{Helpers, FakeApplication, WithApplication}

/**
 * Created by virat on 6/13/16.
 */
class ShopSpec extends Specification {
    "A Shop" should {
        val shop = Shop
        "add items" in new WithApplication(FakeApplication(additionalConfiguration = Helpers.inMemoryDatabase())) {
            shop.create("Play! Framework Essentials", 42) must beSome(Item(2, "Play! Framework Essentials", 42))
        }
    }
}
