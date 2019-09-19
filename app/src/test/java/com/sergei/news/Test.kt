package com.sergei.news

import org.junit.Test

class Test {

    @Test
    fun test(): Unit {
        for (number in 0..200) {
            if (number.isSimple()) {
                println("number = $number")
            }
        }


    }
}
fun Int.isSimple(): Boolean {
    if (this == 2 || this == 3 || this == 5 || this == 7) {
        return true
    }

    if (this % 2 != 0 && this % 3 != 0 && this % 5 != 0 && this % 7 != 0) {
        return true
    }

    return false
}

// 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199

