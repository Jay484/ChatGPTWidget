package com.support.chatgptwidget.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.support.chatgptwidget.data.local.entity.ImageMessagesEntity


@Dao
interface ImageChatDao {

    @Insert
    suspend fun insert(imageMessagesEntity: ImageMessagesEntity)

    @Query("SELECT * from ImageMessages")
    suspend fun getAllImageMessage(): List<ImageMessagesEntity>

}