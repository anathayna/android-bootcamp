package com.anathayna.retropet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.anathayna.retropet.R
import com.anathayna.retropet.api.ProdutoAPI
import com.anathayna.retropet.model.Product
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
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

        //http://oficinacordova.azurewebsites.net/android/rest/produto
        val retrofit = Retrofit.Builder()
            .baseUrl("https://oficinacordova.azurewebsites.net/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ProdutoAPI::class.java)
        val call = api.list()

        val callback = object: Callback<List<Product>> {

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                shimmer.stopShimmer()
                shimmer.visibility = View.GONE
                scrollView.visibility = View.VISIBLE

                if(response.isSuccessful) {
                    response.body()?.let { viewUpdate(it) }
                } else {
                    Toast.makeText(this@ListProductsActivity, "erro no json", Toast.LENGTH_LONG).show()
                    Log.e("[ERROR_API]", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                shimmer.stopShimmer()
                shimmer.visibility = View.GONE
                scrollView.visibility = View.VISIBLE

                scrollView.visibility = View.GONE
                shimmer.visibility = View.VISIBLE
                shimmer.startShimmer()

                Toast.makeText(this@ListProductsActivity, "erro de conexão", Toast.LENGTH_LONG).show()
                Log.e("[ListProductsActivity]", "[fetchProducts]", t)
            }
        }

        call.enqueue(callback)

        scrollView.visibility = View.GONE
        shimmer.visibility = View.VISIBLE
        shimmer.startShimmer()
    }

    fun viewUpdate(products: List<Product>) {
        idContainer.removeAllViews()
        val formatter = NumberFormat.getCurrencyInstance()

        products?.let {

            val shimmer = Shimmer.AlphaHighlightBuilder()
                .setDuration(800)
                .setBaseAlpha(0.9f)
                .setHighlightAlpha(0.7f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

            val drawShimmer = ShimmerDrawable()
            drawShimmer.setShimmer(shimmer)

            for(product in it) {
                val card = layoutInflater.inflate(R.layout.card, idContainer, false)

                card.txtNome.text = product.nomeProduto
                card.txtPreco.text = formatter.format(product.precProduto)

                Picasso.get()
                    .load("https://oficinacordova.azurewebsites.net/android/rest/produto/image/" + product.idProduto)
                    .error(R.drawable.ic_image)
                    .placeholder(drawShimmer)
                    .into(idImageProduct)

                idContainer.addView(card)
            }
        }
    }
}