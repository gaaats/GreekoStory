package com.reciperartgen.greekostory.recycleeer
import androidx.recyclerview.widget.DiffUtil
import com.reciperartgen.greekostory.eventsss.ResponseEventSingle

class EventsDiffUtill: DiffUtil.ItemCallback<ResponseEventSingle>() {
    override fun areItemsTheSame(oldItem: ResponseEventSingle, newItem: ResponseEventSingle): Boolean {
        return oldItem.event == newItem.event
    }

    override fun areContentsTheSame(oldItem: ResponseEventSingle, newItem: ResponseEventSingle): Boolean {
        return oldItem == newItem
    }
}