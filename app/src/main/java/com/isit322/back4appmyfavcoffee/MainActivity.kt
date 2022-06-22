package com.isit322.back4appmyfavcoffee

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


public class MainActivity : AppCompatActivity() {

    lateinit var dbObj: DBOjects
    lateinit var helper: DbHelper

    @RequiresApi(api = Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        dbObj = DBOjects()
        helper = DbHelper()

        dbObj.users = helper.getUsers()

        var btn_map_activity = findViewById(R.id.btn_map_activity) as Button
        btn_map_activity.setOnClickListener {
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(intent);
        }

        //this invisible button is clicked 1 second after loading the page, and will populate the page
        var btn_bring_sample = findViewById(R.id.btn_bring_sample) as Button
        btn_bring_sample.setOnClickListener {
            //        val userName = "PLACEHOLDER"
        val userName = dbObj.users[0].UserName

        val welcome_user: TextView = findViewById(R.id.welcome_user) as TextView
        welcome_user.text = "Welcome, " + userName
        }


        // toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // configure the toolbar to include an UP button and display which screen you've navigated to. p.320
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView = findViewById<NavigationView>(R.id.nav_view) // drawer
        NavigationUI.setupWithNavController(
            navView,
            navController
        ) // link drawer to navigation controller
        val drawer =
            findViewById<DrawerLayout>(R.id.drawer_layout) // gets a ref to the DrawerLayout
        val builder = AppBarConfiguration.Builder(navController.graph)
        // this line means the toolbar will display drawer icon. icon will be on all screens, no UP button
        builder.setOpenableLayout(drawer)
        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)

        Handler().postDelayed(Runnable { btn_bring_sample.performClick() }, 1000)
    }


    // add any menu items to the toolbar (in this case the Fave, Home, Map items)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // navigate to a destination when an item's clicked on the toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
}