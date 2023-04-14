package com.example.arcore_face_filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginActivityViewModel: ViewModel() {
    private val _msg: MutableLiveData<String?> = MutableLiveData(null)
    private val _uid:MutableLiveData<String?> = MutableLiveData(null)
    private val auth = Firebase.auth
    val msg: LiveData<String?> get() = _msg
    val uid: LiveData<String?> get() = _uid

    init {
        _uid.postValue(auth.uid)
    }

    fun login(email:String, password:String) {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _uid.postValue(it.user?.uid)
                }
                .addOnFailureListener {
                    _msg.postValue(it.message)
                }
        }
    }
}