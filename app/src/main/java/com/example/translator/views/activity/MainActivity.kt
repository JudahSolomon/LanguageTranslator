package com.example.translator.views.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.translator.R
import com.example.translator.views.fragment.WelcomeFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
 lateinit var toggle: ActionBarDrawerToggle
    private var drawerLayout: DrawerLayout? = null
    private lateinit var navigationView:NavigationView



    @SuppressLint("RestrictedApi", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Inflating the MainActivity with the welcomeFragment
        supportFragmentManager.beginTransaction().
        replace(R.id. main_layout, WelcomeFragment()).
        commit()

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)!!
        navigationView = findViewById(R.id.nav_view)

//        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close)
//        drawerLayout!!.addDrawerListener(toggle)
//        toggle.isDrawerSlideAnimationEnabled
//        toggle.syncState()
//        supportActionBar?.setDisplayShowHomeEnabled(true)



    }


}