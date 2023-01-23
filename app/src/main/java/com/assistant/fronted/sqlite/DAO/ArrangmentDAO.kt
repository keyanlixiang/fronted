package com.assistant.fronted.sqlite.DAO
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.assistant.fronted.model.Arrangment

@Dao
interface ArrangmentDAO {
    @Insert
    fun insertArrange(arrangment: Arrangment)

    @Query("select * from arrangment where date= :date")
    fun getArrangeFromDate(date: String): List<Arrangment>

    @Query("update arrangment set isdone = :isdone where id= :id")
    fun setIsdone(isdone: Int,id: Int)

    @Query("delete from arrangment where id= :id")
    fun deleteFromId(id: Int)

    @Query("select * from arrangment")
    fun getAll(): List<Arrangment>

    @Query("select * from arrangment where id= :id")
    fun getArrangeFromID(id: Int): Arrangment

    @Query("update arrangment set content = :content where id= :id")
    fun updateArrange(content:String, id: Int)
}