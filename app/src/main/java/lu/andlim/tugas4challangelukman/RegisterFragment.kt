package lu.andlim.tugas4challangelukman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import lu.andlim.tugas4challangelukman.Datauser.DataUser
import lu.andlim.tugas4challangelukman.Datauser.UserDatabase

@DelicateCoroutinesApi
class RegisterFragment : Fragment() {

    private var dbuser : UserDatabase? = null
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
            if(edt_nomor.text.isNotEmpty() &&
                edt_username.text.isNotEmpty() &&
                edt_username.text.isNotEmpty() &&
                edt_password.text.isNotEmpty() &&
                edt_konfirmasi.text.isNotEmpty()){

                //check the similarity between the password field and confirm password
                if(edt_password.text.toString() != edt_konfirmasi.text.toString()){
                    Toast.makeText(requireContext(), "Password dan konfirmasi password harus sama", Toast.LENGTH_SHORT).show()
                }else{
                    //if similar, then input user data to user database
//                    inputUserData()
                    saveData()
                    Navigation.findNavController(view).navigate(R.id.action_to_login)
                }
            }else{
                Toast.makeText(requireContext(), "Semua data belum terisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveData(){
        dbuser = UserDatabase.getInstance(requireContext())
        GlobalScope.async {
            //get all data from edit text
            val nm = edt_nomor.text.toString()
            val user = edt_username.text.toString()
            val em = edt_email.text.toString()
            val pass = edt_password.text.toString()
            val konf = edt_konfirmasi.text.toString()

            val proses = dbuser?.userDao()?.insertUser(DataUser(null, nm, user, em,pass, konf))
            //cehck if command worked or not
            activity?.runOnUiThread{
                if(proses != 0.toLong()){
                    Toast.makeText(requireContext(), "Proses register berhasil", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Proses register gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}