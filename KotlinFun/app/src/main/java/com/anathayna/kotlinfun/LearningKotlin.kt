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

    wallet -= 20

    println(age)
    println(food)
    println(wallet)
    println(c)

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