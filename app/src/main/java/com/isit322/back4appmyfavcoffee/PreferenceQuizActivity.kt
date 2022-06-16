package com.isit322.back4appmyfavcoffee

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.SaveCallback


class PreferenceQuizActivity : AppCompatActivity() {

//    var radioGroup: RadioGroup? = null
//    lateinit var radioButton: RadioButton

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_preference_quiz)

        button = findViewById(R.id.btnSubmit)

    }

    fun onRadioButtonClicked(view: View) {
        val CoffeeType = ParseObject("MyFavCoffee__FavFood")


        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.Espresso ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 1)

                    }
                R.id.Latte ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 2)

                    }
                R.id.IcedCoffee ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 3)

                    }
            }
            CoffeeType.saveInBackground(object : SaveCallback {
                override fun done(e: ParseException?) {
                    if (e == null) {
                        // Success
                    } else {
                        // Error
                    }
                }
            })



        }
    }


    fun onRadioButtonClicked2(Textview: View) {
        val CoffeeType = ParseObject("MyFavCoffee__FavFood")


        if (Textview is RadioButton) {
            // Is the button now checked?
            val checked = Textview.isChecked

            // Check which radio button was clicked
            when (Textview.getId()) {
                R.id.HotChocolate ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 4)
                    }
                R.id.whitechocolate ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 5)
                    }
                R.id.CaramelVanilla ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 6)
                    }
                R.id.NitroColdBrew ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 7)
                    }
                R.id.Cappuccino ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 8)
                    }
                R.id.Frappuccino ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 9)
                    }
            }


            CoffeeType.saveInBackground(object : SaveCallback {
                override fun done(e: ParseException?) {
                    if (e == null) {
                        // Success
                    } else {
                        // Error
                    }
                }
            })


        }
    }


    fun onRadioButtonClicked3(view3: View) {
        val CoffeeType = ParseObject("MyFavCoffee__FavFood")


        if (view3 is RadioButton) {
            // Is the button now checked?
            val checked = view3.isChecked

            // Check which radio button was clicked
            when (view3.getId()) {
                R.id.panini ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 10)
                    }
                R.id.sandwich ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 11)
                    }
                R.id.donuts ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 12)
                    }
                R.id.Bagels ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 13)
                    }
                R.id.croissant ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 14)
                    }
                R.id.cupcakes ->
                    if (checked) {
                        CoffeeType.put("userId", 1)
                        CoffeeType.put("foodId", 15)
                    }
            }
            CoffeeType.saveInBackground(object : SaveCallback {
                override fun done(e: ParseException?) {
                    if (e == null) {
                        // Success
                    } else {
                        // Error
                    }
                }
            })


        }



        button.setOnClickListener {

            Toast.makeText(this, "Thank you we received your request", Toast.LENGTH_SHORT).show()


//            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
//            radioButton = findViewById(intSelectButton)
//            Toast.makeText(baseContext, RadioButton.AUTOFILL_TYPE_TEXT, Toast.LENGTH_SHORT).show()

            Handler().postDelayed({

                val intent =
                    Intent(this@PreferenceQuizActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)

        }
    }
}