package com.reciperartgen.greekostory.recycleeer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.greekostory.R
import com.reciperartgen.greekostory.databinding.OneSingleEventBinding
import com.reciperartgen.greekostory.eventsss.ResponseEventSingle


class EventsAdapter() :
    ListAdapter<ResponseEventSingle, EventsAdapter.EventsVievHolder>(EventsDiffUtill()) {

    private var onItemClickListener: ((event: ResponseEventSingle) -> Unit)? = null
    private var addToFavorite: ((event: ResponseEventSingle) -> Unit)? = null

    class EventsVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = OneSingleEventBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.one_single_event, parent, false).also {
                return EventsVievHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EventsVievHolder, position: Int) {

        val allImagesRandom = listOf(
            R.drawable.e1,
            R.drawable.e2,
            R.drawable.e3,
            R.drawable.e4,
            R.drawable.e5,
            R.drawable.e6,
            R.drawable.e7,
            )

        val currentItemForCreating = getItem(position)
        holder.binding.apply {
            tvEvent.text = currentItemForCreating.event
            tvYear.text = currentItemForCreating.year
            imgPicture.setImageResource(allImagesRandom.random())
            root.setOnClickListener {
//                onItemClickListener?.invoke(currentItem)
                Snackbar.make(
                    this.root,
                    "The day is: ${currentItemForCreating.day}, month is ${currentItemForCreating.month}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
//            imgAddToFavorite.setOnClickListener {
//                addToFavorite?.invoke(currentItem)
//                Log.d("favorit", "pressed ${currentItem._title}")
//                Snackbar.make(
//                    this.root,
//                    "Added to favorite ♥♥♥",
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }
        }
    }

//    fun setOnItemClickListener(listener: (holidayName: RecipesListNetItem) -> Unit) {
//        onItemClickListener = listener
//    }
//
//    fun setOnItemClickListenerHeart(listener: (holidayName: RecipesListNetItem) -> Unit) {
//        addToFavorite = listener
//    }
}