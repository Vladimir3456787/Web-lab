package com.libraryExample.travelApplication.controller


import com.libraryExample.travelApplication.model.Travel
import com.libraryExample.travelApplication.service.TravelService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/travels")
class TravelController(private val service: TravelService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getTravels(): Collection<Travel> = service.getTravels()

    @GetMapping("/{id}")
    fun getTravel(@PathVariable id: Int): Travel = service.getTravel(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTravel(@RequestBody travel: Travel): Travel = service.addTravel(travel)

    @PatchMapping
    fun updateTravel(@RequestBody travel: Travel): Travel = service.updateTravel(travel)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTravel(@PathVariable id: Int): Unit = service.deleteTravel(id)
}