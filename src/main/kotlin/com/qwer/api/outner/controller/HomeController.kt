package com.qwer.api.outner.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/")
class HomeController {

    @GetMapping("/")
    fun home(): String {
        return "kotlin-template" + "1.0.0"
    }
}

