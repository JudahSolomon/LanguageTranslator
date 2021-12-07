package com.example.translator.views.fragment

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.translator.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class CreateAccountFragment : Fragment() {
    //viewBinding
    private var _binding: FragmentCreateAccountBinding? = null
    private val cBinding get() = _binding!!
    private lateinit var progressDialog: ProgressDialog

    private lateinit var cBAuth: FirebaseAuth
    private var email = " "
    private var password = " "
    private var confirm_Pass = " "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressDialog = ProgressDialog(requireActivity())
        progressDialog.show()
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Creating Account...")
        progressDialog.setCanceledOnTouchOutside(false)

        // creaing the FirebaseAuth instance
        cBAuth = FirebaseAuth.getInstance()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        progressDialog.dismiss()
        // Inflate the layout for this fragment
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)


        // this button takes the user back to the Login fragment


        //setting an onClickListener event for the createAccount Button
        cBinding.createAccountButton.setOnClickListener {
            validateData()
        }

        return cBinding.root

    }

    private fun validateData() {
        email = cBinding.emailED.text.toString().trim()
        password = cBinding.passwordED.text.toString().trim()
        confirm_Pass = cBinding.confirmPasswordED.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            cBinding.emailED.error = "invalid email"
        } else if (password.length < 6) {
            cBinding.passwordED.error = "password must be at least 6 characters"
        } else if (TextUtils.isEmpty(password)) {
            cBinding.passwordED.error = "Password is empty"
        } else if (password != confirm_Pass) {
            cBinding.confirmPasswordED.error = "passwords do not match"
        }

        // do this when the user uses valid email and password

        else {
            firebaseSignup()
        }
    }

    private fun firebaseSignup() {
        progressDialog.show()
        cBAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = cBAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireActivity(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = cBAuth.currentUser
        if (currentUser != null) {
            reload();
        }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }
}



