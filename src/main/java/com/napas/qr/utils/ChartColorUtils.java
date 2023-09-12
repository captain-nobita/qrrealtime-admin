package com.napas.qr.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChartColorUtils {
    private static final String arrayColor[] = {"blue", "red", "purple", "orange", "lime", "violet", "green", "chocolate",
            "blueviolet",
            "brown", "cadetblue", "chartreuse", "coral", "cornflowerblue", "crimson", "darkblue",
            "darkcyan", "darkgoldenrod", "darkgreen", "darkmagenta", "darkolivegreen", "darkorange", "darkorchid", "darkred",
            "darkslateblue", "darkslategray", "darkviolet", "deeppink", "forestgreen", "fuchsia", "goldenrod", "indianred",
            "indigo", "lightcoral", "limegreen", "magenta", "maroon", "mediumblue", "midnightblue", "navy", "olive",
            "olivedrab", "orangered", "orchid", "peru", "royalblue", "saddlebrown", "salmon", "sandybrown",
            "seagreen", "sienna", "steelblue", "teal", "tan", "tomato"};

    public static String getColorByIndex(int index) {
        return arrayColor[index];
    }
}
