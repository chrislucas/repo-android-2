package com.xp.samplemvvmarch.feature1.repository

interface Repository<T>  {
    fun get() : T
}