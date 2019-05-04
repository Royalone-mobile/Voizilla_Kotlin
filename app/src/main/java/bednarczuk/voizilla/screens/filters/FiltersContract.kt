package bednarczuk.voizilla.screens.filters

import bednarczuk.voizilla.data.SPHelper
import bednarczuk.voizilla.data.client.model.MapFilters
import bednarczuk.voizilla.screens.base.MVPPresenter
import bednarczuk.voizilla.screens.base.MVPView

interface FiltersContract {

    interface View : MVPView {
        fun showFilters(mapFilters: MapFilters, checkedFilters: Map<String?, Map<String?, Boolean?>>)
        fun showIncorrectFilterSelected()
        fun showNetworkFailure()
    }

    interface Presenter<V : View> : MVPPresenter<V> {
        fun setFilters(key: String, value: Boolean)
        fun refresh()
    }


}