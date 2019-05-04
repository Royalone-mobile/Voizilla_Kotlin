package bednarczuk.voizilla.screens.vehicleshow

import bednarczuk.voizilla.data.SharedPreferencesAPI
import bednarczuk.voizilla.data.client.model.Vehicle
import bednarczuk.voizilla.screens.main.MainContract
import bednarczuk.voizilla.screens.main.MainPresenter
import bednarczuk.voizilla.toJSON
import com.nhaarman.mockitokotlin2.*
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VehicleShowTest {

    @BeforeAll
    fun before() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ ->
            Schedulers.trampoline()
        }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterAll
    fun after() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }


    @Test
    fun `is vehicle object shown`() {
        val presenter = VehiclePresenter<VehicleContract.View>()
        val view: VehicleContract.View = mock()
        val sampleVehicle = Vehicle()

        presenter.attachView(view)
        presenter.setVehicleJson(sampleVehicle.toJSON())

        verify(view, times(1)).showVehicle(sampleVehicle)
    }
}