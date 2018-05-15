package hu.kecsot.ktvaluebar

import android.graphics.Color
import android.view.GestureDetector
import hu.kecsot.ktvaluebar.button.KTButton
import hu.kecsot.ktvaluebar.button.KTButtonParams
import hu.kecsot.ktvaluebar.valuebar.color.KTBarColorFormatter


open class KTValueBarParams(
        var valueBarButtonsPosition: KTButton.ButtonPosition,
        var leftButtonParams: KTButtonParams,
        var rightButtonParams: KTButtonParams,

        var minValue: Float,
        var maxValue: Float,
        var actualValue: Float,
        var stepValue: Float,

        // Bar
        var valueBarColor: Int,
        var valueBarBackgroundColor: Int,
        var barColorFormatter: KTBarColorFormatter?,

        // Vertical border
        var isVerticalBorderNeeded: Boolean,
        var verticalBorderWidth: Float,
        var verticalBorderColor: Int,

        // Horizontal border
        var isHorizontalBorderNeeded: Boolean,
        var horizontalBorderWidth: Float,
        var horizontalBorderColor: Int,
        var isOnlyRemainingHorizontalBorder: Boolean,

        // Controllers
        var isTouchEnabled: Boolean,
        var gestuseDetector: GestureDetector?
) {
    class Builder {
        private var valueBarButtonsPosition = KTButton.ButtonPosition.NEXT_TO_VALUEBAR
        private var leftButtonParams = KTButtonParams.Builder().build()
        private var rightButtonParams = KTButtonParams.Builder().build()

        private var minValue = 0f
        private var maxValue = 100f
        private var actualValue = 0f
        private var stepValue = 1f

        // Bar
        private var valueBarColor = Color.CYAN
        private var valueBarBackgroundColor = Color.TRANSPARENT
        private var barColorFormatter: KTBarColorFormatter? = null

        // Vertical border
        private var isVerticalBorderNeeded = true
        private var verticalBorderWidth = 2f.toPx
        private var verticalBorderColor = Color.CYAN

        // Horizontal border
        private var isHorizontalBorderNeeded = true
        private var horizontalBorderWidth = 2f.toPx
        private var horizontalBorderColor = Color.CYAN
        private var isOnlyRemainingHorizontalBorder = true

        // Controllers
        private var isTouchEnabled = true
        private var gestureDetector: GestureDetector? = null

        fun setValueBarButtonsPosition(position: KTButton.ButtonPosition) = apply {
            this.valueBarButtonsPosition = position
        }

        fun setLeftButtonParams(buttonParams: KTButtonParams) = apply {
            this.leftButtonParams = buttonParams
        }

        fun setRightButtonParams(buttonParams: KTButtonParams) = apply {
            this.rightButtonParams = buttonParams
        }

        fun setMinValue(value: Float) = apply {
            this.minValue = value
        }

        fun setMaxValue(value: Float) = apply {
            this.maxValue = value
        }

        fun setActualValue(value: Float) = apply {
            this.actualValue = value
        }

        fun setStepValue(value: Float) = apply {
            this.stepValue = value
        }

        fun setValueBarColor(color: Int) = apply {
            this.valueBarColor = color
        }

        fun setValueBarBackgroundColor(color: Int) = apply {
            this.valueBarBackgroundColor = color
        }

        fun setVerticalBorderNeeded(isNeed: Boolean) = apply {
            this.isVerticalBorderNeeded = isNeed
        }

        fun setVerticalBorderWidth(width: Float) = apply {
            this.verticalBorderWidth = width
        }

        fun setVerticalBorderColor(color: Int) = apply {
            this.verticalBorderColor = color
        }

        fun setHorizontalBorderNeeded(isNeed: Boolean) = apply {
            this.isHorizontalBorderNeeded = isNeed
        }

        fun setHorizontalBorderWidth(width: Float) = apply {
            this.horizontalBorderWidth = width
        }

        fun setHorizontalBorderColor(color: Int) = apply {
            this.horizontalBorderColor = color
        }

        fun setHorizontalBorderOnlyRemaining(isNeed: Boolean) = apply {
            this.isOnlyRemainingHorizontalBorder = isNeed
        }

        fun setTouchEnabled(isEnabled: Boolean) = apply {
            this.isTouchEnabled = isEnabled
        }

        fun setGestureDetector(gestureDetector: GestureDetector) = apply {
            this.gestureDetector = gestureDetector
        }

        fun setBarColorFormatter(barColorFormatter: KTBarColorFormatter) = apply {
            this.barColorFormatter = barColorFormatter
        }

        fun build(): KTValueBarParams {
            return KTValueBarParams(valueBarButtonsPosition,
                    leftButtonParams,
                    rightButtonParams,
                    minValue,
                    maxValue,
                    actualValue,
                    stepValue,
                    valueBarColor,
                    valueBarBackgroundColor,
                    barColorFormatter,
                    isVerticalBorderNeeded,
                    verticalBorderWidth,
                    verticalBorderColor,
                    isHorizontalBorderNeeded,
                    horizontalBorderWidth,
                    horizontalBorderColor,
                    isOnlyRemainingHorizontalBorder,
                    isTouchEnabled,
                    gestureDetector
            )
        }

    }

}