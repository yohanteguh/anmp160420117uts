package com.example.anmp160420117uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp160420117uts.model.Buku
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application):AndroidViewModel(application) {
    val bukuLD = MutableLiveData<ArrayList<Buku>>()
    val bukuLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        bukuLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://demurer-grips.000webhostapp.com/buku.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Buku>>() { }.type
                val result = Gson().fromJson<ArrayList<Buku>>(it, sType)
                bukuLD.value = result
                loadingLD.value = false

                Log.d("volleyberhasil", it)

            },
            {
                Log.d("volleyerror", it.toString())
                bukuLoadErrorLD.value = true
                loadingLD.value = false

            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }


}