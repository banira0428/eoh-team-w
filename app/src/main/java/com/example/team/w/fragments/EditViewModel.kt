package com.example.team.w.fragments

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap
import android.util.Log
import com.example.team.w.models.Document
import com.example.team.w.models.Event
import com.example.team.w.models.FirebaseRepository
import com.google.firebase.firestore.DocumentSnapshot

class EditViewModel : ViewModel() {

    fun getEvents(): LiveData<List<DocumentSnapshot>> {
        return FirebaseRepository.getEvents()
    }

    fun saveEvents(events: ArrayList<Document>,deleteEvents: ArrayList<Document>){
        FirebaseRepository.saveEvents(events,deleteEvents)
    }

    fun uploadImage(bitmap: Bitmap,url: String,endListener: () -> Unit){
        FirebaseRepository.uploadImage(bitmap,url,endListener)
    }

}
