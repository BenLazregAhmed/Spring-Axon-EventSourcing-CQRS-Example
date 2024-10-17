package summer.camp.commands

import java.util.*

class CreateOrderCommand(
    override val id : String,
    val quantity : Int,
    val customerName :String,
    val productId :String
):BaseCommand<String>(id)