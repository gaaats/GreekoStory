package com.reciperartgen.greekostory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.greekostory.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnImgExit.setOnClickListener {
                initAlertDialogForExit()
            }
            binding.btnAboutApp.setOnClickListener {
                findNavController().navigate(R.id.action_aboutFragment_to_aboutAppFragment)
            }
            binding.btnGallery.setOnClickListener {
                findNavController().navigate(R.id.action_aboutFragment_to_galleryFragment)
            }
            binding.btnIntFacts.setOnClickListener {
                findNavController().navigate(R.id.action_aboutFragment_to_facts_frag)
            }
            binding.btnQuizzRules.setOnClickListener {
                findNavController().navigate(R.id.action_aboutFragment_to_quizRullesFragment)
            }
        } catch (e: Exception) {
            snackBarError()
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun initAlertDialogForExit() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you definitely want to log out, the current data will not be saved?")
            .setPositiveButton("Yes, Exit") { _, _ ->
                requireActivity().onBackPressed()
            }
            .setNegativeButton("Deny") { _, _ ->
            }
            .setCancelable(true)
            .create()
            .show()
    }
}