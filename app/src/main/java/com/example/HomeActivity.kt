package com.example

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    var storeItems= mutableListOf<MKStoreItem>()
    var adapter: MKStoreAdapter?=null
    var txtKartValue: TextView?=null
    val kartItems=LinkedHashMap<Int, MKStoreItem>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.title="MKStore"
        setupStoreData()
    }

    private fun setupStoreData(){
        for (i in 1..20){
            storeItems?.add(MKStoreItem("Item $i", "description $i", i, 30.50f+i,0, false))
        }
        adapter= MKStoreAdapter(this, storeItems);
        recyclerView.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerView.adapter=adapter
        adapter?.itemAddRemoveListner=object :OnItemAddRemoveListener{
            override fun onAddRemove(item: MKStoreItem) {
                when(item.isSelected){
                    true ->kartItems.put(item.id, item)
                    false -> kartItems.remove(item.id)
                }
                if(kartItems.size > 0){
                    txtKartValue?.visibility=View.VISIBLE
                    txtKartValue?.text=kartItems.size.toString();
                }else{
                    txtKartValue?.visibility=View.GONE
                }
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val menuItem: MenuItem=menu!!.findItem(R.id.action_kart)
        var kartView: View=menuItem.actionView;
        var flKart=kartView.findViewById(R.id.flKart);
        txtKartValue= kartView.findViewById(R.id.txtKartValue) as TextView?
        flKart.setOnClickListener{
            calculateKartItems()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.action_kart -> calculateKartItems()
        }
        return true
    }

    private fun calculateKartItems(){
        if(kartItems.size > 0){
            var intent= Intent(this, KartDetailActivity::class.java)
            intent.putExtra("Data", kartItems)
            startActivity(intent)
        }
    }

}
