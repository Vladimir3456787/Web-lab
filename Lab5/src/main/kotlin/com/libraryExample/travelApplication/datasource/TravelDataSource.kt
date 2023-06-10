package com.libraryExample.travelApplication.datasource

import com.libraryExample.travelApplication.model.Travel


interface TravelDataSource {
    fun retrieveTravels(): Collection<Travel>

    fun retrieveTravel(id: Int): Travel

    fun createTravel(travel: Travel): Travel

    fun updateTravel(travel: Travel): Travel

    fun deleteTravel(id: Int): Unit
}
