package com.isit322.back4appmyfavcoffee

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class ShopActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)


        // grab shop name from maps intent
        var shopName = intent.getStringExtra("selectedShop")
        var foodName = intent.getStringExtra("selectedFood")
        var shopNameTextView = findViewById<TextView>(R.id.shopName).apply {
            text = shopName
        }
        var foodNameCheckbox = findViewById<CheckBox>(R.id.itemCheckBox1).apply {
            text = foodName

//            TODO: if current foodName is already displayed in checkboxes, change to different item
//            var checkBox2 = findViewById<CheckBox>(R.id.itemCheckBox2).text
//            var checkBox3 = findViewById<CheckBox>(R.id.itemCheckBox3).text
//            var checkBox4 = findViewById<CheckBox>(R.id.itemCheckBox4).text

        }

        var shopLogo = findViewById<ImageView>(R.id.shopLogo)

        if(shopName == "Capitol Coffee Works"){
            shopLogo.setImageResource(R.drawable.capitolcoffeelogo)
        }

        if(shopName == "Cascade Coffee Works"){
            shopLogo.setImageResource(R.drawable.cascadecoffeelogo)
        }


        // toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val update_btn = findViewById<Button>(R.id.updateButton)
        update_btn.setOnClickListener {
            Toast.makeText(this, "Your selection has been updated!", Toast.LENGTH_SHORT).show()
        }


        val home_btn = findViewById<Button>(R.id.shopHomeButton)
        home_btn.setOnClickListener {
            val intent = Intent(this@ShopActivity, MainActivityFav::class.java)
            startActivity(intent)
            finish()
        }
    }
}