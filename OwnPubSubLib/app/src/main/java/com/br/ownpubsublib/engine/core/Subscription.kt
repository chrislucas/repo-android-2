package com.br.ownpubsublib.engine.core

import java.util.concurrent.ConcurrentHashMap

object Subscription {

    val subscribers: MutableMap<String, MutableList<ConsumerEvent>> = ConcurrentHashMap()

    fun subscribe(topic: String, subscriber: Subscriber) {}

    fun dispatch(topic: String, data: Map<String, EventData<*>>) {

    }
}