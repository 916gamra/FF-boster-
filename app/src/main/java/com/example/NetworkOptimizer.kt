package com.example

import java.net.Socket

object NetworkOptimizer {
    fun applyTcpOptimizations(socket: Socket) {
        socket.tcpNoDelay = true          
        socket.keepAlive = true
        socket.soTimeout = 0                
    }
}
