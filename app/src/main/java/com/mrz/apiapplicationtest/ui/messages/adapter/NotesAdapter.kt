package com.mrz.apiapplicationtest.ui.messages.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrz.apiapplicationtest.R
import com.mrz.apiapplicationtest.data.models.Note

class NotesAdapter(private val dataSet: List<Note>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle:TextView = view.findViewById(R.id.tvTitle)
        val tvDescription:TextView = view.findViewById(R.id.tvDescription)
        val tvId:TextView = view.findViewById(R.id.tvId)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_note, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitle.text = dataSet[position].title
        viewHolder.tvDescription.text = dataSet[position].description
        viewHolder.tvId.text = dataSet[position].id
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
