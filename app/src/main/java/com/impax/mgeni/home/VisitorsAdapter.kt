package com.impax.mgeni.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.ParseException
import androidx.recyclerview.widget.RecyclerView
import com.impax.mgeni.R
import com.impax.mgeni.models.visitorView
import java.text.SimpleDateFormat
import java.util.*

class VisitorsAdapter (private val mList: List<visitorView>) : RecyclerView.Adapter<VisitorsAdapter.ViewHolder>()
{

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val ItemsViewModel = mList[position]
        val date2 =getDateFromatedString(ItemsViewModel.checkInTime)
        val name =ItemsViewModel.name
        val index = 0
        val icon = name!!.get(index)
        // sets the text to the textview from our itemHolder class
        holder.tvIcon.text= icon.toString()
        holder.name.text = name
        holder.host.text=ItemsViewModel.hostName
        holder.reason.text=ItemsViewModel.reason
        holder.checkInTime.text=date2

    }


    fun getDateFromatedString(inputDate: String?): String?
    {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(inputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: java.text.ParseException) {
            e.printStackTrace()
        }
        if (date == null) {
            return ""
        }
        val convetDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a")
        return convetDateFormat.format(date)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int
    {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)
    {
        val tvIcon: TextView= itemView.findViewById(R.id.tvIcon)
        val name: TextView = itemView.findViewById(R.id.tvVisitorName)
        val host: TextView = itemView.findViewById(R.id.tvVisitorHost)
        val reason: TextView = itemView.findViewById(R.id.tvVisitorReason)
        val checkInTime: TextView = itemView.findViewById(R.id.tvCheckInTime)
    }
}
