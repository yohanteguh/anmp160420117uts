package com.example.anmp160420117uts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anmp160420117uts.model.Buku

class DetailViewModel:ViewModel() {
    val bukuLD = MutableLiveData<Buku>()

    fun fetch(){

    }
}