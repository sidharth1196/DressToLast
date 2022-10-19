package com.hackathon.dresstolast.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.hackathon.dresstolast.model.Brand
import com.hackathon.dresstolast.model.ReviewQuestion
import com.hackathon.dresstolast.model.UserProfile
import com.hackathon.dresstolast.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataRepository: DataRepository
): ViewModel() {
    val questions = MutableLiveData<List<ReviewQuestion>>()
    val brands = MutableLiveData<List<Brand>>()
    val answers: MutableMap<String, Int> = mutableMapOf()
    private val auth: FirebaseAuth = Firebase.auth

    fun getAllQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            questions.postValue(dataRepository.getAllReviewQuestions())
        }
    }

    fun getAllBrands() {
        viewModelScope.launch(Dispatchers.IO) {
            brands.postValue(dataRepository.getAllBrands())
        }
    }

    fun calculateDurabilityIndex(value: Brand?): Int {
        var sum = 0
        answers.values.forEach {
            sum += it
        }
        Log.d("DTL", "sum = $sum")
        addToBrandDurabilityIndex(sum, value)
        return sum
    }

    private fun addToBrandDurabilityIndex(sum: Int, value: Brand?) {
        viewModelScope.launch(Dispatchers.IO) {
            value?.let {
                val index = (it.durabilityIndex * it.reviews + sum) / (it.reviews + 1)
                dataRepository.updateBrandDetailsById(it.reviews+1, index, it.id)
                Log.d("DTL", "review added")
            }
        }
    }

    fun calculateDurability(score: Double): String {
        return when {
            score < 5.1 -> "fragile"
            score < 8.1 -> "reliable"
            else -> "durable"
        }
    }

    fun isUserLoggedIn(): Boolean {
        val user = Firebase.auth.currentUser
        return user != null
    }

    fun logoutUser() {
        auth.signOut()
    }

    fun signInWithEmailPassword(email: String, password: String, goHome: () -> Unit) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Log.d("DTL", "Sign in successful")
                        goHome()
                    } else {
                        Log.d("DTL", "Sign in failed")
                    }
                }
        }
    }

    fun createUserWithEmailPassword(email: String, password: String, name: String, goHome: () -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Log.d("DTL", "Firebase User successfully created")
                    createUserRecord(name, email)
                    goHome()
                } else {
                    Log.d("DTL", "Firebase User not created")
                }
            }
    }

    private fun createUserRecord(name: String, email: String) {
        val user = UserProfile(name = name, email = email, userId = auth.currentUser?.uid.toString())
        val dbCollection = FirebaseFirestore.getInstance().collection("users")
        dbCollection
            .add(user)
            .addOnSuccessListener{ docRef ->
                dbCollection.document(docRef.id)
                    .update(hashMapOf("id" to docRef.id) as Map<String, Any>)
                    .addOnCompleteListener {
                        Log.d("DTL", "Firebase User record updated with id")
                    }
            }
    }
}