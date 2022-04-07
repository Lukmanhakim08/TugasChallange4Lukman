package lu.andlim.tugas4challangelukman

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class DashboardFragment : Fragment() {

    private var dbSekolah : SekolahDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //mengambil data username ke home
        val sharedPreferences = requireContext().getSharedPreferences("DATAUSER", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("USERNAME", "")
        text_username.text = "Welcome, $username"

        text_logout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("LOGOUT")
                .setMessage("Apakah anda yakin ingin logout ?")
                .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }.setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                    val sharedPreferences = requireContext().getSharedPreferences("DATAUSER", Context.MODE_PRIVATE)
                    val prefs = sharedPreferences.edit()
                    prefs.clear()
                    prefs.apply()

                    //proses reload activity
                    val mIntent = activity?.intent
                    activity?.finish()
                    startActivity(mIntent)
                }.show()
        }

        fab_add.setOnClickListener {
            InputDialogFragment().show(childFragmentManager, "InputDialogFragment")
        }
        dbSekolah = SekolahDatabase.getInstance(requireContext())
        getDataSekolah()
    }

    // Methode untuk memanggil data dari SekolahDatabase
    private fun getDataSekolah(){
        //mendefinisikan layout manager
        rv_sekolah.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        //proses perintah untuk database kamar yang mengembalikan semua data
        val listSekolah = dbSekolah?.sekolahDao()?.getAllSekolah()
        GlobalScope.launch {
            activity?.runOnUiThread {
                listSekolah.let {
                    rv_sekolah.adapter = AdapterSekolah(it!!)
                }
            }
        }
    }
}