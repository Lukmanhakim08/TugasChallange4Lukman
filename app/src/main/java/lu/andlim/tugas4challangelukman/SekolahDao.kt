package lu.andlim.tugas4challangelukman

import androidx.room.*

@Dao
interface SekolahDao {
    @Insert fun insertSekolah(sekolah: Sekolah) : Long

    @Query("SELECT * FROM Sekolah")
    fun getAllSekolah() : List<Sekolah>

    @Delete
    fun deleteDataSekolah(sekolah: Sekolah) : Int

    @Update
    fun updateDataSekolah(sekolah: Sekolah) : Int
}