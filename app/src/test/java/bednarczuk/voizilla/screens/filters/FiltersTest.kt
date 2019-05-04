package bednarczuk.voizilla.screens.filters

import bednarczuk.voizilla.data.SharedPreferencesAPI
import com.nhaarman.mockitokotlin2.*
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FiltersTest {

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
    fun `show filters after view attachment`() {
        val presenter = FiltersPresenter<FiltersContract.View>()
        val view: FiltersContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)

        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        verify(view, times(1)).showFilters(any(), argThat { size > 0 })
    }


    @Test
    fun `set incorrect filter`() {
        val presenter = FiltersPresenter<FiltersContract.View>()
        val view: FiltersContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)

        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        presenter.setFilters("NOT_EXISTING_KEY", true)

        verify(view, times(1)).showIncorrectFilterSelected()
    }

    @Test
    fun `set correct filter`() {
        val presenter = FiltersPresenter<FiltersContract.View>()
        val view: FiltersContract.View = mock()
        val spHelper: SharedPreferencesAPI = mock()

        whenever(spHelper.getFilter(any())).thenReturn(true)

        presenter.attachSPHelper(spHelper)
        presenter.attachView(view)

        presenter.setFilters("OBJECT_TYPE.VEHICLE", true)

        verify(view, times(0)).showIncorrectFilterSelected()
    }
}