package bednarczuk.voizilla.screens.vehicleshow

import bednarczuk.voizilla.screens.base.BasePresenter
import bednarczuk.voizilla.vehicleFromJSONorNull

class VehiclePresenter<V : VehicleContract.View> : VehicleContract.Presenter<V>, BasePresenter<V>() {
    override fun setVehicleJson(vehicleJSON: String) {
        val vehicle = vehicleJSON.vehicleFromJSONorNull()
        if (vehicle != null) {
            getView()?.showVehicle(vehicle)
        }
    }

}