package org.ldv.chatbot.dto.gemini

data class GeminiChatRequest(
    val contents: List<GeminiContent>
) {
    companion object {
        fun fromUserMessage(prompt: String): GeminiChatRequest =
            GeminiChatRequest(
                contents = listOf(
                    GeminiContent(
                        role = "user",
                        parts = listOf(GeminiPart(text = prompt))
                    )
                )
            )
    }
}