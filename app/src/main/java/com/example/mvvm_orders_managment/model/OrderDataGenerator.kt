package com.example.mvvm_orders_managment.model

import kotlin.random.Random

object OrderDataGenerator {

    private val currentUsers = arrayListOf<User>()
    private val currentProducts = arrayListOf<Product>()
    private val currentOrders = arrayListOf<Order>()

    init {
        for (i in 1..100) {
            generateUsers()
            generateProducts()
        }
        for (i in 1..100) {
            generateOrders()
        }
    }

    private fun generateUsers() {
        val index = Random.nextInt(usersList.size)
        val userName = usersList[index]
        val email = "$userName@gmail.com"
        val userId = 10000 + currentUsers.size

        currentUsers.add(User(userId, userName, email))
    }

    private fun generateProducts() {
        val brandIndex = Random.nextInt(brandsList.size)
        val brandName = brandsList[brandIndex]
        val productIndex = Random.nextInt(productsList.size)
        val productName = productsList[productIndex]
        val price = Random.nextInt(1000)
        val productId = 20000 + currentProducts.size
        currentProducts.add(Product(productId, productName, brandName, price))

    }

    private fun generateOrders() {
        val user = currentUsers[Random.nextInt(currentUsers.size)]
        val product = currentProducts[Random.nextInt(productsList.size)]
        val orderID = 30000 + currentOrders.size

        currentOrders.add(Order(orderID, user, product))
    }

}