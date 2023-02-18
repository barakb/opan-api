package org.async.ai

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory


@SpringBootApplication
class AiApplication{

	@Value("\${openai.url}")
	private lateinit var openURL: String
	@Bean
	fun webClient(): WebClient {
		val size = 16 * 1024 * 1024
		val strategies = ExchangeStrategies.builder()
			.codecs { codecs: ClientCodecConfigurer ->
				codecs.defaultCodecs().maxInMemorySize(size)
			}.build()
		return WebClient.builder()
			.exchangeStrategies(strategies)
			.baseUrl(openURL)
			.build()
	}
	@Bean
	fun objectMapper(): ObjectMapper = jacksonObjectMapper()

	@Bean
	fun OpenAIClient(webClient: WebClient): OpenAIClient {
		val proxyFactory =
			HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
		return proxyFactory.createClient(OpenAIClient::class.java)
	}

}


fun main(args: Array<String>) {
	runApplication<AiApplication>(*args)
}
