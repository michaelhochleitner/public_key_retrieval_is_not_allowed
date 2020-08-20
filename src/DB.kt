package com.example

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class DBLogger {

    object Cities : Table() {
        val id = integer("id").autoIncrement() // Column<Int>
        val name = varchar("name", 50) // Column<String>

    }

    companion object {
        fun logCity() {
            Database.connect(
                "jdbc:mysql://localhost:3306/exposed?useSSL=false", driver = "com.mysql.jdbc.Driver",
                user = "exp", password = "strong_password"
            )
            transaction {
                SchemaUtils.create(Cities)
                val munichId = Cities.insert {
                    it[name] = "Munich"
                } get Cities.id
            }

        }

    }

}