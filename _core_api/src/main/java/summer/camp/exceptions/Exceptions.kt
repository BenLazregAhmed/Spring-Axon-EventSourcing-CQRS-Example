package summer.camp.exceptions

import java.lang.Exception

data class InventoryNotFoundException(
    override val message : String
): Exception(message)
data class ProductNotFoundException(
    override val message : String
): Exception(message)
data class NegativeQuantityException(
    override val message : String
): Exception(message)
data class InsufficientQuantityException(
    override val message : String
): Exception(message)
