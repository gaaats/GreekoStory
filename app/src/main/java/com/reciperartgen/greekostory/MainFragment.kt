package com.reciperartgen.greekostory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.greekostory.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnHeart.setOnClickListener {
                funMakeHeart()
            }
            binding.btnImgSettings.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_mainSettingsFragment)
            }
            binding.imgAbout.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
            }
            binding.imgQuizzzz.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_quizzFragment)
            }
            binding.imgFamousPersonnnnnn.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_famousPersonFragment)
            }
            binding.imgHisEvennnnnts.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_factsFragment)
            }
        } catch (e: Exception) {
            snackBarError()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun funMakeHeart() {
        Snackbar.make(binding.root, "I love you too ♥♥♥", Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }
}