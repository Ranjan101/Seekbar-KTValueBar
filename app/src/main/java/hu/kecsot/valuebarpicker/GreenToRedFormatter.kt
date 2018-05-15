package hu.kecsot.valuebarpicker

import android.graphics.Color

import hu.kecsot.ktvaluebar.valuebar.color.KTBarColorFormatter

class GreenToRedFormatter : KTBarColorFormatter {

    override fun getColor(value: Float, maxVal: Float, minVal: Float): Int {
        val hsv = floatArrayOf(120f * (maxVal - minVal - (value - minVal)) / (maxVal - minVal), 1f, 1f)
        return Color.HSVToColor(hsv)
    }
}
