package lu.andlim.tugas4challangelukman

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lu.andlim.tugas4challangelukman.Datauser.DataUser
import lu.andlim.tugas4challangelukman.Datauser.UserDatabase

class LoginFragment : Fragment() {
    // Get Database
    private var newDb : UserDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        regiter.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_to_register)
        }

        btn_login.setOnClickListener {
            if(loginAuth()){
                Navigation.findNavController(view).navigate(R.id.action_to_dashboar)
            }
        }
    }

    private fun loginAuth() : Boolean{
        if (et_email.text.isNotEmpty() && edt_password.text.toString().isNotEmpty()){

            newDb = UserDatabase.getInstance(requireContext())

            val inputEmail = et_email.text.toString()
            val inputPassw = edt_password.text.toString()

            val user = newDb?.userDao()?.cekloginuser(inputEmail, inputPassw)
            return if (user.isNullOrEmpty()){
                Toast.makeText(requireContext(), "emai dan password salah", Toast.LENGTH_SHORT).show()
                false
            }else {
                //add username of user in a shared preference after checking user availability
                val sharedPreference = requireContext().getSharedPreferences("DATAUSER", Context.MODE_PRIVATE)
                val prefs = sharedPreference.edit()
                prefs.putString("USERNAME", user)
                prefs.apply()
                true
            }
        }else{
            Toast.makeText(requireContext(), "email dan password harus diisi", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}