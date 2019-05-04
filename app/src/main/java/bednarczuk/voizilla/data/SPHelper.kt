package bednarczuk.voizilla.data

import android.content.SharedPreferences

class SPHelper(private val sharedPreferences: SharedPreferences) : SharedPreferencesAPI {

    private val editor = sharedPreferences.edit()

    override fun getFilter(key: String) = sharedPreferences.getBoolean(key, true)

    override fun saveFilter(key: String, value: Boolean) {
        editor.putBoolean(key, value).commit()
    }

}