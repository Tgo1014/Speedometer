package tgo1014.speedometer

import android.graphics.drawable.LayerDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        speedometerDemo()
    }

    private fun speedometerDemo() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread({
                    SpeedometerHelper.moveSpeedometer(getDrawable(R.drawable.speedometer) as LayerDrawable,
                            mainIvSpeedometerLeft,
                            Random().nextInt(101),
                            100)
                })
            }
        }, 0, 2500)
    }
}
