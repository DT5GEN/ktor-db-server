package com.dt5gen

import com.dt5gen.authentication.JwtService
import com.dt5gen.authentication.hash
import com.dt5gen.plugins.*
import com.dt5gen.repository.DatabaseFactory
import com.dt5gen.repository.repo
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.0.243", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    val dataBase = repo()
    val jwtService = JwtService()
    val hashFunction = { s: String -> hash(s) }


    configureSecurity()
    configureSerialization()
    configureRouting()
}
