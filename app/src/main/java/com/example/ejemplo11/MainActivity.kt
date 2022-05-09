package com.example.ejemplo11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private val articulos2 = mutableListOf<Resultado>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniRecycler()
        getEmailPopular()
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getEmailPopular(){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<Respuesta> = getRetrofit().create(ApiServiceInterface::class.java).getResponse("emailed/7.json?api-key=9DnPkV6meO5MHMwNIE6Qs2M9tJilxpOU")
            val nytResponse: Respuesta? = call.body()
            val articulos: List<Resultado> = nytResponse?.results ?: emptyList()
            runOnUiThread{
                if(call.isSuccessful){
                    if(nytResponse != null){
//                        findViewById<TextView>(R.id.titleTextView2).text = articulos.first().title
                        articulos2.clear()
                        articulos2.addAll(articulos)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    fun iniRecycler(){
        adapter = Adapter(articulos2){
            var vieWeb = findViewById<WebView>(R.id.wvArt)
            vieWeb.loadUrl(it.url)
        }
        findViewById<RecyclerView>(R.id.rv1).layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        findViewById<RecyclerView>(R.id.rv1).adapter = adapter
    }


}