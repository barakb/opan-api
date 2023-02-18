package org.async.ai

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import reactor.core.publisher.Mono

@HttpExchange(contentType = MediaType.APPLICATION_JSON_VALUE)
interface OpenAIClient {
    @PostExchange("/completions")
    fun complete(@RequestBody body: Map<String, Any>, @RequestHeader(HttpHeaders.AUTHORIZATION) bearerToken: String): Mono<String>


    @GetExchange(url="/models")
    fun models(@RequestHeader(HttpHeaders.AUTHORIZATION) bearerToken: String): Mono<String>
}