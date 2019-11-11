package com.xp.mockingapirequest

import android.content.Context
import android.content.Intent
import com.xp.mockingapirequest.helpers.UserRepository
import com.xp.mockingapirequest.models.User
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock


import  org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var user : User

    private val userRepositoryNewInstance = spy(UserRepository())

    private val userFactoryInstance = spy(UserRepository.UserFactory(1, "Chris Luccas"))


    /**
     * Podemos liberar o uso das anotacoes da lib Mockito de 2 formas
     * 1) usando @RunWith(MockitoJUnitRunner.class)
     * 2) Executando o metodo   MockitoAnnotations.initMocks(this)

     * */

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun mocking_list_sample_1() {
        val list = mock(MutableList::class.java) as MutableList<String>
        list.add("Chris")
        list.clear()
        list.add("Lucas")

        /**
         * Verifica se um certo comportamento ocorreu alguma vez
         * */
        verify(list).add("Chris")
        verify(list).add("Lucas")
        verify(list).clear()
    }


    @Test
    fun stub_method_sample_1() {
        val list = mock(MutableList::class.java) as MutableList<String>
        `when`(list[0]).thenReturn("Get thet first Element")

        println(list[0])
    }

    @Test
    fun mocking_user_model_with_annotation() {
        val u = doReturn(user)
            .`when`(userRepositoryNewInstance)
            //.createUser(ArgumentMatchers.any(Int::class.java), ArgumentMatchers.any(String::class.java))
            .createUser(1, "Chris Luccas")
        println(u)
    }

    @Test
    fun mocking_user_model_with_annotation_2() {
        val u = doReturn(user)
            .`when`(userFactoryInstance)
            .create()

        println(u)
    }


    @Test
    fun mocking_user_instance() {
        `when`(user.id).thenReturn(1)
        `when`(user.name).thenReturn("Chris Luccas")

        val name = user.name
        val id = user.id
        assert(name == "Chris Luccas" && id == 1) {
            "No matching"
        }
        verify(user).name
    }

    @Test
    fun  mocking_context_test() {}

}
