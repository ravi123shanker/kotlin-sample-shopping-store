package com.example

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_kart_detail.view.*

class MKKartDetailAdapter(val context: Context, var storeItems: MutableList<MKStoreItem>): RecyclerView.Adapter<MKKartDetailAdapter.MKKartDetailViewHolder>() {
    var kartAddRemoveListner: OnKartAddRemoveListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MKKartDetailViewHolder {
        val view: View= LayoutInflater.from(context).inflate(R.layout.item_kart_detail, parent, false)
        return MKKartDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: MKKartDetailViewHolder?, position: Int) {
        holder?.itemView?.txtProductName?.text=storeItems.get(position).name;
        val quantity=storeItems.get(position).count
        holder?.itemView?.txtCount?.text="Quantity=  $quantity";
        holder?.itemView?.txtProductPrice?.text="Price= "+storeItems.get(position).price*quantity;
        holder?.itemView?.btnPlus?.setOnClickListener{
            kartAddRemoveListner?.onAddRemove(position, true)
        }
        holder?.itemView?.btnMinus?.setOnClickListener{
            kartAddRemoveListner?.onAddRemove(position, false)
        }

    }

    override fun getItemCount(): Int {
        return storeItems.size;
    }

    class MKKartDetailViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView)
}