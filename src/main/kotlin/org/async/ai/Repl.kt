package org.async.ai

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader

@Component
class Repl(private val openAIService: OpenAIService) : CommandLineRunner {
    override
    fun run(vararg args: String?) {
        val reader = BufferedReader(InputStreamReader(System.`in`))

        while (true) {
            print(">>> ")
            val input = reader.readLine()
            if (input == "exit") break

            try {
                val result = eval(input)
                println(result)
                println()
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    fun eval(input: String): Any {
        return openAIService.complete(input)
            .block()!!.trimStart()
    }
}