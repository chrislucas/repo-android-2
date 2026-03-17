package com.br.funwithjetpackcompose.tutorials.medium.parameterizedtestsample.utils

import java.util.regex.Pattern


/*
    https://kamiy2j.medium.com/a-smarter-way-to-test-your-code-with-parameterized-unit-tests-junit4-android-f7f81627b019
    https://www.baeldung.com/kotlin/junit-5-kotlin
    https://rionhalili.medium.com/exploring-parameterized-testing-with-junit5-kotlin-tests-and-spock-framework-9b5421dd8ff6
 */
class ValidateEmail {

    operator fun invoke(email: String): Boolean {
        return Pattern.compile("\\w{1,256}@\\w{0,64}\\.\\w{0,25}")
            .matcher(email).matches()
    }
}