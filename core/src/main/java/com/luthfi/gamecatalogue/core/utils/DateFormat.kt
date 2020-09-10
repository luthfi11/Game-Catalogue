package com.luthfi.gamecatalogue.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun dateFormat(date: String?): String? {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    return if (date != null) sdf.format(format.parse(date))
    else "-"
}