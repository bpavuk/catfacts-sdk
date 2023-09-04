package com.bpavuk.catfacts

import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
@Serializable  // приберіть анотацію якщо використовуєте щось інше окрім Serialization API
public data class Fact(val fact: String)
