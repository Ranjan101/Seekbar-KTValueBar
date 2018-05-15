package hu.kecsot.ktvaluebar.valuebar


import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import hu.kecsot.ktvaluebar.KTValueBarParams
import hu.kecsot.ktvaluebar.valuebar.color.KTBarColorFormatter
import hu.kecsot.ktvaluebar.valuebar.listener.KTValueBarChangedListener


internal class ValueBar : View, AnimatorUpdateListener, Animator.AnimatorListener {

    private var params = KTValueBarParams.Builder().build()

    private var minValue = 0f
    private var maxValue = 100f
    private var stepValue = 1f
    private var actualValue = 0f
        set(value) {
            field = if (value in minValue..maxValue) {
                value
            } else if (value > maxValue) {
                maxValue
            } else {
                minValue
            }
            callOnValueChangeListener()
        }

    private lateinit var bar: RectF
    private lateinit var barBackgroundPaint: Paint
    private lateinit var valueBarPaint: Paint
    private lateinit var verticalBorderPaint: Paint
    private lateinit var horizontalBorderPaint: Paint

    private var isTouchEnabled = true
    private var barColorFormatter: KTBarColorFormatter? = null
    private var animator: ObjectAnimator? = null
    private var valueBarChangedListener: KTValueBarChangedListener? = null

    private var gestureDetector: GestureDetector? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun changeParams(paramsKT: KTValueBarParams) {
        this.params = paramsKT
        setValuesByParameters()
    }

    private fun init() {
        bar = RectF()
        barBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        barBackgroundPaint.style = Paint.Style.FILL

        valueBarPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        valueBarPaint.style = Paint.Style.FILL

        verticalBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        verticalBorderPaint.style = Paint.Style.STROKE

        horizontalBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        horizontalBorderPaint.style = Paint.Style.STROKE
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()

        // Draw Background color
        canvas.drawRect(0f, 0f, width, height, barBackgroundPaint)

        // Draw the ValueBar
        barColorFormatter?.let {
            valueBarPaint.color = it.getColor(actualValue, maxValue, minValue)
        }

        val length = width / (maxValue - minValue) * (actualValue - minValue)
        bar.set(0f, 0f, length, height)
        canvas.drawRect(bar, valueBarPaint)


        // Draw Horizontal border
        if (params.isHorizontalBorderNeeded) {
            val valueBarRightPosition = if (params.isOnlyRemainingHorizontalBorder) length else 0f
            canvas.drawLine(valueBarRightPosition, 0f, width, 0f, horizontalBorderPaint);
            canvas.drawLine(valueBarRightPosition, height, width, height, horizontalBorderPaint);
        }

        // Draw Vertical border
        if (params.isVerticalBorderNeeded) {
            canvas.drawLine(0f, 0f, 0f, height, verticalBorderPaint);
            canvas.drawLine(width, 0f, width, height, verticalBorderPaint);
        }
    }


    /**
     * Animates the bar from a specific value to a specific value.
     *
     * @param from
     * @param to
     * @param durationMillis
     */
    fun animate(from: Float, to: Float, durationMillis: Int) {
        var startFromX = from
        var goToX = to

        if (startFromX < minValue) startFromX = minValue
        if (startFromX > maxValue) startFromX = maxValue
        if (goToX < minValue) goToX = minValue
        if (goToX > maxValue) goToX = maxValue

        actualValue = startFromX
        startAnimation(durationMillis.toLong(), goToX)
    }

    /**
     * Animates the bar up from it's minimum value to the specified value.
     *
     * @param to
     * @param durationMillis
     */
    fun animateUp(to: Float, durationMillis: Int) {
        var goToX = to
        if (goToX > maxValue) goToX = maxValue

        startAnimation(durationMillis.toLong(), goToX)
    }

    /**
     * Animates the bar down from it's current value to the specified value.
     *
     * @param to
     * @param durationMillis
     */
    fun animateDown(to: Float, durationMillis: Int) {
        var goToX = to
        if (goToX < minValue) goToX = minValue

        startAnimation(durationMillis.toLong(), goToX)
    }

    private fun startAnimation(durationMillis: Long, newX: Float) {
        animator = ObjectAnimator.ofFloat(this, "actualValue", actualValue, newX)
        animator?.apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = durationMillis
            addUpdateListener(this@ValueBar)
            addListener(this@ValueBar)
            start()
        }
    }

    override fun onAnimationUpdate(va: ValueAnimator) {
        invalidate()
    }

    private fun setValuesByParameters() {
        apply {
            minValue = params.minValue
            maxValue = params.maxValue
            stepValue = params.stepValue
            actualValue = params.actualValue
            isTouchEnabled = params.isTouchEnabled
        }

        verticalBorderPaint.apply {
            strokeWidth = params.verticalBorderWidth
            color = params.verticalBorderColor
        }
        horizontalBorderPaint.apply {
            strokeWidth = params.horizontalBorderWidth
            color = params.horizontalBorderColor
        }
        valueBarPaint.color = params.valueBarColor
        barBackgroundPaint.color = params.valueBarBackgroundColor

        gestureDetector = params.gestuseDetector
        barColorFormatter = params.barColorFormatter
    }

    /**
     * Sets a selectionlistener for callbacks when selecting values on the
     * ValueBar.
     *
     * @param l
     */
    fun setValueBarChangedListener(l: KTValueBarChangedListener) {
        valueBarChangedListener = l
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        if (isTouchEnabled) {

            // if the detector recognized a gesture, consume it
            if (gestureDetector != null && gestureDetector!!.onTouchEvent(motionEvent))
                return true

            val x = motionEvent.x
            val y = motionEvent.y

            when (motionEvent.action) {

                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                    updateValue(x, y)
                    invalidate()

                    if (motionEvent.action.equals(ACTION_DOWN)) {
                        updateValue(x, y)
                        invalidate()
                    }

                    callOnValueChangeListener()
                }

            }
            return true
        } else
            return super.onTouchEvent(motionEvent)
    }

    /**
     * Updates the value on the ValueBar depending on the touch position.
     *
     * @param x
     * @param y
     */
    private fun updateValue(x: Float, y: Float) {
        var newActualValue = if (x <= 0)
            minValue
        else if (x > width)
            maxValue
        else {
            val factor = x / width
            (maxValue - minValue) * factor + minValue
        }

        if (stepValue > 0f) {
            val remainder = newActualValue % stepValue
            if (remainder <= stepValue / 2f) {
                newActualValue -= remainder
            } else {
                newActualValue = newActualValue - remainder + stepValue
            }
        }

        actualValue = newActualValue
    }

    fun decreaseValue() {
        actualValue -= stepValue
        invalidate()
    }

    fun increaseValue() {
        actualValue += stepValue
        invalidate()
    }

    fun callOnValueChangeListener() {
        valueBarChangedListener?.onChanged(actualValue.toInt())
    }

    override fun onAnimationStart(animation: Animator?) {
        isTouchEnabled = false
    }

    override fun onAnimationRepeat(animation: Animator?) {
        isTouchEnabled = false
    }

    override fun onAnimationCancel(animation: Animator?) {
        // Set back to default
        isTouchEnabled = params.isTouchEnabled
    }

    override fun onAnimationEnd(animation: Animator?) {
        // Set back to default
        isTouchEnabled = params.isTouchEnabled
    }
}
