package summer.camp.events

import java.util.*

abstract class BaseEvent<T>(
    open val id : T
)
data class ProductCreatedEvent(
    override val id:String,
    val name :String,
    val price :Double,
    val expirationDate: Date,
    val quantity:Int,
    val inventoryId :String
):BaseEvent<String>(id)
data class ProductUpdatedEvent(
    override val id:String,
    val name :String,
    val price :Double,
    val expirationDate: Date,
    val quantity:Int,
    val inventoryId :String
):BaseEvent<String>(id)
data class ProductDeletedEvent(
    override val id:String,
):BaseEvent<String>(id)