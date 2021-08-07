package com.a13hay.clientapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a13hay.clientapplication.adapter.MyAdapter
import com.a13hay.clientapplication.adapter.MyAdapterQuotes
import com.a13hay.clientapplication.model.Model
import com.a13hay.clientapplication.model.ModelQuotes
import com.google.firebase.firestore.FirebaseFirestore

class QuotesActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapterQuotes: MyAdapterQuotes
    lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        recyclerView = findViewById(R.id.recycler_view_img)
        recyclerView.setHasFixedSize(true)
        db = FirebaseFirestore.getInstance()
        recyclerView.layoutManager = LinearLayoutManager(this)
        var list:ArrayList<Model> = ArrayList()
        var listQ:ArrayList<ModelQuotes> = ArrayList()

        myAdapterQuotes = MyAdapterQuotes(this,listQ)
        db.collection("users")
            .get()
            .addOnCompleteListener{
                if(it.isSuccessful){
                    for (i in it.result!!){
                        var r = i.data.getValue("quotes").toString()
                        Log.i("Text",r)
                        var modelQ = ModelQuotes(r)
                        listQ.add(modelQ)
                    }
                    myAdapterQuotes.notifyDataSetChanged()
                }
            }
//        reference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot){
//                for(i in dataSnapshot.children) {
//                    for (k in i.children) {
//                        model = Model(k.value.toString())
//
//                        Log.d("Mod2", i.value.toString())
//                    }
//                }
//                myAdapter.notifyDataSetChanged()
//            }
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w("TAG", "Failed to read value.", error.toException())
//            }
//        })
        recyclerView.adapter = myAdapterQuotes
    }
}