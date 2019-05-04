package bednarczuk.voizilla.screens.filters.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import bednarczuk.voizilla.R
import bednarczuk.voizilla.data.client.model.MapFilters

class FiltersAdapter(mapFilters: MapFilters, checkedFilters: Map<String?, Map<String?, Boolean?>>, val listener: CheckFilterListener) : RecyclerView.Adapter<FiltersAdapter.ViewHolder>() {

    private val items = mapFilters.filters.toList()
    private val checked = checkedFilters.toList()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        lateinit var options: Map<String, String>

        val name: TextView = item.findViewById(R.id.filterName)
        val checkboxesRecyclerView: RecyclerView = item.findViewById(R.id.filterGroup)

        init {
            checkboxesRecyclerView.layoutManager = LinearLayoutManager(item.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.filter_row, parent, false)
        return ViewHolder(row)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].first
        holder.options = items[position].second

        holder.checkboxesRecyclerView.adapter = CheckBoxesAdapter(items[position].first, items[position].second, checked[position].second, listener)
    }

}