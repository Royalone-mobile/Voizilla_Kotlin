package bednarczuk.voizilla.screens.main

import bednarczuk.voizilla.data.client.model.*
import bednarczuk.voizilla.screens.base.MVPPresenter
import bednarczuk.voizilla.screens.base.MVPView
import com.google.android.gms.maps.model.Marker

interface MainContract {

    interface View : MVPView {
        fun showLoading()
        fun showFinishedLoading()
        fun showNetworkFailure()
        fun showZones(zones: List<Zone>)
        fun showParking(parking: List<Parking>)
        fun showVehicles(vehicles: List<Vehicle>)
        fun showChargers(chargers: List<Charger>)
        fun showPoi(poi: List<POI>)
        fun openFiltersActivity()
        fun openVehicleActivity(vehicleJSON: String)
    }

    interface Presenter<V : View> : MVPPresenter<V> {
        fun onMapReady()
        fun onFiltersClick()
        fun onMarkerClick(marker: Marker)
        fun refresh()
    }
}