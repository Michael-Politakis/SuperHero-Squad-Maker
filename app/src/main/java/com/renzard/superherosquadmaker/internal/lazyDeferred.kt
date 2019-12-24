package com.renzard.superherosquadmaker.internal

import kotlinx.coroutines.*

//higher order suspending function
fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}