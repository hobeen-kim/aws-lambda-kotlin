package com.eigene.example

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import java.util.function.Function

@Component("orderFunction")
class OrderFunction(
    private val orderService: OrderService,
    private val objectMapper: ObjectMapper
): Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    override fun apply(apiGatewayProxyRequestEvent: APIGatewayProxyRequestEvent): APIGatewayProxyResponseEvent {

        val request = objectMapper.readTree(apiGatewayProxyRequestEvent.body)

        val result = orderService.order(request.get("itemName").asText())

        return APIGatewayProxyResponseEvent()
            .withStatusCode(201)
            .withHeaders(mapOf("Location" to result))
    }
}