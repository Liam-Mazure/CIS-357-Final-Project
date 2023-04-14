package com.example.arcore_face_filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Person(val firstName: String = "", val lastName: String = "")

class MainActivityViewModel: ViewModel() {

    private val _uid: MutableLiveData<String?> = MutableLiveData(null)
    private val _persons:MutableLiveData<List<Person>> = MutableLiveData(listOf())

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    val uid: LiveData<String?> get() = _uid
    val persons: LiveData<List<Person>> get() = _persons

    fun logout(){
        viewModelScope.launch(Dispatchers.IO) {
            auth.signOut()
            _uid.postValue(null)
        }
    }
}