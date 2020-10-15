package com.anathayna.retropet.api

import com.anathayna.retropet.model.Product
import retrofit2.http.GET
import retrofit2.Call

interface ProdutoAPI {

    @GET("/android/rest/produto/")
    fun list(): Call<List<Product>> {

    }
}