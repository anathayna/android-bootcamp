package com.anathayna.retropet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anathayna.retropet.R
import com.anathayna.retropet.model.Product
import kotlinx.android.synthetic.main.card.view.*
import kotlinx.android.synthetic.main.list_products.*
import java.text.NumberFormat

class ListProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_products)
    }

    fun viewUpdate(products: List<Product>) {
        val formatter = NumberFormat.getCurrencyInstance()

        for(product in products) {
            val card = layoutInflater.inflate(R.layout.card, idContainer, false)
            card.txtNome.text = product.nomeProduto
            card.txtPreco.text = formatter.format(product.precProduto)
        }
    }
}