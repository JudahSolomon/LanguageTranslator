package com.example.translator.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.translator.databinding.FragmentTranslateBinding
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage

class TranslateFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




//        val drawerLayout = dfindViewById(R.id.drawLayout) as DrawerLayout
//        val navigationView = translateBinding.root.findViewById(R.id.nav_view) as NavigationView
//        val toolbar = translateBinding.root.findViewById(R.id.toolbar) as Toolbar
//
//        val toggle = ActionBarDrawerToggle(requireActivity(),
//            drawerLayout,toolbar, R.string.open, R.string.close)
//
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//        toggle.isDrawerSlideAnimationEnabled = true



        // list of languages for the  (From)Spinners
        val fromLanguage: Array<String> = arrayOf(
                "English", "French", "Spanish", "Zulu", "Italian",
                "Chinese", "Hindi", "Russian", "Japanese", "Ga", "Polish",
                "Hausa", "Twi", "Ewe", "Greek", "Latin", "Zulu", "Afrikaans")

        // list of languages for the  (To)Spinners
        val toLanguage: Array<String> = arrayOf(
                "English", "French", "Spanish", "Zulu", "Italian",
                "Chinese", "Hindi", "Russian", "Japanese", "Ga", "Polish",
                "Hausa", "Twi", "Ewe", "Greek", " Latin", "Zulu", "Afrikaans")


        // Inflate the layout for this fragment
        val tBinding = FragmentTranslateBinding.inflate(inflater, container, false)
        //creating an adapter to inflate the spinner1
        val arrayAdapter1 = ArrayAdapter<String>(requireActivity(),
            android.R.layout.simple_spinner_dropdown_item, fromLanguage)
        tBinding.spinner.setAdapter(arrayAdapter1)

        //first spinner
        tBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    requireActivity(),
                    "you have selected $fromLanguage[position]",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        //setting up spinner2
        val arrayAdapter2 = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item, toLanguage
        )

        tBinding.spinner2.setAdapter(arrayAdapter2)

        tBinding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long

            ) {
                Toast.makeText(requireActivity(), "You have selected $toLanguage[position]",
                    Toast.LENGTH_SHORT)
                    .show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            private fun getLanguageCode(language:String): Int {
            var languageCode = 0

            when(language){
//                "English", "French", "Spanish", "Zulu", "Italian",
//                "Chinese", "Hindi", "Russian", "Japanese", "Ga", "Polish",
//                "Hausa", "Twi", "Ewe", "Greek", "Latin", "Zulu"

                "English" -> languageCode = FirebaseTranslateLanguage.EN
                "French" -> languageCode = FirebaseTranslateLanguage.FR
                "Spanish" -> languageCode = FirebaseTranslateLanguage.SK
                "Zulu" -> languageCode = FirebaseTranslateLanguage.ZH
                "Italian" -> languageCode = FirebaseTranslateLanguage.IT
                "Chinese" -> languageCode = FirebaseTranslateLanguage.CS
                "Hindi" -> languageCode = FirebaseTranslateLanguage.HI
                "Russian" -> languageCode = FirebaseTranslateLanguage.RU
                "Japanese" -> languageCode = FirebaseTranslateLanguage.JA
                "Ga" -> languageCode = FirebaseTranslateLanguage.GA
                "Polish" -> languageCode = FirebaseTranslateLanguage.PL
                "Hausa" -> languageCode = FirebaseTranslateLanguage.HU
                "Twi" -> languageCode = FirebaseTranslateLanguage.TA
                "Ewe" -> languageCode = FirebaseTranslateLanguage.ES
                "Greek" -> languageCode = FirebaseTranslateLanguage.GU
                "Latin" -> languageCode = FirebaseTranslateLanguage.LT
                "Zulu" -> languageCode = FirebaseTranslateLanguage.ZH
                "Afrikaans" -> languageCode = FirebaseTranslateLanguage.AF
                "Bulgarian" -> languageCode = FirebaseTranslateLanguage.BG
                "Catalan" -> languageCode = FirebaseTranslateLanguage.CA
                "Czech" -> languageCode = FirebaseTranslateLanguage.CA


            }
                return languageCode

            }

        }








        return tBinding.root
    }
}