package com.bpavuk.catfacts.config

import io.ktor.http.*

public class CatFactsConfig {
    public var defaultFactLength: Int? = null
    public var baseUrl: Url = Url("https://catfact.ninja")
}
