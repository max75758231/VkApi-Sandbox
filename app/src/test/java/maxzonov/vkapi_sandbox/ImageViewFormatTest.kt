package maxzonov.vkapi_sandbox

import android.content.Context
import android.util.Log

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

import maxzonov.vkapi_sandbox.utils.ImageViewFormatter

@RunWith(PowerMockRunner::class)
@PrepareForTest(ImageViewFormatter::class, Log::class)
class ImageViewFormatTest {

    private lateinit var context: Context

    companion object {
        private const val DEVICE_WIDTH_DP = 360
    }

    @Before
    fun setup() {
        context = PowerMockito.mock(Context::class.java)
        PowerMockito.mockStatic(Log::class.java)
    }

    @Test
    fun testingImageViewHeightInDp_Portrait() {
        Assert.assertEquals(373, ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(1600, 1200))
        Assert.assertEquals(497, ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(1920, 1080))
    }

    @Test
    fun testingImageViewHeightInDp_Square() {
        Assert.assertEquals(280, ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(1000, 1000))
    }

    @Test
    fun testingImageViewHeightInDp_Landscape() {
        Assert.assertEquals(210, ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(600, 800).toLong())
        Assert.assertEquals(14, ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(100, 2000))
    }
}
