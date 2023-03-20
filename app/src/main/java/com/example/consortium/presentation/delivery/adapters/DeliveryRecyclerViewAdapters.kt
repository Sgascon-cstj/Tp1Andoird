package com.example.consortium.presentation.delivery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.consortium.R
import com.example.consortium.databinding.ItemDeliveryBinding
import com.example.consortium.domain.models.Delivery

class DeliveryRecyclerViewAdapters (var deliveries: List<Delivery> = listOf()) :
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
            binding.txvFr.text = String.format("%.2f", delivery.froynyx)
            binding.txvK.text = String.format("%.2f", delivery.kreotrium)
            binding.txvVe.text = String.format("%.2f", delivery.vethynx)
            binding.txvYe.text = String.format("%.2f", delivery.yerfrium)
            binding.txvZ.text = String.format("%.2f", delivery.zuscum)
        }

    }
}