package org.async.ai

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OpenAIService(private val client: OpenAIClient, private val objectMapper: ObjectMapper) {
    @Value("\${openai.key}")
    private lateinit var openAIKey: String

    fun complete(
        msg: String,
        maxTokens: Int = 500,
        temperature: Float = 0.7F,
        model: String = "text-davinci-003"
    ): Mono<String> {
        val body = mapOf(
            "model" to model,
            "prompt" to msg,
            "temperature" to temperature,
            "max_tokens" to maxTokens,
        )
        return client.complete(body, "Bearer $openAIKey")
            .map {
                objectMapper.readTree(it)
                    .get("choices")
                    .get(0)
                    .get("text")
                    .asText()
            }
    }

    fun models(): Mono<List<String>> {
        return client.models("Bearer $openAIKey")
            .map { resp ->
                objectMapper.readTree(resp)
                    .get("data")
                    .map { it.get("id").asText() }
            }
    }
}