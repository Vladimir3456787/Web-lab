package com.libraryExample.travelApplication.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.libraryExample.travelApplication.model.Travel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
class TravelControllerTest @Autowired constructor(
    val mockMvc: MockMvc, val objectMapper: ObjectMapper
) {
    private val baseUrl = "/api/travels"

    @Nested
    @DisplayName("GET /api/travels")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTravels {

        @Test
        fun `should return all travels`() {
            // when / then
            mockMvc.get(baseUrl).andDo { print() }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].title") {
                    value("Россия")
                }
            }
        }

    }

    @Nested
    @DisplayName("GET /api/travels/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTravel {

        @Test
        fun `should return single travel with the given id`() {
            // given
            val id = 1

            // when / then
            mockMvc.get("$baseUrl/$id").andDo { print() }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.title") {
                    value("Россия")
                }
            }
        }

        @Test
        fun `should return NOT FOUND if the given id does not exist`() {
            // given
            val id = 999

            // when / then
            mockMvc.get("$baseUrl/$id").andDo { print() }.andExpect {
                status { isNotFound() }
            }
        }
    }

    @Nested
    @DisplayName("POST /api/books")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AddTravel {

        @Test
        fun `should add the new travel`() {
            // given
            val id = 4
            val residence = "Россия"
            val attractions = "Зоопарк"
            val directions = "Направлни 123"
            val newTravel = Travel(id = id, residence = residence, attractions = attractions,  directions = directions)

            // when
            val performPost = mockMvc.post("/api/travels") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newTravel)
            }

            // then
            performPost.andDo { print() }.andExpect {
                status { isCreated() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(objectMapper.writeValueAsString(newTravel))
                }
            }

            mockMvc.get("/api/travels/${newTravel.id}").andExpect {
                content { json(objectMapper.writeValueAsString(newTravel)) }
            }
        }

        @Test
        fun `should return BAD REQUEST if book with given id already exist`() {
            // given
            val invalidTravel = Travel(id = 1, residence = "Россия", attractions = "Зоопарк", directions = "Направлни 123")

            // when
            val performPost = mockMvc.post("/api/travels") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidTravel)
            }

            // then
            performPost.andDo { print() }.andExpect {
                status { isBadRequest() }
            }
        }
    }

    @Nested
    @DisplayName("PATCH /api/travels")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingTravel {

        @Test
        fun `should update the travel with the given id`() {
            // given
            val id = 1
            val updatedTravel = Travel(id = id, residence = "Россия", attractions = "Зоопарк", directions = "Направлни 123")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedTravel)
            }

            // then
            performPatchRequest.andDo { print() }.andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(objectMapper.writeValueAsString(updatedTravel))
                }
            }

            mockMvc.get("/api/travels/$id").andExpect {
                content { json(objectMapper.writeValueAsString(updatedTravel)) }
            }
        }

        @Test
        fun `should return NOT FOUND if the given id does not exist`() {
            // given
            val id = 999
            val updatedTravel = Travel(id = id, residence = "Россия", attractions = "Зоопарк", directions = "Направлни 123")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedTravel)
            }

            // then
            performPatchRequest.andDo { print() }.andExpect {
                status { isNotFound() }
            }
        }
    }

    @Nested
    @DisplayName("DELETE /api/travels/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistingTravel {

        @Test
        fun `should delete the travel with the given id`() {
            // given
            val id = 1

            // when
            val performDeleteRequest = mockMvc.delete("$baseUrl/$id")

            // then
            performDeleteRequest.andDo { print() }.andExpect {
                status { isNoContent() }
            }

            mockMvc.get("$baseUrl/$id").andExpect {
                status { isNotFound() }
            }
        }

        @Test
        fun `should return NOT FOUND if the given id does not exist`() {
            // given
            val id = 999

            // when
            val performDeleteRequest = mockMvc.delete("$baseUrl/$id")

            // then
            performDeleteRequest.andDo { print() }.andExpect {
                status { isNotFound() }
            }
        }
    }
}