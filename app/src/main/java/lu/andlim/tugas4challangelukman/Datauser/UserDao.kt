package lu.andlim.tugas4challangelukman.Datauser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(dataUser: DataUser) : Long

    @Query("SELECT username FROM DataUser " +
            "WHERE DataUser.email = :email AND DataUser.password = :password")
    fun cekloginuser(email : String, password : String) : String
}