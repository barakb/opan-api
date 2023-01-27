package org.async.ai

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient


@SpringBootApplication
class AiApplication{
	@Bean
	fun webClient(): WebClient {
		val size = 16 * 1024 * 1024
		val strategies = ExchangeStrategies.builder()
			.codecs { codecs: ClientCodecConfigurer ->
				codecs.defaultCodecs().maxInMemorySize(size)
			}.build()
		return WebClient.builder()
			.exchangeStrategies(strategies)
			.baseUrl("https://api.openai.com/v1")
			.build()
	}
	@Bean
	fun objectMapper(): ObjectMapper = jacksonObjectMapper()
	@Bean
	fun openAI(): OpenAI = OpenAI(webClient(), objectMapper())

}


fun main(args: Array<String>) {
	runApplication<AiApplication>(*args)
}
