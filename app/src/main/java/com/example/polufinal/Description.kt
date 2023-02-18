package com.example.polufinal

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class Description : AppCompatActivity() {
    private var title:String="";
    private var desc:String="";
    private var url:String="";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        GlobalScope.launch {
            title= intent.getStringExtra("title").toString()
            desc= intent.getStringExtra("desc").toString()
            url= intent.getStringExtra("url").toString()
            setTitle(title)
            var descContainer = findViewById<TextView>(R.id.description)
            var titleDown = findViewById<TextView>(R.id.titleDown)
            DownloadImageFromInternet(findViewById(R.id.logo)).execute(url)
            descContainer.text = desc
            titleDown.text = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== android.R.id.home){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}