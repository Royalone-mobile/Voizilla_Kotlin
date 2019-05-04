package bednarczuk.voizilla.screens.base

import bednarczuk.voizilla.data.SPHelper
import bednarczuk.voizilla.data.SharedPreferencesAPI

abstract class BasePresenter<V : MVPView> : MVPPresenter<V> {
    private var v: V? = null
    private val isViewAttached get() = v != null
    private var spHelper: SharedPreferencesAPI? = null


    override fun attachView(view: V?) {
        v = view
    }

    override fun getView() = v

    override fun detachView() {
        v = null
    }

    override fun attachSPHelper(spHelper: SharedPreferencesAPI) {
        this.spHelper = spHelper
    }

    override fun detachSPHelper() {
        spHelper = null
    }

    override fun getSPHelper() = spHelper
}