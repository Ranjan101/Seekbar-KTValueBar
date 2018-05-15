# Images
<img width="300" alt="screenshot" src="https://github.com/kecsot/KTValueBar/blob/master/screenshots/types.png"><img width="350" alt="screenshot" src="https://github.com/kecsot/KTValueBar/blob/master/screenshots/image1.png">

# About the library
In this library I used the "PhilJay/ValueBar" repository. Thank you for him!


**Supporting API level 17+**

# Usage
**Gradle** dependency:
**Add it in your root build.gradle at the end of repositories:**
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**Add the dependency**
```
dependencies {
        implementation 'com.github.kecsot:KTValueBar:0.2.0'
}
```

#### Add a View to your layout
```
<hu.kecsot.ktvaluebar.KTValueBar
    android:id="@+id/exampleValueBar"
    android:layout_width="match_parent"
    android:layout_height="60dp" />
```
#### Set the Listener 
```
exampleValueBar.setValueBarChangedListener(object : KTValueBarChangedListener {
    override fun onChanged(newValue: Int) {

    }
})
```
## Types
1. Default: (NEXT_TO_VALUEBAR)
2. Top: (TOP_OF_VALUEBAR)
3. Bottom: (BOTTOM_OF_VALUEBAR)

<img width="650" alt="screenshot" src="https://github.com/kecsot/KTValueBar/blob/master/screenshots/types.png">

View:
```
app:valueBarType="topOfValueBar"
```
Code:
```
val params = exampleValueBar.getParams()
params.apply {
    valueBarButtonsPosition = KTValueBar.TypeOfValueBar.TOP_OF_VALUEBAR
}
exampleValueBar.setParams(params)
```


## Set View attributes
You can set all parameter from the View. Like this:
```
    <hu.kecsot.ktvaluebar.KTValueBar
        android:id="@+id/exampleValueBar"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        app:actualValue="50"
        app:horizontalBorderColor="@color/colorAccent"
        app:horizontalBorderOnlyRemaining="false"
        app:horizontalBorderWidth="8dp"
        app:maxValue="250"
        app:minValue="20"
        app:rightButtonBackgroundColor="@color/colorAccent"
        app:rightButtonRightPadding="50dp"
        app:leftButtonLeftPadding="50dp"
        app:stepValue="2"
        app:valueBarBackgroundColor="@color/colorPrimary"
        app:valueBarColor="@color/colorAccent"
        app:verticalBorderColor="@color/colorAccent"
        app:verticalBorderWidth="8dp" />
```
#### Here is a list from the attrs.xml
```
        app:minValue="float"
        app:maxValue="float"
        app:actualValue="float"
        app:stepValue="float"

        app:valueBarColor="color"
        app:valueBarBackgroundColor="color"
        app:isTouchEnabled="boolean"

        app:horizontalBorderColor="color"
        app:horizontalBorderNeeded="boolean"
        app:horizontalBorderWidth="dimension"
        app:horizontalBorderOnlyRemaining="boolean"

        app:verticalBorderColor="color"
        app:verticalBorderNeeded="boolean"
        app:verticalBorderWidth="dimension"

        app:rightButtonDrawable="reference"
        app:rightButtonDrawableColor="color"
        app:rightButtonLeftPadding="dimension"
        app:rightButtonRightPadding="dimension"
        app:rightButtonTopPadding="dimension"
        app:rightButtonBottomPadding="dimension"
        app:rightButtonBackgroundColor="color"

        app:leftButtonDrawable="reference"
        app:leftButtonDrawableColor="color"
        app:leftButtonLeftPadding="dimension"
        app:leftButtonRightPadding="dimension"
        app:leftButtonTopPadding="dimension"
        app:leftButtonBottomPadding="dimension"
        app:leftButtonBackgroundColor="color"
        
        <attr name="valueBarType" format="integer">
            <flag name="nextToValueBar" value="0" />
            <flag name="topOfValueBar" value="1" />
            <flag name="bottomOfValueBar" value="2" />
        </attr>
```
## Change parameters programmatically
When you set a lot of attributes on the xml then you can set new params programmatically.
Just get the params and change them!
```
       val params = exampleValueBar.getParams()
        params.apply {
            valueBarColor = Color.RED
        }
        exampleValueBar.setParams(params)
```

