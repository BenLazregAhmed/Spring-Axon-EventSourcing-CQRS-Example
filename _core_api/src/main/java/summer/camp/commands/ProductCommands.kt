package summer.camp.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.Date

abstract class BaseCommand<T>(
    @TargetAggregateIdentifier
    open val id : T
)

class CreateProductCommand(
    override val id : String,
    val name :String,
    val price :Double,
    val expirationDate:Date,
    val quantity : Int,
    val inventoryId :String
):BaseCommand<String>(id)
class UpdateProductCommand(
    override val id : String,
    val name :String,
    val price :Double,
    val expirationDate:Date,
    val quantity : Int,
    val inventoryId :String
):BaseCommand<String>(id)

class DeleteProductCommand(
    override val id : String,
):BaseCommand<String>(id)