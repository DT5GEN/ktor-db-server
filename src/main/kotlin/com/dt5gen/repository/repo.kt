package com.dt5gen.repository

import com.dt5gen.data.model.User
import com.dt5gen.data.table.UserTable
import com.dt5gen.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class repo {

    suspend fun addUser(user: User) {
        dbQuery {
            UserTable.insert { userTable ->
                userTable[email] = user.email
                userTable[hashPassword] = user.hashPassword
                userTable[name] = user.userName
            }
        }
    }

    suspend fun findByEmail(email: String) = dbQuery {
        UserTable.select { UserTable.email eq email }
            .map {rowToUser(it) }
            .singleOrNull()
    }

    private fun rowToUser(row: ResultRow?): User? {
        if (row == null) {
            return null

        }
        return User(
            email = row[UserTable.email],
            hashPassword = row[UserTable.hashPassword],
            userName = row[UserTable.name]
        )
    }
}