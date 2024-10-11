package com.dt5gen.data.table

import org.jetbrains.exposed.sql.Table

object UserTable : Table() {
    val email = varchar("email", 50)
    val name = varchar("name", 50)
    val hashPassword = varchar("hashPassword", 255)

    override val primaryKey: PrimaryKey = PrimaryKey(email)

}