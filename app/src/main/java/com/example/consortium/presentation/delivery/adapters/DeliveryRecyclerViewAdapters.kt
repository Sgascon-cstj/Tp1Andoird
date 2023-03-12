package com.example.consortium.presentation.delivery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.consortium.R
import com.example.consortium.databinding.ItemDeliveryBinding
import com.example.consortium.domain.models.Delivery

class DeliveryRecyclerViewAdapters (val deliveries: List<Delivery> = listOf()) :
    RecyclerView.Adapter<DeliveryRecyclerViewAdapters.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeliveryRecyclerViewAdapters.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_delivery,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeliveryRecyclerViewAdapters.ViewHolder, position: Int) {
        val delivery = deliveries[position]
        holder.bind(delivery)

    }

    override fun getItemCount(): Int = deliveries.size
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemDeliveryBinding.bind(view)

        fun bind(delivery: Delivery){

        }

    }
}