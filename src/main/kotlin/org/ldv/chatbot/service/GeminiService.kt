package org.ldv.chatbot.service


import org.ldv.chatbot.dto.gemini.GeminiChatRequest
import org.ldv.chatbot.dto.gemini.GeminiChatResponse
import org.ldv.chatbot.model.entity.ModeleIA
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class GeminiService(
    @Value("\${gemini.api.url:https://generativelanguage.googleapis.com/v1beta}")
    private val baseUrl: String,
    @Value("\${gemini.api.key}")
    private val apiKey: String,


) : IAService {

    private val restClient: RestClient = RestClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .build()

    override fun getFournisseur(): String = "Gemini"

     override fun envoyerPrompt(prompt: String,modeleIA: ModeleIA): String {
        val request = GeminiChatRequest.fromUserMessage(prompt)

        val response = restClient.post()
            .uri(modeleIA.urlAPI)
            .header("x-goog-api-key", apiKey)
            .body(request)
            .retrieve()
            .body(GeminiChatResponse::class.java)

        return response
            ?.candidates
            ?.firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull { !it.text.isNullOrBlank() }
            ?.text
            ?: throw IllegalStateException("Aucune réponse textuelle reçue de Gemini.")
    }
}