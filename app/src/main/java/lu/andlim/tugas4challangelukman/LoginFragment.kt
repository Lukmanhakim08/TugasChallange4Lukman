package lu.andlim.tugas4challangelukman

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
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
            if (loginAuth()){
                Navigation.findNavController(view).navigate(R.id.action_to_dashboar)
            }
        }
    }

    private fun loginAuth() : Boolean{
        if(edt_username.text.isNotEmpty() && edt_password.text.isNotEmpty()){
            val sharedPreferences = requireContext().getSharedPreferences("DATAUSER", Context.MODE_PRIVATE)
            val dataUsername = sharedPreferences.getString("USERNAME", "")
            val dataPassword = sharedPreferences.getString("PASSWORD", "")

            val inputUsername = edt_username.text.toString()
            val inputPassword = edt_password.text.toString()
            return if(inputUsername != dataUsername || inputPassword != dataPassword){
                Toast.makeText(requireContext(), "email/password salah", Toast.LENGTH_SHORT).show()
                false
            }else{
                true
            }

        }else{
            Toast.makeText(requireContext(), "email dan password harus diisi", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}