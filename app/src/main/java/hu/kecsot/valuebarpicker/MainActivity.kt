package hu.kecsot.valuebarpicker

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import hu.kecsot.ktvaluebar.KTValueBarParams
import hu.kecsot.ktvaluebar.button.KTButtonParams
import hu.kecsot.ktvaluebar.toPx
import hu.kecsot.ktvaluebar.valuebar.listener.KTValueBarChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val valueBarButtonParam = KTButtonParams.Builder()
                // .setDrawableColor(Color.RED)
                // .setDrawableLeftPadding(5f.toPx)
                // .setDrawableRightPadding(5.toPx)
                // .setDrawableTopPadding(5.toPx)
                // .setDrawableBottomPadding(5f.toPx)
                 .setBackgroundColor(Color.GREEN)
                //.setDrawable(R.mipmap.ic_launcher_round)
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
                // .setHorizontalBorderWidth(10.toPx)
                // .setHorizontalBorderOnlyRemaining(false)
                // .setTouchEnabled(false)
                // .setVerticalBorderColor(Color.MAGENTA)
                // .setVerticalBorderNeeded(false)
                 .setVerticalBorderWidth(10f.toPx)
                // .setBarColorFormatter(GreenToRedFormatter())
                .build()
      //  exampleValueBar.setParams(valueBarParam)

        exampleValueBar.setValueBarChangedListener(object : KTValueBarChangedListener {
            override fun onChanged(newValue: Int) {
                Log.d("onChanged", "New Value: $newValue")
            }
        })



        exampleValueBar.animate(0f, 50f, 2000)
    }
}
