package com.example.translator.views.fragment
import android.app.ProgressDialog.show
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.translator.R
import com.example.translator.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private lateinit var welcomeBinding: FragmentWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)

        val root : WelcomeFragment = WelcomeFragment()



    }

    override fun onCreateView(inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View? {

        val window = requireActivity().window.apply {

//
//            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);


        };

        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_welcome, container, false)
        val start: TextView = root.findViewById(R.id.get_started)
        //setting up an onClickListener event to navigate to the TranslateFragment activity
        start.setOnClickListener {
            val welcomeFragment = WelcomeFragment()
            val signIn : FragmentTransaction = requireFragmentManager().beginTransaction()
            signIn.replace(R.id.main_layout, LoginFragment()).commit()

        }
        return root
    }


}