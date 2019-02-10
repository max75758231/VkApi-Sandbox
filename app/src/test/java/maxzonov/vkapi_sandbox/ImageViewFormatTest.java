package maxzonov.vkapi_sandbox;

import android.content.Context;
import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import maxzonov.vkapi_sandbox.utils.ImageViewFormatter;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ImageViewFormatter.class, Log.class})
public class ImageViewFormatTest {

    private Context context;
    private static final int DEVICE_WIDTH_DP = 360;

    @Before
    public void setup() {
        context = PowerMockito.mock(Context.class);
        PowerMockito.mockStatic(Log.class);
    }

    @Test
    public void testingImageViewHeightInDp_Portrait() {
        Assert.assertEquals(373, new ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(1600, 1200));
        Assert.assertEquals(497, new ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(1920, 1080));
    }

    @Test
    public void testingImageViewHeightInDp_Square() {
        Assert.assertEquals(280, new ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(1000, 1000));
    }

    @Test
    public void testingImageViewHeightInDp_Landscape() {
        Assert.assertEquals(210, new ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(600, 800));
        Assert.assertEquals(14, new ImageViewFormatter(context, DEVICE_WIDTH_DP).getImageViewHeightInDp(100, 2000));
    }
}
