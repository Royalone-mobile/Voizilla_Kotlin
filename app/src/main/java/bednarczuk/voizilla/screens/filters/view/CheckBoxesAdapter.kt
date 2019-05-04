package bednarczuk.voizilla.screens.filters.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import bednarczuk.voizilla.R

class CheckBoxesAdapter(private val groupName: String, private val options: Map<String, String>, private val checked: Map<String?, Boolean?>, private val listener: CheckFilterListener) : RecyclerView.Adapter<CheckBoxesAdapter.ViewHolder>() {

    val optionsList = options.toList()


    class ViewHolder(item: View, groupName: String, listener: CheckFilterListener) : RecyclerView.ViewHolder(item) {
        val name: TextView = item.findViewById(R.id.filterName)
        val checkBox: CheckBox = item.findViewById(R.id.filterCheckBox)
        lateinit var filterName: String

        init {
            checkBox.setOnCheckedChangeListener { _, b ->
                listener.onClick("$groupName.$filterName", b)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.check_boxes_row, parent, false)
        return ViewHolder(row, groupName, listener)

    }

    override fun getItemCount() = options.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.filterName = optionsList[position].first
        holder.name.text = optionsList[position].second
        holder.checkBox.isChecked = checked[optionsList[position].first]!!
    }

}