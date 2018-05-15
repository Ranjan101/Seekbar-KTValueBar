package hu.kecsot.ktvaluebar.button

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.kecsot.mylibrary.R
import kotlinx.android.synthetic.main.button_layout.view.*

@SuppressLint("ViewConstructor")
class KTButton(context: Context, var isLeftButton: Boolean, var buttonPosition: ButtonPosition, var params: KTButtonParams) : LinearLayout(context) {

    companion object {
        val DEFAULT_RIGHT_DRAWABLE = R.drawable.baseline_add_white_24
        val DEFAULT_LEFT_DRAWABLE = R.drawable.baseline_remove_white_24
    }

    init {
        View.inflate(context, R.layout.button_layout, this)
        updateProperties()
    }

    /**
     * This method calculate and generate the background of button.
     */
    private fun updateBackground(height: Int) {
        if (height > 0) {
            var topLeftRadius = 0f
            var topRightRadius = 0f
            var bottomLeftRadius = 0f
            var bottomRightRadius = 0f
            val halfRadius = (height / 2).toFloat();

            when (buttonPosition) {

                ButtonPosition.NEXT_TO_VALUEBAR -> {
                    if (isLeftButton) {
                        bottomLeftRadius = halfRadius
                        topLeftRadius = halfRadius
                    } else {
                        bottomRightRadius = halfRadius
                        topRightRadius = halfRadius
                    }
                }
                ButtonPosition.TOP_OF_VALUEBAR -> {
                    topRightRadius = halfRadius
                    topLeftRadius = halfRadius
                }
                ButtonPosition.BOTTOM_OF_VALUEBAR -> {
                    bottomLeftRadius = halfRadius
                    bottomRightRadius = halfRadius
                }
            }

            val gradientDrawable = GradientDrawable()
            gradientDrawable.apply {
                shape = GradientDrawable.RECTANGLE
                setColor(params.backgroundColor)
                cornerRadii = floatArrayOf(topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius)
            }

            background = StateListDrawable().apply {
                addState(intArrayOf(), gradientDrawable)
            }
        }
    }

    private fun updateProperties() {

        // Default parameters
        apply {
            setPadding(params.drawableLeftPadding.toInt(),
                    params.drawableTopPadding.toInt(),
                    params.drawableRightPadding.toInt(),
                    params.drawableBottomPadding.toInt())
        }

        // Load Drawable
        val drawableRes = params.drawable
        drawableRes?.let {
            when (it) {
                is Drawable -> imageView.setImageDrawable(it)
                is Int -> imageView.setImageResource(it)
                is Bitmap -> imageView.setImageBitmap(it)
                else -> Log.d(javaClass.name, "Unsupported type!")
            }
        } ?: kotlin.run {
            imageView.setImageResource(getDefaultImageResId())
        }

        // Full Color image
        params.drawableColor?.let {
            imageView.setColorFilter(it);
        }
    }

    fun getDefaultImageResId(): Int {
        return if (isLeftButton) {
            DEFAULT_LEFT_DRAWABLE
        }else{
            DEFAULT_RIGHT_DRAWABLE
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        updateBackground(parentHeight)
    }


    enum class ButtonPosition {
        NEXT_TO_VALUEBAR,
        TOP_OF_VALUEBAR,
        BOTTOM_OF_VALUEBAR
    }

}