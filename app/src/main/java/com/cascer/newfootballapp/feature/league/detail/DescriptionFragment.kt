package com.cascer.newfootballapp.feature.league.detail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cascer.newfootballapp.R
import kotlinx.android.synthetic.main.fragment_description.*

class DescriptionFragment(private val desc: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_desc.movementMethod = ScrollingMovementMethod()
        tv_desc.text = desc
    }
}