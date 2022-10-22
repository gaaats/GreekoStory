package com.reciperartgen.greekostory.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reciperartgen.greekostory.R
import com.reciperartgen.greekostory.databinding.PagerBinding

class AdapterPager (val list: List<Int>): RecyclerView.Adapter<AdapterPager.VievPagerHolder>() {

    inner class VievPagerHolder (view: View): RecyclerView.ViewHolder(view){

        val binding = PagerBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VievPagerHolder {
        val viev = LayoutInflater.from(parent.context).inflate(R.layout.pager, parent, false)
        return VievPagerHolder(viev)
    }

    override fun onBindViewHolder(holder: VievPagerHolder, position: Int) {
        holder.binding.imageViev.setImageResource(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}