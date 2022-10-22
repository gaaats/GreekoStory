package com.reciperartgen.greekostory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.greekostory.databinding.FragmentGalleryBinding
import com.reciperartgen.greekostory.helpers.AdapterPager


class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnImgExit.setOnClickListener {
                initAlertDialogForExit()
            }

            val listOfImages = generateImgForPager()
            val pagerAdapter = AdapterPager(listOfImages)
            binding.pager.adapter = pagerAdapter
            binding.circleIndicatorPhotoGalery.setViewPager(binding.pager)


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

    private fun generateImgForPager(): List<Int> {
        return listOf(
            R.drawable.hercules,
            R.drawable.hydra,
            R.drawable.hermes,
            R.drawable.demeter,
            R.drawable.ares,
            R.drawable.statue,
            R.drawable.olympus,
            R.drawable.hades,

        )
    }
}