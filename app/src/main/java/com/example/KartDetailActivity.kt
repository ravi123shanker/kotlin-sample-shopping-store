package com.example

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_kart_detail.*

class KartDetailActivity : AppCompatActivity() {
    var kartItems=mutableListOf<MKStoreItem>();
    var adapter: MKKartDetailAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kart_detail)
        val kartMapItems= intent.extras["Data"] as HashMap<Int, MKStoreItem>
        for(entry in kartMapItems){
            kartItems?.add(entry.value);
        }
        setSupportActionBar(toolbar)
        supportActionBar?.title = "MKStore Kart"
        setupStoreData()
        btnCheckout.setOnClickListener {
            var totalPrice: Float=0f;
            for (item in kartItems)
                totalPrice+=(item.price*item.count);
            var alertDialog=AlertDialog.Builder(this);
            alertDialog.setTitle("Payment")
            alertDialog.setMessage("Are you sure, want to checkout total payment: (Rs.) $totalPrice")
            alertDialog.setPositiveButton("Yes", DialogInterface.OnClickListener({
                dialog: DialogInterface?, which: Int ->
                var intent= Intent(this@KartDetailActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }))
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener({
                dialog: DialogInterface?, which: Int ->  dialog?.dismiss()
            }))
            alertDialog.create().show();
        }
    }

    private fun setupStoreData(){
        adapter= MKKartDetailAdapter(this, kartItems);
        recyclerView.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerView.adapter=adapter
        adapter?.kartAddRemoveListner=object :OnKartAddRemoveListener{
            override fun onAddRemove(position: Int, isAdded: Boolean) {
                if(isAdded){
                    kartItems.get(position).count+=1;
                }else{
                    kartItems.get(position).count-=1;
                }
                if(kartItems.get(position).count == 0){
                    kartItems.removeAt(position)
                }
                adapter?.notifyDataSetChanged()
            }


        }

    }
}
