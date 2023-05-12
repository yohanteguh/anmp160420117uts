package com.example.anmp160420117uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp160420117uts.model.Buku
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class KategoriViewModel(application: Application): AndroidViewModel(application) {
    val kategoriLD = MutableLiveData<ArrayList<String>>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://demurer-grips.000webhostapp.com/buku.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Buku>>() { }.type
                val result = Gson().fromJson<ArrayList<Buku>>(it, sType)

                var resultKategori = ArrayList<String>()
                for (i in result){
                    resultKategori.add(i.kategori.toString())
                }
                var resultFiltered= ArrayList(resultKategori.distinct())
                kategoriLD.value = resultFiltered

                Log.d("volleyberhasil", resultFiltered.toString())

            },
            {
                Log.d("volleyerror", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}