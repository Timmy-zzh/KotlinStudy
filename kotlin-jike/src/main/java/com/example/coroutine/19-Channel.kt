package com.example.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() {
//    println("Channel")
//
//    channelFun01()
//}

fun main() = runBlocking {

    val channel = Channel<Int>()

    launch {
        (1..3).forEach {
            channel.send(it)
            logX("Send $it")
        }
    }

    async {
        for (i in channel){
            var receive = channel.receive()
            logX("Receive: $receive")
        }
//        var receive = channel.receive()
//        logX(receive)
//         receive = channel.receive()
//        logX(receive)
//         receive = channel.receive()
//        logX(receive)
    }

    logX("Channel end")

}



