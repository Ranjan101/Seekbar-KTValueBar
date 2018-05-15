package hu.kecsot.ktvaluebar.button

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import com.kecsot.mylibrary.R
import hu.kecsot.ktvaluebar.toPx


data class KTButtonParams(
        var drawable: Any?,
        var drawableColor: Int?,
        var backgroundColor: Int,
        var drawableLeftPadding: Float,
        var drawableRightPadding: Float,
        var drawableBottomPadding: Float,
        var drawableTopPadding: Float) {
    class Builder {

        private var drawable: Any? = null
        private var drawableColor: Int? = null
        private var backgroundColor = Color.BLACK
        private var drawableLeftPadding = 8f.toPx
        private var drawableRightPadding = 8f.toPx
        private var drawableBottomPadding = 8f.toPx
        private var drawableTopPadding = 8f.toPx


        fun setDrawable(drawable: Drawable) = apply { this.drawable = drawable }
        fun setDrawable(resId: Int) = apply { this.drawable = resId }
        fun setDrawable(bitmap: Bitmap) = apply { this.drawable = bitmap }
        fun setDrawableColor(color: Int) = apply { this.drawableColor = color }
        fun setBackgroundColor(color: Int) = apply { this.backgroundColor = color }
        fun setDrawableLeftPadding(leftPadding: Float) = apply { this.drawableLeftPadding = leftPadding }
        fun setDrawableRightPadding(rightPadding: Float) = apply { this.drawableRightPadding = rightPadding }
        fun setDrawableTopPadding(topPadding: Float) = apply { this.drawableTopPadding = topPadding }
        fun setDrawableBottomPadding(bottomPadding: Float) = apply { this.drawableBottomPadding = bottomPadding }

        fun build(): KTButtonParams {
            return KTButtonParams(
                    drawable,
                    drawableColor,
                    backgroundColor,
                    drawableLeftPadding,
                    drawableRightPadding,
                    drawableBottomPadding,
                    drawableTopPadding
            )
        }
    }
}