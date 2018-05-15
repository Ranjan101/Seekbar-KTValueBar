package hu.kecsot.ktvaluebar

import android.content.res.Resources

// Create PX from DP like 2dp.toPx = 2.toPx
val Float.toPx: Float get() = (this * Resources.getSystem().displayMetrics.density)
val Int.toPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
