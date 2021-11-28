package io.iamcyw.ams.adapter.incoming

import org.apache.camel.builder.RouteBuilder

class AmqpIncomeRoute : RouteBuilder() {
    override fun configure() {
        from("amqp:destinationType:destinationName")
            .bean("alarm-income", "receive")

    }
}