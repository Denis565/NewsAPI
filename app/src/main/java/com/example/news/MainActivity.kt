package com.example.news

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {
    private var url =
        "http://newsapi.org/v2/top-headlines?country=us&apiKey=13c922c768eb40109037f6b5f96873b8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receivingInformation(url)
    }

    private val client = OkHttpClient()

    private fun receivingInformation(url: String) {
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonArray = JSONObject(response.body()?.string()).getJSONArray("articles")
                val lengthJsonArray = jsonArray.length()
                val arrayUrl: Array<String?> = arrayOfNulls(lengthJsonArray)
                val arrayTitle: Array<String?> = arrayOfNulls(lengthJsonArray)
                val arrayContext: Array<String?> = arrayOfNulls(lengthJsonArray)
                val arrayAuthor: Array<String?> = arrayOfNulls(lengthJsonArray)
                val arrayYear: Array<String?> = arrayOfNulls(lengthJsonArray)
                val arrayDescription: Array<String?> = arrayOfNulls(lengthJsonArray)
                val listImage = arrayListOf<ItemOfList>()

                for (i in 0 until jsonArray.length()) {

                    arrayUrl[i] = (jsonArray.getJSONObject(i).get("urlToImage")).toString()
                    val title = (jsonArray.getJSONObject(i).get("title")).toString()
                    val descriptions = (jsonArray.getJSONObject(i).get("description")).toString()
                    val context = (jsonArray.getJSONObject(i).get("content")).toString()
                    val author = (jsonArray.getJSONObject(i).get("author")).toString()
                    val year = (jsonArray.getJSONObject(i).get("publishedAt")).toString()

                    if (title == "null" || title == "")
                        arrayTitle[i] = "No title"
                    else
                        arrayTitle[i] = title

                    if (descriptions == "null" || descriptions == "")
                        arrayDescription[i] = "No description"
                    else
                        arrayDescription[i] = descriptions

                    if (context == "null" || context == "")
                        arrayContext[i] = "No content "
                    else
                        arrayContext[i] = context

                    if (author == "null" || author == "")
                        arrayAuthor[i] = "No author "
                    else
                        arrayAuthor[i] = author

                    if (year == "null" || year == "")
                        arrayYear[i] = "No year "
                    else
                        arrayYear[i] = year

                    listImage.add(
                        i,
                        ItemOfList(
                            arrayUrl[i],
                            arrayTitle[i],
                            arrayDescription[i],
                            arrayContext[i],
                            arrayAuthor[i],
                            arrayYear[i]
                        )
                    )

                }
                runOnUiThread {
                    if (response.isSuccessful) {
                        fillAdapter(listImage)
                    }
                }
            }
        })
    }

    fun fillAdapter(listImages: ArrayList<ItemOfList>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        //Делает вид ListView
        recyclerView.layoutManager = LinearLayoutManager(this)
        //Не изменяет высоту или ширину у RecyclerView.
        recyclerView.setHasFixedSize(true)
        //RecyclerView требует адаптер заполнения и управления элементами
        recyclerView.adapter = ItemAdapter(this, listImages) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("OBJECT_INTENT", it)
            startActivity(intent)
        }
    }


}




