package com.libraryExample.travelApplication.datasource.mock

import com.libraryExample.travelApplication.datasource.TravelDataSource
import com.libraryExample.travelApplication.model.Travel
import org.springframework.stereotype.Repository

@Repository
class MockTravelDataSource : TravelDataSource {

    val travels = mutableListOf(
        Travel(1, "Россия", "Зоопарк", "Направление 123"),
        Travel(2, "Турция", "Аквопарк", "Направление 345"),
        Travel(3, "Египет", "Пирамида", "Направление 335"),
    )

    override fun retrieveTravels(): Collection<Travel> = travels

    override fun retrieveTravel(id: Int): Travel =
        travels.firstOrNull { it.id == id } ?: throw NoSuchElementException("Could not find travel with id $id")

    override fun createTravel(travel: Travel): Travel {
        if (travels.any { it.id == travel.id }) {
            throw IllegalArgumentException("Travel with id ${travel.id} already exists")
        }
        travels.add(travel)
        return travel
    }

    override fun updateTravel(travel: Travel): Travel {
        val currentTravel = travels.firstOrNull { it.id == travel.id }
            ?: throw NoSuchElementException("Could not find travel with id ${travel.id}")

        travels.remove(currentTravel)
        travels.add(travel)

        return travel
    }

    override fun deleteTravel(id: Int) {
        val currentTravel =
            travels.firstOrNull { it.id == id } ?: throw NoSuchElementException("Could not find book with id $id")

        travels.remove(currentTravel)
    }
}