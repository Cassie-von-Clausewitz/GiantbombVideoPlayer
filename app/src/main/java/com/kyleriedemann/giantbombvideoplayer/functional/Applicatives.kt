package com.kyleriedemann.giantbombvideoplayer.functional

inline infix fun <A, reified B> Array<(A) -> B>.apply(a: Array<A>) =
        Array(this.size * a.size) {
            this[it / a.size](a[it % a.size])
        }

fun curriedAddition(a: Int) = { b: Int ->
    a + b
}

fun curriedTimes(a: Int) = { b: Int ->
    a * b
}

fun tripleProduct(a: Int, b: Int, c: Int) = a * b * c

fun <A, B, C, D> curry(f: (A, B, C) -> D): (A) -> (B) -> (C) -> D = { a -> { b -> { c -> f(a, b, c) } } }