package com.example.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class StartPage(
    private val listener : OnGoClickListener
) : Fragment() {

    private lateinit var letsGoButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.start_page, null)

        letsGoButton = view.findViewById(R.id.go_button)

        letsGoButton.setOnClickListener {
            listener.onGoClick()
        }

        return view
    }

    interface OnGoClickListener {
        fun onGoClick()
    }
}