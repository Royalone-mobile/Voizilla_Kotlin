package bednarczuk.voizilla.screens.vehicleshow

import bednarczuk.voizilla.data.client.model.*
import bednarczuk.voizilla.screens.base.MVPPresenter
import bednarczuk.voizilla.screens.base.MVPView
import com.google.android.gms.maps.model.Marker

interface VehicleContract {

    interface View : MVPView {
        fun showVehicle(vehicle: Vehicle)
    }

    interface Presenter<V : View> : MVPPresenter<V> {
        fun setVehicleJson(vehicleJSON: String)
    }
}