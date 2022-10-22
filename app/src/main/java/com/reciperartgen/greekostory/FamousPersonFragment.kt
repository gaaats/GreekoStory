package com.reciperartgen.greekostory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.greekostory.databinding.FragmentFactsBinding
import com.reciperartgen.greekostory.databinding.FragmentFamousPersonBinding
import com.reciperartgen.greekostory.helpers.AdapterPager


class FamousPersonFragment : Fragment() {

    private var _binding: FragmentFamousPersonBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    val listImages = listOf(
        R.drawable.leonid_2,
        R.drawable.leonid_3,
        R.drawable.leonid_5,
        R.drawable.leonid_6
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFamousPersonBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnImgExit.setOnClickListener {
                initExitDialog()
            }
            val adapter = AdapterPager(listImages)
            binding.pagerPhoto.adapter = adapter
            binding.circleIndicator.setViewPager(binding.pagerPhoto)

        } catch (e: Exception) {
            makeError()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun makeError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun initExitDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you really want to EXIT, current data will not be saved?")
            .setPositiveButton("Yes, Exit") { _, _ ->
                requireActivity().onBackPressed()
            }
            .setNegativeButton("No") { _, _ ->
            }
            .setCancelable(true)
            .create()
            .show()
    }
}