package com.example.mvvm_orders_managment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_orders_managment.databinding.OrderItemViewBinding
import com.example.mvvm_orders_managment.model.Order

class OrderAdapter(private val orders: ArrayList<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding =
            OrderItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)

    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.count()

    fun updateOrders(newOrders: List<Order>) {
        orders.clear()
        orders.addAll(newOrders)
        notifyDataSetChanged()
    }

    inner class OrderViewHolder(val binding: OrderItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.orderIDTextView.text = "${order.id}"
            binding.productTextView.text = "${order.product}"
            binding.userTextView.text = "${order.user}"
        }
    }


}