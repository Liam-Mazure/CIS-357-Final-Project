package com.example.arcore_face_filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupActivityViewModel: ViewModel() {

    private val _uid: MutableLiveData<String?> = MutableLiveData(null)
    private val _msg: MutableLiveData<String?> = MutableLiveData(null)
    val uid: LiveData<String?> get() = _uid
    val msg: LiveData<String?> get() = _msg
    private val auth = Firebase.auth

    fun newAccount (email:String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _uid.postValue(it.user?.uid)
                }
                .addOnFailureListener {
                    _msg.postValue(it.message)
                }
        }
    }
}