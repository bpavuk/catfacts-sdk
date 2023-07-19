package com.bpavuk.catfacts

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal actual fun getClient(): HttpClient = HttpClient(Darwin) {
    install(ContentNegotiation) {  // серіалізація
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }
    install(HttpCookies) // cookies
    install(UserAgent) {
        agent = "My cool secret user agent"
    }
    install(DefaultRequest) {  // запити за замовчуванням
        url {
            host = "catfact.ninja"
            protocol = URLProtocol.HTTPS
        }
    }
}
