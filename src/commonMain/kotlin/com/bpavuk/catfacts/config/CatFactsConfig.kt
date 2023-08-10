package com.bpavuk.catfacts.config

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
public class CatFactsConfig {
    public var defaultFactLength: Int? = null
    public var baseUrl: String = "https://catfact.ninja"
}
