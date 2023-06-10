package com.libraryExample.travelApplication.datasource.mock

import org.assertj.core.api.Assertions.assertThat

class MockTravelDataSourceTest {
    private val mockDataSource = MockTravelDataSource()

    @org.junit.jupiter.api.Test
    fun `should provide a collection of travels`() {
        // when
        val travels = mockDataSource.retrieveTravels()
        // then
        assertThat(travels.size).isGreaterThanOrEqualTo(3)
    }

    @org.junit.jupiter.api.Test
    fun `should provide some mock data`() {
        // when
        val travels = mockDataSource.retrieveTravels()
        // then
        assertThat(travels).allMatch { it.residence.isNotBlank() }
        assertThat(travels).anyMatch { it.attractions.isNotBlank() }
        assertThat(travels).anyMatch { it.directions.isNotBlank() }
    }
}