## Animate

```
exampleValueBar.animate(0f, 50f, 2000)
```

## ColorFormatter

Create your own Formatter Like this:
```
class GreenToRedFormatter : KTBarColorFormatter {

    override fun getColor(value: Float, maxVal: Float, minVal: Float): Int {
        val hsv = floatArrayOf(120f * (maxVal - minVal - (value - minVal)) / (maxVal - minVal), 1f, 1f)
        return Color.HSVToColor(hsv)
    }
}
```

Set the formatter
```
val params = exampleValueBar.getParams()
params.apply {
    barColorFormatter = GreenToRedFormatter()

}
exampleValueBar.setParams(params)
```

## Builder
You can set programmatically the KTValueBar by using the Builder().
When you using the Builder, than your **layout settings will be overrided.** 
```
        val valueBarButtonParam = KTButtonParams.Builder()
                // .setDrawableColor(Color.RED)
                // .setDrawableLeftPadding(5f.toPx)
                // .setDrawableRightPadding(5f.toPx)
                // .setDrawableTopPadding(5f.toPx)
                // .setDrawableBottomPadding(5f.toPx)
                // .setBackgroundColor(Color.GREEN)
                // .setDrawable(R.mipmap.ic_launcher_round)
                .build()

        val valueBarParam = KTValueBarParams.Builder()
                // .setValueBarBackgroundColor(Color.TRANSPARENT)
                // .setValueBarColor(Color.BLUE)
                // .setMinValue(0)
                // .setActualValue(100)
                // .setMaxValue(200)
                // .setStepValue(2)
                .setRightButtonParams(valueBarButtonParam)
                .setLeftButtonParams(valueBarButtonParam)
                // .setHorizontalBorderColor(Color.BLUE)
                // .setHorizontalBorderNeeded(true)
                // .setHorizontalBorderWidth(10f.toPx)
                // .setHorizontalBorderOnlyRemaining(false)
                // .setTouchEnabled(false)
                // .setVerticalBorderColor(Color.MAGENTA)
                // .setVerticalBorderNeeded(false)
                // .setVerticalBorderWidth(10f.toPx)
                // .setBarColorFormatter(GreenToRedFormatter())
                .build()
        exampleValueBar.setParams(valueBarParam)
```

### Propetries of ViewBar  
#### KTValueBarParams.Builder()
```
fun setTypeOfValueBar(TypeOfValueBar: KTValueBar.TypeOfValueBar) 
fun setLeftButtonParams(buttonParams: KTButtonParams) 
fun setRightButtonParams(buttonParams: KTButtonParams)
fun setMinValue(value: Float) 
fun setMaxValue(value: Float) 
fun setActualValue(value: Float)
fun setStepValue(value: Float) 
fun setValueBarColor(color: Int)
fun setValueBarBackgroundColor(color: Int)
fun setVerticalBorderNeeded(isNeed: Boolean)
fun setVerticalBorderWidth(width: Float) 
fun setVerticalBorderColor(color: Int) 
fun setHorizontalBorderNeeded(isNeed: Boolean) 
fun setHorizontalBorderWidth(width: Float) 
fun setHorizontalBorderColor(color: Int) 
fun setHorizontalBorderOnlyRemaining(isNeed: Boolean)
fun setTouchEnabled(isEnabled: Boolean) 
fun setGestuseDetector(gestuseDetector: GestureDetector) 
fun setBarColorFormatter(barColorFormatter: KTBarColorFormatter)
```

###  Properties of Buttons
#### KTButtonParams.Builder()
```
fun setDrawable(drawable: Drawable) 
fun setDrawable(resId: Int) 
fun setDrawable(bitmap: Bitmap) 
fun setDrawableColor(color: Int) 
fun setBackgroundColor(color: Int) 
fun setDrawableLeftPadding(leftPadding: Float)
fun setDrawableRightPadding(rightPadding: Float) 
fun setDrawableTopPadding(topPadding: Float) 
fun setDrawableBottomPadding(bottomPadding: Float) 
```



[![Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donate_SM.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=8DU35VL4CCL5L)
