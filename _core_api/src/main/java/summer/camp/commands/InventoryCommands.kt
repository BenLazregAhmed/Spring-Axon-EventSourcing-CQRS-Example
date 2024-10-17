package summer.camp.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

class CreateInventoryCommand(
    override val id : String,
    val creationDate:Date
):BaseCommand<String>(id)