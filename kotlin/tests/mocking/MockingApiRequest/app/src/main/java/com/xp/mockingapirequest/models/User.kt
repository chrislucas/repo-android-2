package com.xp.mockingapirequest.models

data class User(val id: Int, val name: String) {
    override fun toString(): String = "ID: $id, Nmae: $name"
}