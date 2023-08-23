package com.acabes.carapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CarDetails(val Results:List<Car>)
@Parcelize
data class Car(val Country:String,
               val Mfr_CommonName: String,
               val Mfr_ID: Int,
               val Mfr_Name: String,
               val VehicleTypes: List<Vehicles>):Parcelable
@Parcelize
data class Vehicles(val Name:String):Parcelable