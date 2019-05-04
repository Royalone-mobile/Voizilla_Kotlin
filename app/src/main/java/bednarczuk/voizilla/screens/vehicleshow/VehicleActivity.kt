package bednarczuk.voizilla.screens.vehicleshow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import bednarczuk.voizilla.R
import bednarczuk.voizilla.data.client.model.Vehicle
import com.squareup.picasso.Picasso
import io.swagger.annotations.Api
import kotlinx.android.synthetic.main.activity_vehicle.*

class VehicleActivity : AppCompatActivity(), VehicleContract.View {
    private lateinit var presenter: VehicleContract.Presenter<VehicleContract.View>
    private lateinit var vehicleJSON: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle)
        presenter = VehiclePresenter()
        vehicleJSON = intent.getStringExtra("vehicle")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        presenter.setVehicleJson(vehicleJSON)
    }

    override fun showVehicle(vehicle: Vehicle) {
        Picasso.get()
                .load("https://dev.vozilla.pl/api-client-mobile/attachments/${vehicle.picture.id}")
                .into(carPhoto)
        carName.text = "${getString(R.string.car_name)} ${vehicle.name}"
        carStatus.text = "${getString(R.string.car_status)} ${vehicle.status}"
        carRange.text = "${getString(R.string.car_range)} ${((vehicle.rangeKm * vehicle.batteryLevelPct) / vehicle.rangeKm).toInt()}km"
        carSideNumber.text = "${getString(R.string.car_side_number)} ${vehicle.sideNumber}"
        carPlateNumber.text = "${getString(R.string.car_plate_number)} ${vehicle.platesNumber}"
        carColour.text = "${getString(R.string.car_colour)} ${vehicle.color}"
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()

    }
}