package com.example.consortium.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deliveries")
data class Delivery(
        var froynyx: Float = 0.0f,
        var kreotrium : Float = 0.0f,
        var vethynx : Float = 0.0f,
        var yerfrium :Float = 0.0f,
        var zuscum : Float = 0.0f){
    @PrimaryKey(true)
    var idDelivery: Int = 0
}
