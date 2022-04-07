package lu.andlim.tugas4challangelukman

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lu.andlim.tugas4challangelukman.Datauser.DataUser
import lu.andlim.tugas4challangelukman.Datauser.UserDao

@Database(entities = [Sekolah::class], version = 1)
abstract class SekolahDatabase : RoomDatabase(){
    abstract fun sekolahDao() : SekolahDao

    companion object{
        private var INSTANCE : SekolahDatabase? = null
        fun getInstance(context : Context):SekolahDatabase? {
            if (INSTANCE == null){
                synchronized(SekolahDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SekolahDatabase::class.java,"Sekolah.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}