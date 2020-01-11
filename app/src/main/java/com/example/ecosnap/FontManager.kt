package com.example.ecosnap

import android.content.Context
import android.graphics.Typeface

object FontManager {
    const val ROOT = "fonts/"
    const val LINEAWESOMEREGULAR = ROOT + "la-solid-900.ttf"
    fun getTypeface(context: Context, font: String?): Typeface {
        return Typeface.createFromAsset(context.assets, font)
    }
}