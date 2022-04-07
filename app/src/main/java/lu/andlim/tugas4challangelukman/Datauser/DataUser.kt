package lu.andlim.tugas4challangelukman.Datauser

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class DataUser(
    @PrimaryKey(autoGenerate = true) var id : Int?,
    @ColumnInfo(name = "nomor") var nomor : String?,
    @ColumnInfo(name = "username") var username : String?,
    @ColumnInfo(name = "email") var email : String?,
    @ColumnInfo(name = "password") var password : String?,
    @ColumnInfo(name = "konfirmasi") var konfirmasi : String?,
) : Parcelable
