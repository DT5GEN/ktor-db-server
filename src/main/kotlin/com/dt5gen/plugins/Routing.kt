package com.dt5gen.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.dt5gen.authentication.JwtService
import com.dt5gen.authentication.hash
import com.dt5gen.repository.repo
import com.dt5gen.routes.UserFuckingRoutes
import io.ktor.http.ContentType

fun Application.configureRouting()   {

    val db = repo()
    val jwtService = JwtService()
    val hashFunction = { s: String -> hash(s) }

    routing {
        trace { application.log.trace(it.buildText()) }


        get("/") {
            call.respondText("HELLO Fucking WORLD!", contentType = ContentType.Text.Plain)
        }

        UserFuckingRoutes(db,jwtService,hashFunction)


        get("/notes") {
            call.respondText("Hello World!")
        }
//
//        get("/note/{id}") {
//            val id = call.parameters["id"]
//            call.respondText("$id")
//        }

//        get("/token") {
//            val email = call.request.queryParameters["email"]!!
//            val password = call.request.queryParameters["password"]!!
//            val username = call.request.queryParameters["username"]!!
//
//            val user = User(email, hashFunction(password), username)
//            call.respond(jwtService.generateToken(user))
//        }
//        get("/note") {
//            val id = call.request.queryParameters["id"]
//            call.respondText("$id")
//        }


        route("/notes") {
            route("/create") {
                // http://192.168.0.243:8080/
                post {
                    // call.respondText("Something post")
                    val body = call.receive<String>()
                    call.respond(body)
                }
            }

            delete {
                val body = call.receive<String>()
                call.respond(body)
            }
        }


    }

}