package com.bpavuk.catfacts

import io.ktor.client.*

internal expect fun getClient(): HttpClient
