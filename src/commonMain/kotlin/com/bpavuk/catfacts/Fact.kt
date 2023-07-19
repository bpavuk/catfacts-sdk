package com.bpavuk.catfacts

import kotlinx.serialization.Serializable

@Serializable  // приберіть анотацію якщо використовуєте щось інше окрім Serialization API
public data class Fact(val fact: String)
