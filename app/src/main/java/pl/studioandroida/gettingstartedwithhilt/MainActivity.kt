package pl.studioandroida.gettingstartedwithhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //thats a field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
        println(someClass.doSomeOtherThing())
    }
}


class SomeClass @Inject constructor(
    //thats a constructor injection
    private val someOtherClass: SomeOtherClass
) {
    fun doAThing(): String {
        return "Look i did a thing!"
    }

    fun doSomeOtherThing(): String {
        return someOtherClass.doSomeOtherThing()
    }
}

class SomeOtherClass @Inject constructor() {
    fun doSomeOtherThing(): String {
        return "Look I did some other thing!"
    }
}
