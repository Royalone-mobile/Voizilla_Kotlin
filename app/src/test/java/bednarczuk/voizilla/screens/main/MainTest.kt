package bednarczuk.voizilla.screens.main

import bednarczuk.voizilla.data.SharedPreferencesAPI
import bednarczuk.voizilla.data.client.model.Vehicle
import com.nhaarman.mockitokotlin2.*
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

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
    fun `Show all object on view When view starts and map is ready faster than view attachment`() {
        val presenter = MainPresenter<MainContract.View>()
        val view: MainContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)

        presenter.onMapReady()
        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        verify(view, times(1)).showChargers(argThat { size > 0 })
        verify(view, times(1)).showParking(argThat { size > 0 })
        verify(view, times(1)).showPoi(argThat { size > 0 })
        verify(view, times(1)).showVehicles(argThat { size > 0 })
        verify(view, times(1)).showZones(argThat { size > 0 })
    }

    @Test
    fun `Show all object on view When view starts and map is ready slower than view attachment`() {
        val presenter = MainPresenter<MainContract.View>()
        val view: MainContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)

        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)
        presenter.onMapReady()

        verify(view, times(1)).showChargers(argThat { size > 0 })
        verify(view, times(1)).showParking(argThat { size > 0 })
        verify(view, times(1)).showPoi(argThat { size > 0 })
        verify(view, times(1)).showVehicles(argThat { size > 0 })
        verify(view, times(1)).showZones(argThat { size > 0 })
    }

    @Test
    fun `Show no objects When view starts and filters applied`() {
        val presenter = MainPresenter<MainContract.View>()
        val view: MainContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)
        whenever(spHelper.getFilter("OBJECT_TYPE." + any())).thenReturn(false)

        presenter.onMapReady()
        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        verify(view, times(1)).showVehicles(emptyList())
        verify(view, times(1)).showChargers(emptyList())
        verify(view, times(1)).showParking(emptyList())
        verify(view, times(1)).showPoi(emptyList())
        verify(view, times(1)).showZones(emptyList())


    }

    @Test
    fun `Show all vehicles without certain status`() {
        val presenter = MainPresenter<MainContract.View>()
        val view: MainContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)
        whenever(spHelper.getFilter("VEHICLE_STATUS." + any())).thenReturn(true)
        whenever(spHelper.getFilter("VEHICLE_STATUS.AVAILABLE")).thenReturn(false)

        presenter.onMapReady()
        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        verify(view, times(1)).showVehicles(check {
            it.forEach { vehicle ->
                assert(vehicle.status != Vehicle.StatusEnum.AVAILABLE)
            }
        })

    }

    @Test
    fun `Mimic app's onStop & onStart`() {
        val presenter = MainPresenter<MainContract.View>()
        val view: MainContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)

        presenter.onMapReady()
        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        presenter.detachView()
        presenter.detachSPHelper()
        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        verify(view, times(1)).showChargers(argThat { size > 0 })
        verify(view, times(1)).showParking(argThat { size > 0 })
        verify(view, times(1)).showPoi(argThat { size > 0 })
        verify(view, times(1)).showVehicles(argThat { size > 0 })
        verify(view, times(1)).showZones(argThat { size > 0 })

    }

    @Test
    fun `Mimic going to filters screen and back`() {
        val presenter = MainPresenter<MainContract.View>()
        val view: MainContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)

        presenter.onMapReady()
        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        presenter.onFiltersClick()

        presenter.detachView()
        presenter.detachSPHelper()
        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        verify(view, times(2)).showChargers(argThat { size > 0 })
        verify(view, times(2)).showParking(argThat { size > 0 })
        verify(view, times(2)).showPoi(argThat { size > 0 })
        verify(view, times(2)).showVehicles(argThat { size > 0 })
        verify(view, times(2)).showZones(argThat { size > 0 })

    }


    @Test
    fun `Open filters view`() {
        val presenter = MainPresenter<MainContract.View>()
        val view: MainContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)
        whenever(spHelper.getFilter("VEHICLE_STATUS." + any())).thenReturn(true)
        whenever(spHelper.getFilter("VEHICLE_STATUS.AVAILABLE")).thenReturn(false)

        presenter.onMapReady()
        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        presenter.onFiltersClick()
        verify(view, times(1)).openFiltersActivity()
    }

}