package com.example.anmp160420117uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.anmp160420117uts.R

class AkunFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

    var name = String()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLoginAkun = view.findViewById<Button>(R.id.btnLoginAkun)
        val txtWarning = view.findViewById<TextView>(R.id.txtWarning)

        if (arguments != null) {
            name = AkunFragmentArgs.fromBundle(requireArguments()).name.toString()
            if (name != "user"){
                if (name.isNotEmpty()) {
                    txtWarning.text = "Welcome $name"
                    btnLoginAkun.visibility = View.GONE
                }
            }
        }


        btnLoginAkun.setOnClickListener {
            val action = AkunFragmentDirections.actionItemAkunToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}