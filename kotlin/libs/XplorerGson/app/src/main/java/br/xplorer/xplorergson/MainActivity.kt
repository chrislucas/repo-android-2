package br.xplorer.xplorergson

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.xplorer.xplorergson.models.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.io.Reader
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getListOfPerson()
    }


    fun getListOfPerson() {

        val personsJsonInputStream = assets.open("json/persons.json")
        val stream = InputStreamReader(personsJsonInputStream)
        val arrayOfPersons = Gson().fromJson(stream, Array<Person>::class.java)

        val typeListPerson : TypeToken<MutableList<Person>> = TypeToken.get(MutableList::class.java as Class<MutableList<Person>>)
        val listOfPersons : MutableList<Person> = Gson().fromJson(stream, typeListPerson.type)

        println(arrayOfPersons)
        println(listOfPersons)

    }
}
