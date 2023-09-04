package com.bpavuk.catfacts

import io.ktor.client.*
import io.ktor.http.*

internal expect fun getClient(baseUrl: Url = Url("https://catfact.ninja")): HttpClient
