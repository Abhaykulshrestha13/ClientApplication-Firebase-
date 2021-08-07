package com.a13hay.clientapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    lateinit var quotesBtn:CardView
    lateinit var motPhotoBtn:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quotesBtn = findViewById(R.id.quotes_btn)
        motPhotoBtn = findViewById(R.id.mot_pho_btn)
        quotesBtn.setOnClickListener {
            startActivity(Intent(this,QuotesActivity::class.java))
        }
        motPhotoBtn.setOnClickListener {
            startActivity(Intent(this,ImagesActivity::class.java))
        }
    }
}