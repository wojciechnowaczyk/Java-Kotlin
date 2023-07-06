

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitectures.R

class CountriesAdapter(
    // on below line we are passing variables
    // as course list and context
    private var countriesList: ArrayList<String>,
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountriesAdapter.ViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.row_layout,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return CountriesAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountriesAdapter.ViewHolder, position: Int) {
        // on below line we are setting text to our text view.
        holder.countryTV.text = countriesList.get(position)
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning the size of list.
        return countriesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our text view.
        val countryTV: TextView = itemView.findViewById(R.id.listText)
    }

}