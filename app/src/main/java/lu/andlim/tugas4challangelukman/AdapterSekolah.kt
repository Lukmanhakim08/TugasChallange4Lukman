package lu.andlim.tugas4challangelukman

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.alert_hapus.view.*
import kotlinx.android.synthetic.main.edit_custem.view.*
import kotlinx.android.synthetic.main.item_sekolah.view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@DelicateCoroutinesApi
class AdapterSekolah(private val listSekolah : List<Sekolah>) : RecyclerView.Adapter<AdapterSekolah.ViewHolder>()  {
    private var dbSekolah : SekolahDatabase? = null
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sekolah, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text_id.text = listSekolah[position].id.toString()
        holder.itemView.text_sekolah.text = listSekolah[position].sekolah
        holder.itemView.text_alamat.text = listSekolah[position].alamat

        //triger hapus
        holder.itemView.btn_hapus.setOnClickListener {
            dbSekolah = SekolahDatabase.getInstance(it.context)
            val dialogcustem = LayoutInflater.from(it.context)
                .inflate(R.layout.alert_hapus, null, false)
            val hapus = AlertDialog.Builder(it.context)
                .setView(dialogcustem)
                .create()
            dialogcustem.btnbatal.setOnClickListener {
                hapus.dismiss()
            }
            dialogcustem.btnhapus.setOnClickListener {
                val delete = dbSekolah?.sekolahDao()?.deleteDataSekolah(listSekolah[position])
                (dialogcustem.context as MainActivity).runOnUiThread {
                    if(delete != 0){
                        Toast.makeText(dialogcustem.context, "Data Nama ${listSekolah[position].sekolah} berhasil dihapus", Toast.LENGTH_SHORT).show()
                        (dialogcustem.context as MainActivity).recreate()
                    }else{
                        Toast.makeText(dialogcustem.context, "Data Nama ${listSekolah[position].sekolah} gagal dihapus", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            hapus.show()
        }

        //Triger Edit/update
        holder.itemView.btn_edit.setOnClickListener {
            dbSekolah = SekolahDatabase.getInstance(it.context)

            //create dialog for edit action
            val dialogcustemedit = LayoutInflater.from(it.context)
                .inflate(R.layout.edit_custem, null, false)
            val edit = AlertDialog.Builder(it.context)
                .setView(dialogcustemedit)
                .create()

            dialogcustemedit.edt_editsekolah.setText(listSekolah[position].sekolah)
            dialogcustemedit.edt_editalamat.setText(listSekolah[position].alamat)

            // edit action button
            dialogcustemedit.btn_update.setOnClickListener {
                val newsekolah = dialogcustemedit.edt_editsekolah.text.toString()
                val newAlamat = dialogcustemedit.edt_editalamat.text.toString()

                // memanggil ulang data daftar sekolah yang ada di posisi sekarang
                listSekolah[position].sekolah = newsekolah
                listSekolah[position].alamat = newAlamat

                GlobalScope.async {
                    //proses kodingan yang akan di input kedalam database
                    val prosesedit = dbSekolah?.sekolahDao()?.updateDataSekolah(listSekolah[position])
                    //memeriksa apakah proses edit berhasil atau tidak
                    (dialogcustemedit.context as MainActivity).runOnUiThread {
                        if (prosesedit != 0){
                            Toast.makeText(it.context, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
                            //recreate activity
                            (dialogcustemedit.context as MainActivity).recreate()
                        }else{
                            Toast.makeText(it.context, "Catatan gagal diupdate", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
           }
            edit.show()
        }
    }

    override fun getItemCount(): Int {
        return listSekolah.size
    }
}