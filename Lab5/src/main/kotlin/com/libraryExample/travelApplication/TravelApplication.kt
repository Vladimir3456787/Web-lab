package com.libraryExample.travelApplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class TravelApplication

fun main(args: Array<String>) {
	runApplication<TravelApplication>(*args)
}
