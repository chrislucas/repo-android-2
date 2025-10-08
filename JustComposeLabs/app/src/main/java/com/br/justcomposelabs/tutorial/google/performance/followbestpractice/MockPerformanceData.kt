package com.br.justcomposelabs.tutorial.google.performance.followbestpractice

data class Contact(val name: String) : Comparable<Contact> {
    override fun compareTo(other: Contact): Int = this.name.compareTo(other.name)
}

class Note {
    val id: Int = 0
}

class NoteRow(note: Any)

class Snack

val snack = Snack()

