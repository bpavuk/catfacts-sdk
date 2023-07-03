import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

suspend fun main() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {  // серіалізація
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(HttpCookies) // cookies
        install(UserAgent) {
            agent = "My cool secret user agent"
        }
        install(DefaultRequest) {  // запити за замовчуванням
            url {
                host = "catfact.ninja"
                protocol = URLProtocol.HTTPS
            }
        }
    }
    val response = client.get("fact") {  // get-запит до API
        url {
            parameters.append("max_length", "60")
        }
    }
    println(response.bodyAsText())  // bodyAsText() перетворює тіло відповіді у текст, або падає якщо там не текст
    val fact: Fact = response.body()  // можна також response.body<Fact>()
    println(fact.fact)  // над усіма отриманими даними можна оперувати як і зі звичайними датакласами
}
