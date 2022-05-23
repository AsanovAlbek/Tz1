package com.example.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class EndPage(
    private val userProgress: String,
    private val percent: String
) : Fragment () {

    lateinit var progress : TextView
    lateinit var progressByPercent : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val view : View = inflater.inflate(R.layout.end_fragment, null)

        progress = view.findViewById(R.id.progress)
        progressByPercent = view.findViewById(R.id.progress_by_percent)

        progress.text = userProgress
        progressByPercent.text = percent

        return view
    }

    fun setProgress(progress : String){
        this.progress.text = progress
    }

    fun setProgressByPercent(percent : String) {
        progressByPercent.text = percent + "%"
    }
}