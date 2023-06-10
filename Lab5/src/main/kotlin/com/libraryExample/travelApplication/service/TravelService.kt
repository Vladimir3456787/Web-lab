package com.libraryExample.travelApplication.service

import com.libraryExample.travelApplication.datasource.TravelDataSource
import com.libraryExample.travelApplication.model.Travel
import org.springframework.stereotype.Service

@Service
class TravelService(private val dataSource: TravelDataSource) {
    fun getTravels(): Collection<Travel> = dataSource.retrieveTravels()
    fun getTravel(id: Int) = dataSource.retrieveTravel(id)
    fun addTravel(travel: Travel): Travel = dataSource.createTravel(travel)
    fun updateTravel(travel: Travel): Travel = dataSource.updateTravel(travel)
    fun deleteTravel(id: Int): Unit = dataSource.deleteTravel(id)
}
