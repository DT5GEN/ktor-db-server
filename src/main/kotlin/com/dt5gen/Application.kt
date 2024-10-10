package com.dt5gen

import com.dt5gen.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.0.243", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
}
