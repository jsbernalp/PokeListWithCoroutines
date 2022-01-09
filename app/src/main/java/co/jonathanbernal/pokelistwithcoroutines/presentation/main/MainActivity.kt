package co.jonathanbernal.pokelistwithcoroutines.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.jonathanbernal.pokelistwithcoroutines.R
import co.jonathanbernal.pokelistwithcoroutines.common.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]

    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}