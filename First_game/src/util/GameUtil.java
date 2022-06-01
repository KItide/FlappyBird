package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameUtil {
    public static BufferedImage lodeBufferedImage(String imgpath) {
        try {
            return ImageIO.read(new FileInputStream(imgpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
