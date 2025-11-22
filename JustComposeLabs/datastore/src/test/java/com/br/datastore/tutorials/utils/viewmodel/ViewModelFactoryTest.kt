package com.br.datastore.tutorials.utils.viewmodel

import org.junit.Test

class ViewModelFactoryTest {

    @Test
    fun `Create ViewModel with default constructor`() {
        // When argsToValues is empty, verify that the ViewModel is created using its no-argument constructor. [9]
        // TODO implement test
    }

    @Test
    fun `Create ViewModel with single argument constructor`() {
        // When argsToValues contains a single argument and value, verify that the correct constructor is called and the ViewModel is instantiated. [3]
        // TODO implement test
    }

    @Test
    fun `Create ViewModel with multiple arguments constructor`() {
        // When argsToValues has multiple arguments and values, verify that the constructor with the matching signature is invoked and the ViewModel is created successfully. [4]
        // TODO implement test
    }

    @Test
    fun `Mismatched argument types`() {
        // Test for IllegalArgumentException when the type of a value in argsToValues does not match the corresponding constructor parameter type. [16]
        // TODO implement test
    }

    @Test
    fun `Non existent constructor`() {
        // Test for NoSuchMethodException when no constructor on the ViewModel class matches the arguments provided in argsToValues. [5, 6]
        // TODO implement test
    }

    @Test
    fun `Incorrect number of arguments`() {
        // Test for NoSuchMethodException when the number of arguments in argsToValues doesn't match any constructor of the ViewModel class. [16]
        // TODO implement test
    }

    @Test
    fun `Null value for primitive type argument`() {
        // Test for IllegalArgumentException when a null value is provided for a constructor parameter that is a primitive type.
        // TODO implement test
    }

    @Test
    fun `Abstract ViewModel class`() {
        // Test for InstantiationException when the modelClass provided is an abstract class. [16]
        // TODO implement test
    }

    @Test
    fun `ViewModel with a private constructor`() {
        // Test for IllegalAccessException when the matching constructor in the ViewModel is private and the factory doesn't have access. [16, 22]
        // TODO implement test
    }

    @Test
    fun `Constructor throws an exception`() {
        // Verify that if the ViewModel's constructor throws an exception, it is propagated as an InvocationTargetException. [16, 22]
        // TODO implement test
    }

    @Test
    fun `Subclass of a provided argument type`() {
        // Test that the factory correctly creates the ViewModel when a value in argsToValues is a subclass of the required constructor parameter type.
        // TODO implement test
    }

    @Test
    fun `Empty map for a ViewModel with only a parameterized constructor`() {
        // Test for NoSuchMethodException when argsToValues is empty, but the ViewModel only has parameterized constructors. [9]
        // TODO implement test
    }

    @Test
    fun `ViewModel with multiple constructors`() {
        // Test that the factory selects the correct constructor when the ViewModel has multiple constructors and the provided arguments match one of them.
        // TODO implement test
    }

    @Test
    fun `Argument order correctness`() {
        // Verify that the factory correctly maps argument types and values to the constructor parameters based on the order in the provided map. Note: Map iteration order is not guaranteed, so this might be tricky.
        // TODO implement test
    }

}