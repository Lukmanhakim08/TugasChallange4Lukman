package lu.andlim.tugas4challangelukman

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Sekolah(
    @PrimaryKey(autoGenerate = true) var id : Int?,
    @ColumnInfo(name = "sekolah") var sekolah: String?,
    @ColumnInfo(name = "alamat") var alamat: String?,
) : Parcelable
