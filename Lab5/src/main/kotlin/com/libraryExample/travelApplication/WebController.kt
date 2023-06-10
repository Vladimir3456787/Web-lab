package com.libraryExample.travelApplication

import com.libraryExample.travelApplication.dtos.LoginDTO
import com.libraryExample.travelApplication.dtos.RegisterDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
@Controller
class WebController {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("registerDTO", RegisterDTO())
        model.addAttribute("loginDTO", LoginDTO())
        return "index"
    }
}
