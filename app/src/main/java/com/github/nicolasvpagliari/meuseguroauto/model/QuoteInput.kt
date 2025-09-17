import com.github.nicolasvpagliari.meuseguroauto.model.CarInsurancePlan
import com.github.nicolasvpagliari.meuseguroauto.model.VehiclesTypes

data class QuoteInput(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val carInsurancePlan: CarInsurancePlan = CarInsurancePlan.STANDART,
    val vehiclesTypes: VehiclesTypes = VehiclesTypes.CAR,
    val age: Int = 30
)