package com.libraryExample.travelApplication.service

import com.libraryExample.travelApplication.datasource.TravelDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class TravelServiceTest {
    private val dataSource: TravelDataSource = mockk(relaxed = true)
    private val travelService = TravelService(dataSource)

    @Test
    fun `should call its data source to retrieve books`() {
        // when
        travelService.getBooks()

        // then
        verify(exactly = 1) { dataSource.retrieveBooks() }
    }
}