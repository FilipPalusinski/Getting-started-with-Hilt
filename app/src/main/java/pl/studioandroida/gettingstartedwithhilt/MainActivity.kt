package pl.studioandroida.gettingstartedwithhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

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

@AndroidEntryPoint
class MyFragment: Fragment() {

    @Inject
    lateinit var someClass: SomeClass

}

@ActivityScoped
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
