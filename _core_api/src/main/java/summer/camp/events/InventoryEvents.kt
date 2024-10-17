package summer.camp.events

import java.util.*

data class InventoryCreatedEvent(
    override val id:String,
    val creationDate: Date
):BaseEvent<String>(id)