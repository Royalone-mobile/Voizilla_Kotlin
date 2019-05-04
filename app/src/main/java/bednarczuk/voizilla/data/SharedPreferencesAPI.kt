package bednarczuk.voizilla.data

interface SharedPreferencesAPI {
    fun getFilter(key: String): Boolean
    fun saveFilter(key: String, value: Boolean)
}