package org.async.ai
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class OpenAI(private val openAiWebClient: WebClient, private val objectMapper: ObjectMapper) {

    @Value("\${openai.key}")
    private var openAIKey: String? = ""

    fun complete(
        msg: String,
        maxTokens: Int = 100,
        temperature: Float = 0F,
        model: String = "text-davinci-003"
    ): Mono<String> {



        return openAiWebClient.post()
            .uri("/completions")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer $openAIKey")
            .bodyValue(
                mapOf(
                    "model" to model,
                    "prompt" to msg,
                    "temperature" to temperature,
                    "max_tokens" to maxTokens
                )
            )
            .retrieve()
            .bodyToMono(String::class.java)
            .map{
                objectMapper.readTree(it)
                    .get("choices")
                    .get(0)
                    .get("text")
                    .asText()
            }

    }

    fun models(): Mono<List<String>> {
        return openAiWebClient.get()
            .uri("/models")
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer  $openAIKey")
            .retrieve()
            .bodyToMono(String::class.java)
            .map { resp ->
                objectMapper.readTree(resp)
                    .get("data")
                    .map { it.get("id").asText() }
            }
    }
}

