package bednarczuk.voizilla.screens.base

import bednarczuk.voizilla.data.SPHelper
import bednarczuk.voizilla.data.SharedPreferencesAPI

interface
MVPPresenter<V : MVPView> {

    fun attachView(view: V?)
    fun detachView()
    fun getView(): V?
    fun attachSPHelper(spHelper: SharedPreferencesAPI)
    fun detachSPHelper()
    fun getSPHelper(): SharedPreferencesAPI?
}