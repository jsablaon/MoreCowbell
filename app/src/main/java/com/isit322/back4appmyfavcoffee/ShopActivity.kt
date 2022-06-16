package com.isit322.back4appmyfavcoffee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        // toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val update_btn = findViewById<Button>(R.id.updateButton)
        update_btn.setOnClickListener {
            Toast.makeText(this, "Your selection has been updated!", Toast.LENGTH_SHORT).show()


        }

        val home_btn = findViewById<Button>(R.id.shopHomeButton)
        home_btn.setOnClickListener {
            val intent = Intent(this@ShopActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}