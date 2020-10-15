package com.anathayna.retropet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.anathayna.retropet.R
import com.anathayna.retropet.api.ProdutoAPI
import com.anathayna.retropet.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card.*
import kotlinx.android.synthetic.main.card.view.*
import kotlinx.android.synthetic.main.list_products.*
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.text.NumberFormat
import java.util.concurrent.TimeUnit

class ListProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_products)
    }

    override fun onResume() {
        super.onResume()
        fetchProducts()
    }

    fun fetchProducts() {
        val httpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://oficinacordova.azurewebsites.net/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ProdutoAPI::class.java)
        val call = api.list()

        val callback = object: Callback<List<Product>> {

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if(response.isSuccessful) {
                    response.body()?.let { viewUpdate(it) }
                } else {
                    Toast.makeText(this@ListProductsActivity, "erro no json", Toast.LENGTH_LONG).show()
                    Log.e("[ERROR_API]", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@ListProductsActivity, "erro de conexão", Toast.LENGTH_LONG).show()
                Log.e("[ListProductsActivity]", "[fetchProducts]", t)
            }
        }

        call.enqueue(callback)
    }

    fun viewUpdate(products: List<Product>) {
        val formatter = NumberFormat.getCurrencyInstance()
        idContainer.removeAllViews()

        products?.let {
            for(product in products) {
                val card = layoutInflater.inflate(R.layout.card, idContainer, false)

                card.txtNome.text = product.nomeProduto
                card.txtPreco.text = formatter.format(product.precProduto)

                Picasso.get()
                    .load("https://oficinacordova.azurewebsites.net/android/rest/produto/image/" + product.idProduto)
                    .into(imageView)

                idContainer.addView(card)
            }
        }
    }
}