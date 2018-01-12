package utils;

import java.util.Random;

/**
 * Created by r028367 on 12/01/2018.
 */

public class ColorUtils {

    private static final Random random = new Random();

    public static final int getRandomColor(int lowerBound, int upperBound) {
        if(lowerBound < 0)
            lowerBound = 0;
        else if(lowerBound > 255)
            lowerBound %= 255;
        if(upperBound < 0)
            upperBound = 0;
        else if(upperBound > 255)
            upperBound %= 255;
        // swap usando XOR operator
        if(upperBound < lowerBound) {
            upperBound ^= lowerBound;
            lowerBound ^= upperBound;
            upperBound ^= lowerBound;
        }

        int r = random.nextInt(upperBound - lowerBound) + lowerBound;
        int g = random.nextInt(upperBound - lowerBound) + lowerBound;
        int b = random.nextInt(upperBound - lowerBound) + lowerBound;
        return 0xff000000 + (r << 16) + (g << 8) + b;
    }
}
