package br.xplorer.driwm

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.matcher.ViewMatchers.withId

object EspressonViewHelper {


    fun performClick(id: Int): ViewInteraction = onView(withId(id)).perform(click())

}