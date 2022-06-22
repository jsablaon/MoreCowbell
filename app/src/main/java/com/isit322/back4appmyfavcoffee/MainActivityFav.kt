package com.isit322.back4appmyfavcoffee

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
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


public class MainActivityFav : AppCompatActivity() {

    lateinit var dbObj: DBOjects
    lateinit var helper: DbHelper

    @RequiresApi(api = Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_fav)

        dbObj = DBOjects()
        helper = DbHelper()

        dbObj.coffeeShops = helper.getCoffeeShops()
        dbObj.foods = helper.getFoods()
        dbObj.users = helper.getUsers()

        var btn_map_activity = findViewById(R.id.btn_map_activity) as Button
        btn_map_activity.setOnClickListener {
            val intent = Intent(this@MainActivityFav, MapsActivity::class.java)
            startActivity(intent);
        }

        //this invisible button is clicked 1 second after loading the page, and will populate the page
        var btn_bring_sample = findViewById(R.id.btn_bring_sample) as Button
        btn_bring_sample.setOnClickListener {

            var checkBool = if (dbObj.testBoolean == false) {
                println("++++++++++FALSE+++++++++++")
            } else {
                println("++++++++++TRUE+++++++++++")
            }
            //println("++++++++++${dbObj.users[0].UserName}+++++++++++")

            //this code pulls the UserName from the database.
            //We can redirect it to pull from the person who logged in once we figure that out.
            val userName = dbObj.users[0].UserName

            val welcome_user: TextView = findViewById(R.id.welcome_user) as TextView
            welcome_user.text = "Welcome, " + userName


            //This code pulls shop names from the database, not the favorites list.
            //We can easily change this to pull from the favorites with a couple more operations.

            //println("++++++++++${dbObj.coffeeShops[0].ShopName}+++++++++++")
            val shop1name = dbObj.coffeeShops[0].ShopName
            val shop2name = dbObj.coffeeShops[1].ShopName
            val shop3name = dbObj.coffeeShops[2].ShopName

            val textViewShop1: TextView = findViewById(R.id.fav_1_name) as TextView
            textViewShop1.text = shop1name

            val fav_1_flavor: TextView = findViewById(R.id.fav_1_flavor) as TextView
            fav_1_flavor.text = "A calm oasis in vibrant Capitol Hill. Kind people and exquisite coffee. Part of the Seattle Coffee Works family, a direct trade coffee roaster and importer dedicated to making coffee better in terms Quality, Equity, Sustainability and Transparency."
            val fav_1_address: TextView = findViewById(R.id.fav_1_address) as TextView
            fav_1_address.text = "909 E Pike St, Seattle, WA 98122"

            val textViewShop2: TextView = findViewById(R.id.fav_2_name) as TextView
            textViewShop2.text = shop2name

            val fav_2_flavor: TextView = findViewById(R.id.fav_2_flavor) as TextView
            fav_2_flavor.text = "At Seattle Coffee Works, we strive to make coffee better, in terms of quality, equity, sustainability and transparency. Our team of specialty coffee professionals is dedicated to helping you explore the world of fine coffee, in our cafes, or delivered to your door!"
            val fav_2_address: TextView = findViewById(R.id.fav_2_address) as TextView
            fav_2_address.text = "1130 Thomas Street, Seattle, WA 98109"

            val textViewShop3: TextView = findViewById(R.id.fav_3_name) as TextView
            textViewShop3.text = shop3name

            val fav_3_flavor: TextView = findViewById(R.id.fav_3_flavor) as TextView
            fav_3_flavor.text = "Over the years, our deep commitments to quality and community have led to the unique, delicious blends and single-origin coffees – and the loyal customer following – we enjoy today."
            val fav_3_address: TextView = findViewById(R.id.fav_3_address) as TextView
            fav_3_address.text = "1218 3rd Ave 103, Seattle, WA 98101"

            //sample code that will hide an element in case it needs to be gone
            //textViewShop3.setVisibility(View.GONE);
        }

        //sample code that will lead to the menu page

        var btn_menu_1 = findViewById(R.id.btn_menu_1) as Button
        btn_menu_1.setOnClickListener {
            val intent = Intent(this@MainActivityFav, ShopActivity::class.java)
            startActivity(intent);
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

        //this Handler method automatically clicks the button one second after the app navigates here.
        //Beware that setting the delay below 1000ms can cause errors since the page doesn't get a chance to load.
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