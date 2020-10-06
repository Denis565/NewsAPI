package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val item = intent.getParcelableExtra<ItemOfList>("OBJECT_INTENT")

        val imgSrc = findViewById<ImageView>(R.id._imageDetail)
        val imgTitle = findViewById<TextView>(R.id._imageTitle)
        val imgContext = findViewById<TextView>(R.id._imageContext)
        val imgAuthor=findViewById<TextView>(R.id._author)
        val imgYear=findViewById<TextView>(R.id._year)
        val imgDescription=findViewById<TextView>(R.id._description)

        Picasso.get().load(item!!.photo).fit()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(imgSrc)

        imgTitle.text= item.imageTitle
        imgContext.text= item.imageContext
        imgAuthor.text=item.imageAuthor
        imgYear.text=item.imageYear
        imgDescription.text=item.imageDescription

        Gobak.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }
}


