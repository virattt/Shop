package models.ch2

case class Item(id: Long, name: String, price: Double)

trait Shop {
    def list(): Iterable[Item]

    def create(name: String, price: Double): Option[Item]

    def get(id: Long): Option[Item]

    def update(id: Long, name: String, price: Double): Option[Item]

    def delete(id: Long): Boolean
}

object Shop extends Shop {
    import db.Schema.queryLanguage._
    import db.Schema.{db, items}

    override def list: Iterable[Item] = db withSession {
        implicit session => items.list
    }

    override def update(id: Long, name: String, price: Double): Option[Item] = db withSession {
        implicit session => {
            items.byId(id).update(Item(id, name, price))
            items.byId(id).firstOption
        }
    }

    override def get(id: Long): Option[Item] = db withSession {
        implicit session => {
            items.byId(id).firstOption
        }
    }

    override def delete(id: Long): Boolean = db withSession {
        implicit session => {
            items.byId(id).delete != 0
        }
    }

    override def create(name: String, price: Double): Option[Item] = db withSession {
        implicit session => {
            val id = items.returning(items.map(_.id)) += Item(0, name, price)
            items.byId(id).firstOption
        }
    }
}
