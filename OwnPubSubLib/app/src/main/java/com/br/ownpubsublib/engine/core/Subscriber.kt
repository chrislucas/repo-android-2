package com.br.ownpubsublib.engine.core

interface Subscriber {

    fun onEvent(data: Map<String, EventData<*>>)
}