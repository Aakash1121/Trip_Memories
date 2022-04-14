package com.example.tripmemories.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tripmemories.R
import com.example.tripmemories.databinding.FragmentSignUpBinding
import com.example.tripmemories.model.UserData
import com.google.android.material.snackbar.Snackbar

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SignUpFragment : Fragment() {
   // private val mFireStore = FirebaseFirestore.getInstance()
    lateinit var binding: FragmentSignUpBinding
    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = Firebase.auth
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.btnSignUp.setOnClickListener {
            registerUser(
                binding.signUpEmailId.text.toString(),
                binding.signUpPassword.text.toString()
            )

        }
        return binding.root
    }

    fun registerUser(emailId: String, password: String) {

        auth.createUserWithEmailAndPassword(emailId, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.i("USER", "createUserWithEmail:success")
                    Snackbar.make(requireView(), "Registered", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                    val user = auth.currentUser
                    val userData=UserData()
                    if (user != null) {
                        userData.id=user.uid.toString()
                    }
                    userData.firstName=binding.signUpFirstName.text.toString()
                    userData.lastName=binding.signUpLastName.text.toString()
                    userData.email=binding.signUpEmailId.text.toString()

                    db.collection("USERS")
                        .document(userData.id)
                        .set(userData, SetOptions.merge())
                        .addOnCompleteListener {

                            Log.i("USER", "User->${userData.firstName} added to firestore Successfully.")
                        }
                        .addOnFailureListener {
                            Log.i("USER", "Error occured while adding User->${userData.firstName} to firestore.")
                        }


                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("USER", "createUserWithEmail:Failed")
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailId, password)
//            .addOnCompleteListener { task ->
//
//                if (task.isSuccessful) {
//
////                    val fireabaseUser: FirebaseUser = task.result!!.user!!
////                    val registeredEmail = fireabaseUser.email!!
////                    val user = User(fireabaseUser.uid, emailId)
////                    fireStoreRegisterUser(user)
//                } else {
//                    Log.i("TAG", "createUserWithEmail:Failed")
//                }
//            }
    }

//    fun fireStoreRegisterUser(user: User) {
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        var currentUserID = ""
//        if (currentUser != null) {
//            currentUserID = currentUser.uid
//        }
//        Log.i("TAG", "UID->${currentUserID}")
//        mFireStore.collection("USERS")
//        // Document ID for users fields. Here the document it is the User ID.
//
//            .document(currentUserID)
//            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge
//            .set(user, SetOptions.merge())
//            .addOnSuccessListener {
//
//                // Here call a function of base activity for transferring the result to it.
//                Log.i("TAG", "User Created Successfully.")
//
//            }
//            .addOnFailureListener { e ->
//                Log.i("TAG", "Error writing document", e)
//            }
//    }

}