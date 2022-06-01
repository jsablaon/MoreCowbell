package com.isit322.back4appmyfavcoffee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
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
import com.parse.Parse
import com.parse.ParseObject


public class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        setContentView(R.layout.activity_main)

        var btn_map_activity = findViewById(R.id.btn_map_activity) as Button
        btn_map_activity.setOnClickListener {
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(intent);
        }

        val firstObject = ParseObject("FirstClass")
        firstObject.put("message", "Hey ! First message from android. Parse is now connected")
        firstObject.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
            } else {
                Log.d("MainActivity", "Object saved.")
            }
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