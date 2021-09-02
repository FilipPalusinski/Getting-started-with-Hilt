package pl.studioandroida.gettingstartedwithhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
    }
}

class SomeClass @Inject constructor(private val someInterfaceImpl: SomeInterface) {
    fun doAThing(): String{
        return "Look I got: ${someInterfaceImpl.getAThing()}"
    }
}

class SomeInterfaceImpl @Inject constructor() : SomeInterface{
    override fun getAThing(): String {
        return "A Thing"
    }
}

interface SomeInterface {
    fun getAThing(): String
}

@InstallIn(ActivityComponent::class)
@Module
abstract class MyModule{

    @ActivityScoped
    @Binds
    abstract fun bindSomeDependency(
        someImpl: SomeInterfaceImpl
    ) : SomeInterface
}

