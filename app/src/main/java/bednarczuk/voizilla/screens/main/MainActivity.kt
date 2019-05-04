package bednarczuk.voizilla.screens.main

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import bednarczuk.voizilla.R
import bednarczuk.voizilla.data.SPHelper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import bednarczuk.voizilla.data.client.model.*
import bednarczuk.voizilla.bitmapDescriptorFromtext
import bednarczuk.voizilla.latlng
import bednarczuk.voizilla.screens.filters.view.FiltersActivity
import bednarczuk.voizilla.screens.vehicleshow.VehicleActivity
import com.crashlytics.android.Crashlytics
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback, MainContract.View {

    private lateinit var presenter: MainPresenter<MainContract.View>
    private lateinit var mMap: GoogleMap
    private var vehiclesMarker = mutableListOf<Marker>()
    private var parkingMarker = mutableListOf<Marker>()
    private var zonesPolygons = mutableListOf<com.google.android.gms.maps.model.Polygon>()
    private var chargersMarker = mutableListOf<Marker>()
    private var poiMarker = mutableListOf<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        presenter = MainPresenter()
        presenter.attachSPHelper(SPHelper(PreferenceManager.getDefaultSharedPreferences(this)))
    }


    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.filters -> presenter.onFiltersClick()
            R.id.crashButton -> Crashlytics.getInstance().crash()
        }
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(51.107883, 17.038538), 12.0f))
        mMap.setOnMarkerClickListener { marker ->
            presenter.onMarkerClick(marker)
            false
        }
        presenter.onMapReady()
    }

    override fun showFinishedLoading() {
        networkProgressBar.visibility = View.GONE
    }

    override fun showLoading() {
        networkProgressBar.visibility = View.VISIBLE
    }

    override fun showNetworkFailure() {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), "Network Failure", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Refresh", { presenter.refresh() })
        snackbar.show()
    }

    override fun showZones(zones: List<Zone>) {
        zonesPolygons.forEach { it.remove() }
        zonesPolygons.clear()
        zones.forEach { zone ->
            zone.excludedAreas.forEach { it ->
                val options = PolygonOptions()
                        .addAll(it.points.map { point -> point.latlng() })
                        .strokeColor(Color.RED)
                        .fillColor(ResourcesCompat.getColor(resources, R.color.redTransparent, null))
                        .strokeWidth(5.0f)
                zonesPolygons.add(mMap.addPolygon(options))
            }

            zone.allowedAreas.forEach { it ->
                val options = PolygonOptions()
                        .addAll(it.points.map { point -> point.latlng() })
                        .strokeColor(Color.BLUE)
                        .strokeWidth(5.0f)
                zonesPolygons.add(mMap.addPolygon(options))
            }

        }
    }

    override fun showParking(parkings: List<Parking>) {
        parkingMarker.forEach { it.remove() }
        parkingMarker.clear()
        parkings.forEach {
            val options = MarkerOptions()
                    .position(it.location.latlng())
                    .title(it.description)
                    .icon(this.bitmapDescriptorFromtext(R.drawable.circle_blue, "${it.availableSpacesCount}/${it.spacesCount}", Color.rgb(255, 255, 255)))
            parkingMarker.add(mMap.addMarker(options))
        }
    }

    override fun showVehicles(vehicles: List<Vehicle>) {
        vehiclesMarker.forEach { it.remove() }
        vehiclesMarker.clear()
        vehicles.forEach {
            val color = if (it.status == Vehicle.StatusEnum.AVAILABLE) BitmapDescriptorFactory.HUE_GREEN else BitmapDescriptorFactory.HUE_RED
            val options = MarkerOptions()
                    .position(it.location.latlng())
                    .title(it.name)
                    .icon(BitmapDescriptorFactory.defaultMarker(color))
            val marker = mMap.addMarker(options)
            marker.tag = it.id
            vehiclesMarker.add(marker)
        }
    }


    override fun showChargers(chargers: List<Charger>) {
        chargersMarker.forEach { it.remove() }
        chargersMarker.clear()
        chargers.forEach {
            val options = MarkerOptions()
                    .position(it.location.latlng())
                    .title(it.name)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
            chargersMarker.add(mMap.addMarker(options))
        }
    }

    override fun showPoi(poi: List<POI>) {
        poiMarker.forEach { it.remove() }
        poiMarker.clear()
        poi.forEach {
            val options = MarkerOptions()
                    .position(it.location.latlng())
                    .title(it.description)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
            poiMarker.add(mMap.addMarker(options))
        }

    }

    override fun openFiltersActivity() {
        val intent = Intent(this, FiltersActivity::class.java)
        startActivity(intent)
    }

    override fun openVehicleActivity(vehicleJSON: String) {
        val intent = Intent(this, VehicleActivity::class.java)
        intent.putExtra("vehicle", vehicleJSON)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachSPHelper()
    }
}
