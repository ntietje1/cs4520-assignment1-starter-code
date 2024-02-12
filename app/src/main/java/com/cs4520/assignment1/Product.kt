package com.cs4520.assignment1

data class Product(val name: String, val type: String, val expiryDate: String?, val price: Int) {
    constructor(args: List<Any?>) : this(args[0] as String, args[1] as String, args[2] as String?, args[3] as Int)
}
