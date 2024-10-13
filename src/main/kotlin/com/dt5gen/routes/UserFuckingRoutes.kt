package com.dt5gen.routes

import com.dt5gen.authentication.JwtService
import com.dt5gen.data.model.LoginRequest
import com.dt5gen.data.model.RegisterRequest
import com.dt5gen.data.model.SimpleResponse
import com.dt5gen.data.model.User
import com.dt5gen.repository.repo
import io.ktor.http.HttpStatusCode
import io.ktor.locations.Location
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import java.lang.Exception


const val API_VERSION = "/v1"
const val USERS = "$API_VERSION/users"
const val REGISTER_REQUEST = "$USERS/register"
const val LOGIN_REQUEST = "$USERS/login"

@Location(REGISTER_REQUEST)
class UserRegisterRoute

@Location(LOGIN_REQUEST)

class UserLoginRoute


fun Route.UserFuckingRoutes(
    db: repo,
    jwtService: JwtService,
    hashFunction: (String) -> String
) {

    post<UserRegisterRoute> {
        val registerRequest = try {
            call.receive<RegisterRequest>()
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, SimpleResponse(false, "Missing Some Fields"))
            return@post
        }

        try {
            val user = User(registerRequest.email, hashFunction(registerRequest.password), registerRequest.name)
            db.addUser(user)
            call.respond(HttpStatusCode.OK, SimpleResponse(true, jwtService.generateToken(user)))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict, SimpleResponse(false, e.message ?: "Some Problem Occurred!"))
        }
    }

    post<UserLoginRoute> {
        val loginRequest = try {
            call.receive<LoginRequest>()
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, SimpleResponse(false, "Missing Some Fields"))
            return@post
        }

        try {
            val user = db.findByEmail(loginRequest.email)

            if (user == null) {
                call.respond(HttpStatusCode.BadRequest, SimpleResponse(false, "Wrong Email Id"))
            } else {

                if (user.hashPassword == hashFunction(loginRequest.password)) {
                    call.respond(HttpStatusCode.OK, SimpleResponse(true, jwtService.generateToken(user)))
                } else {
                    call.respond(HttpStatusCode.BadRequest, SimpleResponse(false, "Password Incorrect!"))
                }
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict, SimpleResponse(false, e.message ?: "Some Problem Occurred!"))
        }
    }

}
