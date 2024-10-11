package com.dt5gen.plugins

import com.dt5gen.data.model.User
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.dt5gen.authentication.JwtService

fun Application.configureRouting() {
    routing {
        get("/notes") {
            call.respondText("Hello World!")
        }

        get("/note/{id}") {
            val id = call.parameters["id"]
            call.respondText("$id")
        }

        get("/token"){
            val email = call.request.queryParameters["email"]!!
            val password = call.request.queryParameters["password"]
            val username = call.request.queryParameters["username"]!!

            val user = User(email, hashFunction(password), username)
            call.respond(jwtService.generateToken(user))
        }
        get("/note") {
            val id = call.request.queryParameters["id"]
            call.respondText("$id")
        }


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
