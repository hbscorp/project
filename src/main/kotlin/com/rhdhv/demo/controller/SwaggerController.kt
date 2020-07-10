package com.rhdhv.demo.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@ApiIgnore
@RestController
class SwaggerController {

    // The only purpose here is to redirect calls from the root url to /swagger-ui.html
    @GetMapping("/")
    fun redirectRootUrlToSwaggerUI(): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "/swagger-ui.html")
                .build()
    }

}
