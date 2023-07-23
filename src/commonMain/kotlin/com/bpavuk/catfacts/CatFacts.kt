package com.bpavuk.catfacts

import com.bpavuk.catfacts.config.CatFactsConfig
import io.ktor.client.call.*
import io.ktor.client.request.*

public class CatFacts(config: CatFactsConfig.() -> Unit = {}) {
    private val client = getClient()
    private val config = CatFactsConfig().apply(config)

    /**
     * Returns cat fact
     * @param maxLength maximum length of the fact, null means unlimited length.
     * Defaults to [CatFactsConfig.defaultFactLength].
     */
    public suspend fun getCatFact(maxLength: Int? = config.defaultFactLength): Fact = client.get("fact") {  // get-запит до API
        url {
            if (maxLength != null) parameters.append("max_length", "$maxLength")
        }
    }.body()
}
