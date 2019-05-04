package bednarczuk.voizilla.screens.filters.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import bednarczuk.voizilla.R
import bednarczuk.voizilla.data.SPHelper
import bednarczuk.voizilla.data.client.model.MapFilters
import bednarczuk.voizilla.screens.filters.FiltersContract
import bednarczuk.voizilla.screens.filters.FiltersPresenter
import kotlinx.android.synthetic.main.activity_filters.*

class FiltersActivity : AppCompatActivity(), FiltersContract.View {
    private lateinit var presenter: FiltersPresenter<FiltersContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filters)
        presenter = FiltersPresenter()
        presenter.attachSPHelper(SPHelper(PreferenceManager.getDefaultSharedPreferences(this)))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun showFilters(mapFilters: MapFilters, checkedFilters: Map<String?, Map<String?, Boolean?>>) {
        filtersRecyclerView.adapter = FiltersAdapter(mapFilters, checkedFilters,
                object : CheckFilterListener {
                    override fun onClick(key: String, value: Boolean) {
                        presenter.setFilters(key, value)
                    }
                })
        filtersRecyclerView.layoutManager = LinearLayoutManager(baseContext)
    }

    override fun showNetworkFailure() {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), "Network Failure", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Refresh", { presenter.refresh() })
        snackbar.show()
    }

    override fun showIncorrectFilterSelected() {
        Snackbar.make(findViewById(android.R.id.content), "Incorrect Filter Selected", Snackbar.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
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
