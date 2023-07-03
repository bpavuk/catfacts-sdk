import kotlinx.serialization.Serializable

@Serializable  // приберіть анотацію якщо використовуєте щось інше окрім Serialization API
data class Fact(val fact: String)
