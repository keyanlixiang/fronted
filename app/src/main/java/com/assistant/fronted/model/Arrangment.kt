package com.assistant.fronted.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Arrangment")
data class Arrangment(
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "isdone") var isdone: Int
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
