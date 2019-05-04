package bednarczuk.voizilla.screens.filters

import bednarczuk.voizilla.data.client.ApiClient
import bednarczuk.voizilla.data.client.api.MapApi
import bednarczuk.voizilla.data.client.model.MapFilters
import bednarczuk.voizilla.screens.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FiltersPresenter<V : FiltersContract.View> : FiltersContract.Presenter<V>, BasePresenter<V>() {
    private val client: MapApi = ApiClient().createService(MapApi::class.java)
    private lateinit var mapFilters: MapFilters

    override fun attachView(view: V?) {
        super.attachView(view)
        loadFilters()
    }

    override fun setFilters(key: String, value: Boolean) {
        if (isFilterKeyValid(key)) {
            getSPHelper()?.saveFilter(key, value)
        } else {
            getView()?.showIncorrectFilterSelected()
        }
    }

    override fun refresh() {
        loadFilters()
    }

    private fun loadFilters() {
        client.filters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { filters ->
                            mapFilters = filters
                            showFilters()
                        },
                        onError = {
                            getView()?.showNetworkFailure()
                        }
                )
    }

    private fun showFilters() {
        val checkedFilters = mapFilters.filters.map { it ->
            Pair(it.key, it.value.map { key ->
                Pair(key.key, getSPHelper()?.getFilter("${it.key}.${key.key}"))
            }.toMap())
        }.toMap()
        getView()?.showFilters(mapFilters, checkedFilters)

    }

    private fun isFilterKeyValid(key: String): Boolean {
        val keys = key.split(".")
        return (mapFilters.filters.keys.contains(keys[0]) && mapFilters.filters[keys[0]]!!.contains(keys[1]))
    }
}