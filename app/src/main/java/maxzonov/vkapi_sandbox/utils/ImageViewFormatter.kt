package maxzonov.vkapi_sandbox.utils

import android.content.Context
import android.util.DisplayMetrics

class ImageViewFormatter(private val context: Context, private val deviceWidthDp: Int) {

    fun getImageViewHeightInPx(imageHeight: Int, imageWidth: Int): Int {

        val imageViewWidthInDevice: Int = deviceWidthDp - 80
        val imageSizeRatio: Double = imageHeight.toDouble() / imageWidth.toDouble()
        return convertDpToPx((imageViewWidthInDevice.toDouble() * imageSizeRatio).toInt(), getDeviceDpi())
    }

    fun getImageViewHeightInDp(imageHeight: Int, imageWidth: Int): Int {
        val imageViewWidthInDevice: Int = deviceWidthDp - 80
        val imageSizeRatio: Double = imageHeight.toDouble() / imageWidth.toDouble()
        return (imageViewWidthInDevice.toDouble() * imageSizeRatio).toInt()
    }

    private fun getDeviceDpi(): Int {
        return context.resources.displayMetrics.densityDpi
    }

    private fun convertDpToPx(dp: Int, deviceDpi: Int) : Int {
        return dp * (deviceDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT).toInt()
    }
}