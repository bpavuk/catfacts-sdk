package com.bpavuk.catfacts

import io.ktor.client.call.*
import io.ktor.client.request.*

public class CatFacts {
    private val client = getClient()

    public suspend fun getCatFact(maxLength: Int? = null): Fact = client.get("fact") {  // get-запит до API
        url {
            if (maxLength != null) parameters.append("max_length", "$maxLength")
        }
    }.body()
}
