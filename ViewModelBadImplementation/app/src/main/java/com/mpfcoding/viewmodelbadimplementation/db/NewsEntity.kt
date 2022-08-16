package com.mpfcoding.viewmodelbadimplementation.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mpfcoding.viewmodelbadimplementation.domain.News

@Entity
data class NewsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title_news") val title: String
)

fun NewsEntity.toDomain() = News(this.id, this.title)