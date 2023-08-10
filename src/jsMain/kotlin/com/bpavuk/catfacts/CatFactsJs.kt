package com.bpavuk.catfacts

import com.bpavuk.catfacts.config.CatFactsConfig
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

@OptIn(ExperimentalJsExport::class)
@JsExport
public class CatFactsJs(config: CatFactsConfig.() -> Unit = {})  {
    private val config = CatFactsConfig().apply(config)
    private val sdk = CatFacts(this.config)

    @OptIn(DelicateCoroutinesApi::class)
    public fun getCatFact(maxLength: Int? = config.defaultFactLength): Promise<Fact> = GlobalScope.promise {
        sdk.getCatFact(maxLength)
    }
}
