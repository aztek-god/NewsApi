package com.sergei.news.extension

import org.junit.Test

class BackPressureTest {

    @Test
    fun test(): Unit {

        val backPressure = BackPressure(1000)

        while (true) {
            backPressure.next(true, {
                println("bool = $it")
            })
        }
    }
}