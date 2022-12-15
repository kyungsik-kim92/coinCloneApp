package com.application.coincloneapp.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.coincloneapp.databinding.FragmentIntro2Binding
import com.application.coincloneapp.view.SelectActivity


class IntroFragment2 : Fragment() {

    private var _binding : FragmentIntro2Binding? = null
    private val binding get() =_binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIntro2Binding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            val intent = Intent(requireContext(), SelectActivity::class.java)
            startActivity(intent)

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}