package com.sameershelar.toodo.util

import androidx.compose.ui.graphics.Color

/**
 * Converts a hex color string to a Compose Color.
 *
 * Supports formats:
 * - "0xFF00FFFF" or "FF00FFFF" (ARGB format)
 *
 * @return Color parsed from the hex string, or Color.Transparent if parsing fails
 */
fun String.toColor(): Color {
    return try {
        // Parse hex string like "0xFF00FFFF" or "FF00FFFF" to ULong
        val hexString = removePrefix("0x").removePrefix("0X").trim()
        val colorValue = hexString.toULong(radix = 16)

        // Extract ARGB components from the 32-bit value
        // Format: ARGB (Alpha, Red, Green, Blue)
        val alpha = ((colorValue shr 24) and 0xFFu).toInt() / 255f
        val red = ((colorValue shr 16) and 0xFFu).toInt() / 255f
        val green = ((colorValue shr 8) and 0xFFu).toInt() / 255f
        val blue = (colorValue and 0xFFu).toInt() / 255f

        // Use Color(red, green, blue, alpha) instead of Color(ULong) to avoid ArrayIndexOutOfBoundsException
        Color(red, green, blue, alpha)
    } catch (_: Exception) {
        Color.Transparent
    }
}

/**
 * Converts a Compose Color to a hex string for storage.
 *
 * @return Hex string in format "FF00FFFF" (ARGB format, without "0x" prefix)
 *
 * Example:
 * - Color.Cyan.toHexString() returns "FF00FFFF"
 * - "FF00FFFF".toColor() converts back to Color.Cyan
 */
fun Color.toHexString(): String {
    val a = (alpha * 255).toInt()
    val r = (red * 255).toInt()
    val g = (green * 255).toInt()
    val b = (blue * 255).toInt()

    // Format as 2-digit uppercase hex - multiplatform compatible
    fun Int.toHex(): String {
        val hex = toString(16).uppercase()
        return if (hex.length == 1) "0$hex" else hex
    }

    return "${a.toHex()}${r.toHex()}${g.toHex()}${b.toHex()}"
}