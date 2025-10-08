package com.br.justcomposelabs.tutorial.google.performance.followbestpractice

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.glance.text.Text
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    https://developer.android.com/develop/ui/compose/performance/bestpractices#use-remember
 */


@Composable
fun ContactListWrong(
    modifier: Modifier = Modifier,
    contacts: List<Contact>,
    comparator: Comparator<Contact> = Comparator { first, second -> first.name.compareTo(second.name) },
) {
    LazyColumn(modifier) {
        // DON’T DO THIS
        items(contacts.sortedWith(comparator)) { contact ->
            Text("$contact")
        }
    }
}

@Composable
fun ContactListCorrect(
    modifier: Modifier = Modifier,
    contacts: List<Contact>,
    comparator: Comparator<Contact> = Comparator { first, second -> first.name.compareTo(second.name) },
) {
    /*
        Use remember to minimize expensive calculations
     */
    val sortedContacts = remember(contacts, comparator) {
        contacts.sortedWith(comparator)
    }
    LazyColumn(modifier) {
        items(sortedContacts) { contact ->
            Text("$contact")
        }
    }
}

@Preview
@Composable
fun ContactListPreview() {
    val contacts = listOf(
        Contact(name = "John Doe"),
        Contact(name = "Jane Smith"),
        Contact(name = "Peter Jones")
    )

    JustComposeLabsTheme {
        ContactListWrong(
            modifier = Modifier.fillMaxWidth(),
            contacts = contacts
        )
    }
}