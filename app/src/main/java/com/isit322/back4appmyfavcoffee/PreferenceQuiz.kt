package com.isit322.back4appmyfavcoffee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.snackbar.Snackbar
import com.isit322.back4appmyfavcoffee.databinding.FragmentPreferenceQuizBinding


class PreferenceQuiz : Fragment() {

    private var _binding: FragmentPreferenceQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreferenceQuizBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.fab.setOnClickListener {
            val coffeeType = binding.coffeeGroup.checkedRadioButtonId


            if (coffeeType == -1) {
                val text = "You need to choose a Coffee type"
                Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
            } else {
                val text = "Thank you successfully submitted"
                Toast.makeText(activity, text, Toast.LENGTH_LONG).show()

                }
            }


            return view
        }
    }


