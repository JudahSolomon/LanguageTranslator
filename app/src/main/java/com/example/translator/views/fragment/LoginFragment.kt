package com.example.translator.views.fragment

import android.app.ActionBar
import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.translator.R
import com.example.translator.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var progressDialog :ProgressDialog
    private var _binding: FragmentLoginBinding? = null
    //viewBinding
    private val lBinding get() = _binding!!


    private lateinit var fBAuth: FirebaseAuth
    private var userName = " "
    private var password = " "



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //configuring the progressbar
        progressDialog = ProgressDialog(requireActivity())
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In")
        progressDialog.setCanceledOnTouchOutside(false)


        fBAuth = FirebaseAuth.getInstance()
        checkUser()

    }

    private fun checkUser() {
        val firebaseUser = fBAuth.currentUser
        if (firebaseUser!=null){
            val loginSuccessful = LoginFragment()
            val login: Int = requireFragmentManager().beginTransaction()
                .replace(R.id.main_layout, TranslateFragment()).commit()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container,false)

        //handling createAccount button click to create an account
        lBinding.createAccountText.setOnClickListener {
            val loginFragment = LoginFragment()
            val create: Int = requireFragmentManager().beginTransaction()
                .replace(R.id.main_layout, CreateAccountFragment()).commit()
        }

        lBinding.logInButton.setOnClickListener {
            validateData()

        }

        return lBinding.root


    }

    private fun validateData() {
        // this lines of code validates the inputted data and check if its valid
        userName = lBinding.emailEDT.text.toString().trim()
        password = lBinding.passwordEDT.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()){
            lBinding.emailEDT.error = "incorrect user name"
        }
            else if (TextUtils.isEmpty(password)){
                lBinding.passwordEDT.error = "incorrect password"
            }
        else{
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        progressDialog.show()
        fBAuth.signInWithEmailAndPassword(userName,password)
            .addOnSuccessListener {
               val firebaseuser= fBAuth.currentUser
                val email = firebaseuser!!.email
                progressDialog.dismiss()
                val snackbar = Snackbar.make(requireActivity().findViewById(R.id.main_layout),
                    "Logged in as $email", Snackbar.LENGTH_SHORT).show()

                val loginSuccessful = LoginFragment()
                val login: Int = requireFragmentManager().beginTransaction()
                    .replace(R.id.main_layout, TranslateFragment()).commit()

            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
//                val snackbar = Snackbar.make(requireActivity().findViewById(R.id.main_layout),
//                    "Log In Failed due to ${e.message}", Snackbar.LENGTH_SHORT).show()



            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


