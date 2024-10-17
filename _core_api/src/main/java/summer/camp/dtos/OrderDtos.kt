package summer.camp.dtos

import java.util.*



data class CreateOrderRequestDto
    (
    var customerName: String = "",
    var quantity:Int=0,
    var productId :String=""
)