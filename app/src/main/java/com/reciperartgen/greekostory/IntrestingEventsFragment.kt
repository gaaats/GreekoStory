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
import com.reciperartgen.greekostory.databinding.FragmentInterestingFactsBinding


class IntrestingEventsFragment : Fragment() {

    private var _binding: FragmentInterestingFactsBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    private val listFactsLogo = listOf(
        R.drawable.athena,
        R.drawable.trojan,
        R.drawable.greece,
        R.drawable.horn,
        R.drawable.minotaur,
        R.drawable.apollo,
        R.drawable.atlas,
        R.drawable.aphrodite,
        R.drawable.eros,
    )

    private val listFactsText = listOf(
        "According to MSN Weather, Rhodes, Greece has about 300 sunny days each year! That’s about 3,000 hours of sunshine! Bring that sunscreen… you’re going to need it!",
        "While only 227 of them are inhabited, each island has a unique culture and landscape, ensuring that every destination will bring a brand new adventure!",
        "16 of these sites are based on cultural criteria such as the Temple of Apollo, the Acropolis of Athens, and the Medieval City of Rhodes. The last 2 (Mount Athos and Meteora), are considered to be heritage sites for their cultural and natural significance!",
        "When you picture Greece, you might think of the whitewashed buildings and blue domed churches of the cities on the Mediterranean, but the majority of Greece is actually mountains. According to the Official Greece Tourism Site, Greece is one of the most mountainous countries in Europe!",
        "Thats 9,942 miles of pristine blue coastline! 4,660 of those miles are found around the thousands of islands of the Greek archipelago! No matter which island you visit, there is sure to be a captivating view of the mediterranean! ",
        "Santorini is home to black, white and red beaches. The black beaches are thanks to Santorini’s impressive volcanic past. The beaches are comprised of black sand and pebbles that become very slippery in the water! Be careful–if you wade in too far, you might end up going for a swim!",
        "Athena is not only the Patron of Athens, but also the Goddess of warfare, strategy and wisdom. As the myth goes, Athena was chosen to be the Patron of Athens after she won the people over with the gift of an olive tree. Poseidon offered the city water, but they deemed Athena’s gift to be of higher value.",
        "Speaking of olive trees, Greece produces 2.2 million metric tons of olives each year. The only countries to beat Greece are Spain and Italy! According to True Voyagers, a travel blog, Greece has more varieties of olives than any other country in the world!",
        "One of the most well-known museums is the Acropolis Museum located in Athens. Check out Discover Greeces page on the 12 most popular archaeological museums to learn about more of them!",
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterestingFactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnImgExit.setOnClickListener {
                initAlertDialogForExit()
            }

            binding.btnNextFact.setOnClickListener {
                binding.tvSingleIntFact.text = listFactsText.random()
                binding.imgIntFacts.setImageResource(listFactsLogo.random())
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