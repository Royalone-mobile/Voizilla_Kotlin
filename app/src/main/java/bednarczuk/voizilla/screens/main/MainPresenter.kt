package bednarczuk.voizilla.screens.main

import bednarczuk.voizilla.data.client.ApiClient
import bednarczuk.voizilla.data.DataHelper
import bednarczuk.voizilla.data.client.api.MapApi
import bednarczuk.voizilla.data.client.model.MapFilters
import bednarczuk.voizilla.data.client.model.Vehicle
import bednarczuk.voizilla.screens.base.BasePresenter
import bednarczuk.voizilla.toJSON
import com.google.android.gms.maps.model.Marker
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.*

class MainPresenter<V : MainContract.View> : MainContract.Presenter<V>, BasePresenter<V>() {
    private val client: MapApi = ApiClient().createService(MapApi::class.java)
    private val dh = DataHelper()
    private lateinit var filtersObservable: Observable<MapFilters>
    private val mapReadyObservable = PublishSubject.create<Boolean>()
    private var isMapReady = false
    private lateinit var vehicles: List<Vehicle>
    private var isStartupOrFilterViewOpened = true


    init {
        getFilters()
    }

    override fun onMapReady() {
        isMapReady = true
        mapReadyObservable.onNext(true)
    }

    override fun attachView(view: V?) {
        super.attachView(view)
        if (isStartupOrFilterViewOpened) {
            getView()?.showLoading()
            subscribeFilters()
            isStartupOrFilterViewOpened = false
        }
    }

    override fun refresh() {
        getView()?.showLoading()
        getFilters()
        subscribeFilters()
    }

    override fun onFiltersClick() {
        getView()?.openFiltersActivity()
        isStartupOrFilterViewOpened = true
    }


    override fun onMarkerClick(marker: Marker) {
        val vehicle = getVehicleFromMarker(marker)
        if (vehicle != null) {
            getView()?.openVehicleActivity(vehicle.toJSON())
        }
    }

    private fun getFilters() {
        filtersObservable = client.filters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .replay(1)
                .autoConnect()
    }

    private fun subscribeFilters() {
        filtersObservable.subscribeBy(
                onNext = {
                    checkMapBeforeUpdate(it)
                },
                onError = {
                    networkError()
                }
        )
    }

    private fun checkMapBeforeUpdate(mapFilters: MapFilters) {
        if (isMapReady) {
            updateMap(mapFilters)
        } else {
            mapReadyObservable.subscribeBy {
                updateMap(mapFilters)
            }
        }
    }

    private fun updateMap(mapFilters: MapFilters) {
        val objectTypes = mapFilters.filters[dh.OBJECT_TYPE]!!.keys.toList().filter {
            getSPHelper()!!.getFilter("${dh.OBJECT_TYPE}.$it")
        }
        val vehicleType = mapFilters.filters[dh.VEHICLE_TYPE]!!.keys.toList().filter {
            getSPHelper()!!.getFilter("${dh.VEHICLE_TYPE}.$it")
        }
        val vehicleModel = mapFilters.filters[dh.VEHICLE_MODEL]!!.keys.toList().filter {
            getSPHelper()!!.getFilter("${dh.VEHICLE_MODEL}.$it")
        }
        val vehicleStatus = mapFilters.filters[dh.VEHICLE_STATUS]!!.keys.toList().filter {
            getSPHelper()!!.getFilter("${dh.VEHICLE_STATUS}.$it")
        }
        val poiCategory = mapFilters.filters[dh.POI_CATEGORY]!!.keys.toList().filter {
            getSPHelper()!!.getFilter("${dh.POI_CATEGORY}.$it")
        }
        client.findMapObjects(objectTypes, vehicleType, vehicleModel, vehicleStatus, poiCategory, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            getView()?.showVehicles(it.vehicles)
                            getView()?.showChargers(it.chargers)
                            getView()?.showParking(it.parking)
                            getView()?.showPoi(it.poi)
                            getView()?.showZones(it.zones)
                            getView()?.showFinishedLoading()
                            vehicles = it.vehicles
                        },
                        onError = {
                            networkError()
                        }
                )

    }

    private fun networkError() {
        getView()?.showFinishedLoading()
        getView()?.showNetworkFailure()
    }

    private fun getVehicleFromMarker(marker: Marker): Vehicle? {
        val tag: UUID? = marker.tag as UUID?
        if (tag != null) {
            return vehicles.firstOrNull { it.id == tag }
        }
        return null
    }


}