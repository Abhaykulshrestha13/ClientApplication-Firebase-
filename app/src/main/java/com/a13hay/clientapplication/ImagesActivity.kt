package com.a13hay.clientapplication

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a13hay.clientapplication.adapter.MyAdapter
import com.a13hay.clientapplication.model.Model
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class ImagesActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var myAdapter:MyAdapter
    lateinit var model: Model
    var reference:DatabaseReference = FirebaseDatabase.getInstance().getReference("Image")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.setHasFixedSize(true);

        recyclerView.layoutManager = LinearLayoutManager(this)
        var list:ArrayList<Model> = ArrayList()
        myAdapter = MyAdapter(this,list,this)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                for(i in dataSnapshot.children) {
                    for (k in i.children) {
                        model = Model(k.value.toString())
                        list.add(model)
                        Log.d("Mod2", i.value.toString())
                    }
                }
                    myAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
        recyclerView.adapter = myAdapter
    }
}