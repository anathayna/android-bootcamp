package com.anathayna.kotlinfun

fun main(args:Array<String>) {
    var age: Int = 24
    var weight: Int = 65
    var isLightOn: Boolean = false
    var canRide: Boolean = age > 11
    var food: String = "sushi"
    var wallet: Int = 90
    var pi: Double = 3.14
    var radius: Int = 5
    var c = pi.toInt() * radius * 2
    var candy: List<String> = listOf("cookie", "snickers", "chocolate")
    var snack: Array<String> = arrayOf("sandwiche", "cheese", "pretzel")
    var lunch: MutableList<String> = mutableListOf("salad", "beef", "egg")
    var movies: MutableList<String> = mutableListOf("totoro", "chihiro", "kiki")

    wallet -= 2
    lunch.add(3, "juice")
    movies.add(3, "high school musical")

    println(age)
    println(food)
    println(wallet)
    println(c)
    println(candy[1])
    println(snack[0])
    println(lunch[3])
    println(movies[3])

    if(isLightOn) {
        println("walk across the room")
    } else {
        println("don't move a muscle")
    }

    // age > 11
    if(canRide) {
        println("enjoy the ride")
    } else {
        println("sorry son... maybe another day")
    }

    if(weight >= 80) {
        println("slide on down!")
    } else {
        println("try out the kiddie pool")
    }



}