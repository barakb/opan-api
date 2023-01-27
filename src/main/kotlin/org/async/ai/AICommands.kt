package org.async.ai

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class AICommands(private val openAI: OpenAI) {

    @ShellMethod("Complete a sentence")
    fun q(@ShellOption msg: String): String {
        return openAI.complete(msg)
            .block()!!.trimStart()
    }

    @ShellMethod("list AI models")
    fun listModels(): String {
        return openAI.models()
            .block()!!
            .joinToString(", ")
    }
}
