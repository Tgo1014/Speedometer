package tgo1014.speedometer

import android.animation.ObjectAnimator
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RotateDrawable
import android.widget.ImageView

object SpeedometerHelper {

    /**
     * Calculate the speedometer fill angle based on the informed values
     *
     * @param speedometerLayer          Layer with the speedometer
     * @param speedomaterImageView      ImageView where the speedometer is
     * @param desiredValue              Desired value in the speedometer
     * @param maxValue                  Max speedometer value
     */
    fun moveSpeedometer(speedometerLayer: LayerDrawable,
                        speedomaterImageView: ImageView,
                        desiredValue:
                        Float, maxValue: Float) {

        //Do nothing if empty
        if (desiredValue == 0f)
            return

        var mutableDesiredValue = desiredValue

        //don't allow the desired value be higher than the max value
        if (mutableDesiredValue > maxValue)
            mutableDesiredValue = maxValue

        val desiredValuePercentage = (mutableDesiredValue / maxValue * 100).toInt()

        val maxRotationvalue = 5000

        var percentualFakeBg = desiredValuePercentage
        if (percentualFakeBg != 100) percentualFakeBg++ //the fake bg must be a little foward to show the pointer

        val pointerAngle = maxRotationvalue * desiredValuePercentage / 100
        val fakeBgAngle = maxRotationvalue * percentualFakeBg / 100

        /*
         Mutate is necessary, otherwise, the transformation is applyed to all equals
         speedometer LayerDrawables, meaning repeated values in case you use more than one speedometer
         */
        val speedometer = speedometerLayer.mutate() as LayerDrawable

        val pointer = speedometer.findDrawableByLayerId(R.id.item_speedometer_pointer) as RotateDrawable
        val fakeBg = speedometer.findDrawableByLayerId(R.id.item_speedometer_fake_empty_bg) as RotateDrawable

        //animate the transformation
        ObjectAnimator.ofInt(pointer, "level", pointerAngle).setDuration(1500).start()
        ObjectAnimator.ofInt(fakeBg, "level", fakeBgAngle).setDuration(1500).start()

        speedomaterImageView.setImageDrawable(speedometerLayer)
    }

    /**
     * Calculate the speedometer fill angle based on the informed values
     *
     * @param speedometerLayer          Layer with the speedometer
     * @param speedomaterImageView      ImageView where the speedometer is
     * @param desiredValue              Desired value in the speedometer
     * @param maxValue                  Max speedometer value
     */
    fun moveSpeedometer(speedometerLayer: LayerDrawable, imageView: ImageView, desiredValue: Int, maxValue: Int) {
        moveSpeedometer(speedometerLayer, imageView, desiredValue.toFloat(), maxValue.toFloat())
    }
}