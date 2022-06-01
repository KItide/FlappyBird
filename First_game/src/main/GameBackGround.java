package main;

import util.Constant;
import util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Constant.*;

public class GameBackGround {
//    背景需要的资源图片
    private BufferedImage bkimg;
//    构造器初始化资源
    public GameBackGround(){
         bkimg= GameUtil.lodeBufferedImage(Constant.BK_IMG_OATH);
    }
    public void draw(Graphics g){
//        填充背景色
        g.setColor(BK_COLOR);
        g.fillRect(0,0,FRAM_WIDRH,FRAM_HEIGHT);
        g.setColor(Color.black);
//  得到图片的高度和宽度
        int height =bkimg.getHeight();
        int width = bkimg.getWidth();
//        所需要图片的张数
        int count=Constant.FRAM_WIDRH/width+1;
        for (int i=0;i<count;i++){
            g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);

        }

    }
}
