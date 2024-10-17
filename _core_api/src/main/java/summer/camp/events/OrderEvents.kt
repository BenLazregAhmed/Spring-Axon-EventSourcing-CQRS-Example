package summer.camp.events

import java.util.*


data class OrderCreatedEvent(
    override val id:String,
    val customerName :String,
    val quantity:Int,
    val productId :String
):BaseEvent<String>(id)