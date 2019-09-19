package com.sergei.news

import android.graphics.Color
import io.reactivex.Observable
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class PerfectKotlin {


    @Test
    fun test1() {

        val message = SimpleClass()
        val simpleString: String = message.simpleString
        println("message = ${simpleString.length}")

//        val set = setOf(1, 2, 3)
//
//        if (3 in set) {
//            println("true")
//        }

//        val simpleString = "string"
//
//        if("str" in simpleString) {
//            println(true)
//        }

//        val mapOf: Map<Int, String> = mapOf(1 to "str1", 2 to "str2")

//        data class User(val id: Int, val name: String)
//
//        val user = User(1, "sergei")
//
//        val user2 = user.copy(name = "dima")
//
//        println(user2)

//        val listOf = listOf(1, 2, 3)

//        println("list = ${listOf.hashCode()}")

//        listOf.toList()

//        println("list - 2 = ${listOf.toList().hashCode()}")

//        println("e = ${listOf === listOf}")

//        var list = listOf(1)
//        list += 2
//        println("list = $list")

//        var names by Delegates.observable(listOf<String>()) { _, old, new ->
//            println("Names changed from $old to $new")
//        }
//
//        names += "Fabio"

    }

    fun updateWeather(degrees: Int) {
        val (description, color) = when {
            degrees < 5 -> "cold" to Color.BLUE
            degrees < 23 -> "mild" to Color.YELLOW
            else -> "hot" to Color.RED
        }
    }

    fun aa(def: Int) {
        val (a, b) = when {
            def < 5 -> 1 to 0
            else -> 0 to 0
        }
    }

    @Test
    fun tt(): Unit {
        val primes: Sequence<Int> = sequence {
            var numbers = generateSequence(2) { it + 1 }

            while (true) {
                val prime = numbers.first()
                yield(prime)
                numbers = numbers.drop(1).filter { it % prime != 0 }
            }
        }

        print(primes.take(10).toList())

        var numbers = generateSequence(2) { it + 1 }.forEach { println("it = $it") }

        println("numbers = $numbers")
    }


    @Test
    fun test0() {
        var num = 0
        val atomicInt = AtomicInteger(0)
        val lock = Any()
        for (i in 1..1000) {
            thread {
                Thread.sleep(10)
//                synchronized(lock) {
                atomicInt.incrementAndGet()
                num += 1
//                }
                println("Thread - ${Thread.currentThread().name}, num - $num, $atomicInt")
            }
        }
        Thread.sleep(5000)
        println(num) // Very unlikely to be 1000
        // every offsetInterval a different number, like for instance 973
        println("atomicInt = $atomicInt")
    }

    @Test
    fun concatTest() {
        val concatMap = Observable.fromIterable(listOf(1, 2, 3))
            .concatMap {
                Observable.just(it)
            }
    }

    @Test
    fun test11() {
    }
}