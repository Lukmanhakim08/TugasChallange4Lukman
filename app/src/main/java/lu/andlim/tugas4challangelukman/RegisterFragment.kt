package lu.andlim.tugas4challangelukman

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_register.setOnClickListener {
            if (edt_nomor.text.isNotEmpty() &&
                    edt_username.text.isNotEmpty() &&
                    edt_email.text.isNotEmpty() &&
                    edt_email.text.isNotEmpty() &&
                    edt_password.text.isNotEmpty() &&
                    edt_konfirmasi.text.isNotEmpty()){
                if (edt_password.text.toString() != edt_konfirmasi.text.toString()){
                    Toast.makeText(requireContext(), "Password dan passsword harus sama", Toast.LENGTH_SHORT).show()
                }else{
                    inputUserData()
                    Navigation.findNavController(view).navigate(R.id.action_to_login)
                    Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(requireContext(), "Semua data belum terisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputUserData(){
        val datnomor = edt_nomor.text.toString()
        val datausername = edt_username.text.toString()
        val dataEmail = edt_email.text.toString()
        val datapassword = edt_password.text.toString()
        val datakomfirmasi = edt_konfirmasi.text.toString()

        val sharedPreference = requireContext().getSharedPreferences("DATAUSER", Context.MODE_PRIVATE)
        val pref = sharedPreference.edit()
        pref.putString("NOMOR", datnomor)
        pref.putString("USERNAME", datausername)
        pref.putString("EMAIL", dataEmail)
        pref.putString("PASSWORD", datapassword)
        pref.putString("KONFIRMASI", datakomfirmasi)
        pref.apply()
    }
}