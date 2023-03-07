package com.example.consortium.domain.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.consortium.domain.models.Delivery
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryRepository {
    @Query("SELECT * FROM deliveries")
    fun retrieveAll() : Flow<List<Delivery>>

    @Insert
    suspend fun insert(delivery: Delivery)
}