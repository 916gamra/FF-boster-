package com.example

import android.content.Context
import android.content.SharedPreferences

class StabilizerPrefs(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("stabilizer_prefs", Context.MODE_PRIVATE)

    companion object {
        const val KEY_DELAY_MS = "stabilization_delay_ms"
        const val KEY_BUFFER_SIZE = "jitter_buffer_size"
        const val DEFAULT_DELAY_MS = 30L
        const val DEFAULT_BUFFER_SIZE = 8
    }

    var delayMs: Long
        get() = prefs.getLong(KEY_DELAY_MS, DEFAULT_DELAY_MS)
        set(value) = prefs.edit().putLong(KEY_DELAY_MS, value).apply()

    var bufferSize: Int
        get() = prefs.getInt(KEY_BUFFER_SIZE, DEFAULT_BUFFER_SIZE)
        set(value) = prefs.edit().putInt(KEY_BUFFER_SIZE, value).apply()
}
