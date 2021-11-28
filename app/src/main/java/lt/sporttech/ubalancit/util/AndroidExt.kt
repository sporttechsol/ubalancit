package lt.sporttech.ubalancit.util

import android.content.Context
import androidx.annotation.DrawableRes

fun imageResourceFromString(
    context: Context,
    title: String,
    @DrawableRes default: Int
): Int = context.resources.getIdentifier(
    title.toSnakeCase().also { android.util.Log.v("CharlieDebug", "Name: $it") },
    "drawable",
    context.packageName,
).let { if (it == 0) default else it }

private fun String.toSnakeCase(): String {
    val words = split(" ")
    return words.joinToString(separator = "_") { word -> word.lowercase() }
}