package org.async.ai

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

class OpenAI(private val openAiWebClient: WebClient, private val objectMapper: ObjectMapper) {

    @Value("\${openai.api.key}")
    private lateinit var openAIKey: String

    fun complete(
        msg: String,
        maxTokens: Int = 200,
        temperature: Float = 0.7F,
        model: String = "text-davinci-003"
    ): Mono<String> {

        return openAiWebClient.post()
            .uri("/completions")
            .headers { it.addAll(createHttpHeaders()) }
            .bodyValue(mapOf(
                "model" to model,
                "prompt" to msg,
                "temperature" to temperature,
                "max_tokens" to maxTokens,
            ))
            .retrieve()
            .bodyToMono(String::class.java)
            .map {
                objectMapper.readTree(it)
                    .get("choices")
                    .get(0)
                    .get("text")
                    .asText()
            }

    }

    @Suppress("unused")
    fun models(): Mono<List<String>> {
        return openAiWebClient.get()
            .uri("/models")
            .headers { it.addAll(createHttpHeaders()) }
            .retrieve()
            .bodyToMono(String::class.java)
            .map { resp ->
                objectMapper.readTree(resp)
                    .get("data")
                    .map { it.get("id").asText() }
            }
    }

    private fun createHttpHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBearerAuth(openAIKey)
        return headers
    }

}

