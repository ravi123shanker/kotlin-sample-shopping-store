package com.example

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_mk_store.view.*

class MKStoreAdapter(val context: Context, var storeItems: MutableList<MKStoreItem>): RecyclerView.Adapter<MKStoreAdapter.MKStoreViewHolder>() {
    var itemAddRemoveListner: OnItemAddRemoveListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MKStoreViewHolder {
        val view: View= LayoutInflater.from(context).inflate(R.layout.item_mk_store, parent, false)
        return MKStoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: MKStoreViewHolder?, position: Int) {
        holder?.itemView?.txtProductName?.text=storeItems.get(position).name;
        holder?.itemView?.txtProductDes?.text=storeItems.get(position).description;
        holder?.itemView?.txtProductPrice?.text="Price= "+storeItems.get(position).price;
        when (storeItems.get(position).isSelected){
            true ->  holder?.itemView?.btnAddRemove?.text= "Remove"
            false -> holder?.itemView?.btnAddRemove?.text= "Add"
        }
        holder?.itemView?.btnAddRemove?.setOnClickListener{
            if(storeItems.get(position).isSelected){
                storeItems.get(position).isSelected=false;
                storeItems.get(position).count=0;
            }else{
                storeItems.get(position).isSelected=true;
                storeItems.get(position).count=1;
            }
            notifyDataSetChanged()
            itemAddRemoveListner?.onAddRemove(storeItems.get(position))
        }

    }

    override fun getItemCount(): Int {
        return storeItems.size;
    }

    class MKStoreViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView)
}