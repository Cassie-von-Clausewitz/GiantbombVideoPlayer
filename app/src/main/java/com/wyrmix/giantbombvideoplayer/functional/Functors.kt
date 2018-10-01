package com.wyrmix.giantbombvideoplayer.functional

fun sumThree(n: Int) = n + 3

data class Post(val id: Int, val title: String, val body: String)

fun findPost(id: Int) = Option.Some(Post(id, "Functors are fun", "Learn how to use a functor now!"))

fun getPostTitle(post: Post) = post.title

typealias IntFunction = (Int) -> Int

infix fun IntFunction.map(g: IntFunction): IntFunction {
    return { x -> this(g(x)) }
}