package summer.camp.dtos

import java.util.*

data class CreateProductRequestDto
    (
     var name: String = "",
     var price: Double = 0.0,
     var expirationDate: Date,
     var quantity:Int=0,
     var inventoryId :String
)
data class UpdateProductRequestDto
    (
     var id:String = "",
     var name: String = "",
     var price: Double = 0.0,
     var expirationDate: Date,
     var quantity:Int=0,
     var inventoryId :String

)
