package com.anathayna.retropet.model

data class Product (
    val idProduto: Int,
    val idCategoria: Int,
    val precProduto: Float,
    val descProduto: String,
    val ativoProduto: Boolean,
    val nomeProduto: String,
    val qtdMinEstoque: Int,
    val descontoPromocao: Float
)