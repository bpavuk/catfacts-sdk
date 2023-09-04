import com.bpavuk.catfacts.CatFacts
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

class SDKTest {
    @Test
    fun testCreateSDKWithDefaultConfig() = runTest(timeout = 30.seconds) {
        val sdk = CatFacts()

        sdk.getCatFact()
    }

    @Test
    fun createConfigurableSDK() = runTest(timeout = 30.seconds) {
        val sdk = CatFacts {
            defaultFactLength = 50
        }

        repeat(5) {
            val fact = sdk.getCatFact()
            assertTrue(fact.fact.length <= 50)
        }
    }
}
