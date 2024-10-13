package com.dt5gen

import com.dt5gen.authentication.JwtService
import com.dt5gen.authentication.hash
import com.dt5gen.plugins.*
import com.dt5gen.repository.DatabaseFactory
import com.dt5gen.repository.repo
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.resources.Resources
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.0.243", module = Application::module)
        .start(wait = true)
}

@kotlin.jvm.JvmOverloads


fun Application.module(testing: Boolean = false) {
    DatabaseFactory.init()
    val dataBase = repo()
    val jwtService = JwtService()
    val hashFunction = { s: String -> hash(s) }

    install(Resources)
    //io.ktor.server.resources.Resources

    fun Application.configureSerialization() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }


    configureSecurity()
    configureSerialization()
    configureRouting()
}
