package com.reciperartgen.greekostory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.greekostory.databinding.FragmentFactsBinding
import com.reciperartgen.greekostory.eventsss.MyInterceptor
import com.reciperartgen.greekostory.eventsss.ResponseEventSingle
import com.reciperartgen.greekostory.eventsss.ServiceEventsAPI
import com.reciperartgen.greekostory.recycleeer.EventsAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FactsFragment : Fragment() {


    private var list = mutableListOf<ResponseEventSingle>()

    private val adapter by lazy {
        EventsAdapter()
    }


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ServiceEventsAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: ServiceEventsAPI by lazy {
        retrofit.create(ServiceEventsAPI::class.java)
    }

    private var _binding: FragmentFactsBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        try {
            try {
                initProgBar()
                addVertAndHorDividers()
                initProgBar()


            } catch (e: Exception) {
                snackBarError()
            }

            binding.btnGoBack.setOnClickListener {
                try {
                    initAlertDialog()
                } catch (e: Exception) {
                    snackBarError()
                }
            }
            binding.btnImgExit.setOnClickListener {
                initAlertDialog()
            }
        } catch (e: Exception) {
            snackBarError()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadList() {
        lifecycleScope.launch {
            try {
                val result = api.getEvents()
                if (result.isSuccessful) {
                    list = result.body()!!
                    adapter.submitList(list)
                    binding.recyclerViewEvents.adapter = adapter
                } else {
                    snackBarError()
                }
            } catch (e: Exception) {
                snackBarError()
            }
        }
    }

    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun initProgBar() {
        lifecycleScope.launch {
            binding.imgLogoEventsMain.visibility = View.GONE
            binding.btnGoBack.visibility = View.GONE
            binding.tvHelperNameEvent.visibility = View.GONE
            binding.tvHelperYear.visibility = View.GONE
            binding.cardVEvetsInside.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE

            loadList()
            delay(3000)
            binding.lottieAnimVaiting.visibility = View.VISIBLE
            binding.tvPleaseVaitLoading.visibility = View.VISIBLE

            binding.imgLogoEventsMain.visibility = View.VISIBLE
            binding.cardVEvetsInside.visibility = View.VISIBLE
            binding.tvHelperNameEvent.visibility = View.VISIBLE
            binding.tvHelperYear.visibility = View.VISIBLE
            binding.btnGoBack.visibility = View.VISIBLE
            binding.btnImgExit.visibility = View.VISIBLE
            binding.lottieAnimVaiting.visibility = View.GONE
            binding.tvPleaseVaitLoading.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun saveToClipBoard() {
        Snackbar.make(binding.root, "Saved!", Snackbar.LENGTH_LONG).show()
    }

    private fun addVertAndHorDividers() {
        binding.recyclerViewEvents.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initAlertDialog() {
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