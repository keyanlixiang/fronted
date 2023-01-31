package com.assistant.fronted.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "Achievement")
data class Achievement (
    @ColumnInfo(name = "sno") var sno:String,
    @ColumnInfo(name = "ano") var ano:String,
    @ColumnInfo(name="acontext") var acontext:String,
    @ColumnInfo(name = "acertificate") var acertificate:String,
    @ColumnInfo(name = "atime") var atime:String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}