package com.xp.samplemvvmarch.repository

interface Repository<T>  {
    fun get() : T
}