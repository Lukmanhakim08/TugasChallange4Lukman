package lu.andlim.tugas4challangelukman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_input_dialog.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@DelicateCoroutinesApi
class InputDialogFragment : DialogFragment() {

    private var dbSekolah : SekolahDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbSekolah = SekolahDatabase.getInstance(requireContext())
        btn_simpan.setOnClickListener {
            GlobalScope.async {
                val nama = input_sekolah.text.toString()
                val alamat = edt_inputalamat.text.toString()
                val simpan = dbSekolah?.sekolahDao()?.insertSekolah(Sekolah(null, nama, alamat))
                activity?.runOnUiThread {
                    if (simpan != 0.toLong()){
                        Toast.makeText(requireContext(), "Sukses", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(), "Gagal", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            dismiss()
        }
    }

    override fun onDetach() {
        super.onDetach()
        activity?.recreate()
    }

}