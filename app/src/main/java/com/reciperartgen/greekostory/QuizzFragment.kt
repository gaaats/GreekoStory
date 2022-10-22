package com.reciperartgen.greekostory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.greekostory.databinding.FragmentQuizzBinding
import com.reciperartgen.greekostory.eventsss.MyInterceptor
import com.reciperartgen.greekostory.eventsss.ServiceEventsAPI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class QuizzFragment : Fragment() {
    private var _binding: FragmentQuizzBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    private val listImagesLogo = listOf(
        R.drawable.athena,
        R.drawable.ares,
        R.drawable.sphinx,
        R.drawable.perseus,
        R.drawable.cyclops,
        R.drawable.pegasus,
        R.drawable.hermes,
        R.drawable.hercules,
        R.drawable.hydra,
    )

    private var currentQuestion = ""
    private var currentAns = ""

    private val loading = 1
    private val correct = 2
    private val fail = 3

    private val listFakeAns = listOf(
        "Harry Potter",
        "Ukraine",
        "56262.54",
        "John",
        "Horse",
        "Perseus",
        "Kyiv",
        "Egypt",
        "Carpatian mountains",
        "England",
        "568",
        "Tanos",
        "Great Battle Bastard",
        "Arrax",
        "Carrax",
        "Deamon",
        "Peter",
        "USA",
        "Mountain",
        "Every Monday",
        "Non of this ans",
        "Population",
        "Science",
    )



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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizzBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initProgBar(loading)

        try {
            binding.btnImgExit.setOnClickListener {
                initAlertDialogForExit()
            }
            binding.btnAns1.setOnClickListener {
                sendAnsFromUser(binding.btnAns1.text.toString())
            }
            binding.btnAns2.setOnClickListener {
                sendAnsFromUser(binding.btnAns2.text.toString())
            }
            binding.btnAns3.setOnClickListener {
                sendAnsFromUser(binding.btnAns3.text.toString())
            }
            binding.btnAns4.setOnClickListener {
                sendAnsFromUser(binding.btnAns4.text.toString())
            }
//            loadList()



        } catch (e: Exception) {
            snackBarError()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun sendAnsFromUser(textAns: String) {
        if (currentAns == textAns){
            lifecycleScope.launch {
                initProgBar(correct)
            }
        } else {
            initProgBar(fail)
        }
    }

    private fun loadList() {
        lifecycleScope.launch {
            try {
                val result = api.getQuestion()
                if (result.isSuccessful) {
                    currentQuestion = result.body()!![0].question!!
                    currentAns = result.body()!![0].answer!!

                    binding.tvQuestionText.text = currentQuestion
                    binding.btnAns1.text = currentAns
                    binding.btnAns2.text = listFakeAns.random()
                    binding.btnAns3.text = listFakeAns.random()
                    binding.btnAns4.text = listFakeAns.random()
                    binding.imgLogoQuestion.setImageResource(listImagesLogo.random())

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

    private fun initProgBar(kindOfAnim:Int) {
        lifecycleScope.launch {
            binding.tvTitleQuizzz.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE
            binding.imgLogoQuestion.visibility = View.GONE
            binding.tvQuestionText.visibility = View.GONE
            binding.tvDovnText.visibility = View.GONE
            binding.btnAns1.visibility = View.GONE
            binding.btnAns2.visibility = View.GONE
            binding.btnAns3.visibility = View.GONE
            binding.btnAns4.visibility = View.GONE

            when(kindOfAnim){
                loading -> {
                    binding.lottieAnimLoading.visibility = View.VISIBLE
                }
                correct -> {
                    findNavController().navigate(R.id.action_quizzFragment_to_correctFragment)
                }
                fail -> {
                    findNavController().navigate(R.id.action_quizzFragment_to_failedFragment)
                }
            }

            loadList()

            delay(1100)

            binding.tvTitleQuizzz.visibility = View.VISIBLE
            binding.btnImgExit.visibility = View.VISIBLE
            binding.imgLogoQuestion.visibility = View.VISIBLE
            binding.tvQuestionText.visibility = View.VISIBLE
            binding.tvDovnText.visibility = View.VISIBLE
            binding.btnAns1.visibility = View.VISIBLE
            binding.btnAns2.visibility = View.VISIBLE
            binding.btnAns3.visibility = View.VISIBLE
            binding.btnAns4.visibility = View.VISIBLE

            binding.lottieAnimLoading.visibility = View.GONE

        }
    }
}