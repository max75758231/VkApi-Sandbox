package maxzonov.vkapi_sandbox.utils

import android.content.Context
import android.util.DisplayMetrics

class ImageViewFormatter(private val context: Context, private val deviceWidthDp: Int) {

    fun getImageViewHeightInDp(imageHeight: Int, imageWidth: Int): Int {

        val imageViewWidthInDevice: Int = deviceWidthDp - 80
        val imageSizeRatio: Double = imageHeight.toDouble() / imageWidth.toDouble()
        return convertDpToPx((imageViewWidthInDevice.toDouble() * imageSizeRatio).toInt())
    }

    private fun convertDpToPx(dp: Int) : Int {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT).toInt()
    }
}