package com.eigene.example

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService {

    fun order(itemName: String): String {

        val orderId = UUID.randomUUID().toString()

        println("$orderId - $itemName is ordered")

        //저장하는 기능 구현

        return "http://localhost:8082/orders/$orderId"
    }
}