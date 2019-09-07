package com.sergei.news

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val user = User(1, "Sergei")

        println("canonicalName = ${user::class.java.canonicalName}")

        println("canonicalName = ${User::class.java.canonicalName}")
    }
}

data class User(val id: Int, val name: String)
