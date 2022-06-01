package main;

import util.Constant;
import util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;

public class Brid {
//    小鸟的加速度
    private int acc;
//    小鸟的生命值
    public boolean life=true;
    //    小鸟矩形对象
    private Rectangle rectangle;
    //    存放小鸟的照片
    private BufferedImage[] images;
    public static final int BIRD_IMG_COUNT = 3;
    //    鸟的状态
    private int state;
    public static final int STATE_NORMAR = 0;//平飞
    public static final int STATE_UP = 1;//上飞
    public static final int STATE_DOWN = 2;//下飞

    //    小鸟移动的方向 上下
    private boolean up = false, dowm = false;
    //    小鸟的移动速度
    private int speed = 4;
    private int x = 200, y = 200;

    //    构造方法中资源初始化
    public Brid() {
        images = new BufferedImage[BIRD_IMG_COUNT];
        for (int i = 0; i < BIRD_IMG_COUNT; i++) {
            images[i] = GameUtil.lodeBufferedImage(Constant.BIRD_IMG[i]);

        }
        int w = images[0].getWidth();
        int h = images[0].getHeight();
        rectangle = new Rectangle(w, h);


    }

    //    绘制小鸟
    public void draw(Graphics g) {
        flyLogic();
        g.drawImage(images[state], x, y, null);
//        绘制出小鸟的矩形
//        g.drawRect(x, y, (int) rectangle.getWidth(), (int) rectangle.getHeight());
        rectangle.x = this.x;
        rectangle.y = this.y;

    }

    //    控制小鸟移动的方向
    public void flyLogic() {
        if (up) {
            acc--;
            y+=acc;
            if (y<-10){
                acc=-10;
            }
            if (y < 20) {
                y = 20;
                acc=0;
            }

        }
        if (!up) {
            acc++;
            y+=acc;
            if(acc>10){
                acc=10;
            }
            if (y > 500) {
                y = 500;
                acc=0;
            }
        }
    }

    public void fly(int fly) {
        switch (fly) {
            case 1:
                state = 1;
                up = true;
                break;
            case 5:
                state = 2;
                up = false;
                break;
        }

        }
    public Rectangle getRect () {
        return rectangle;}

    //    重新绘制小鸟的位置
    public void restartDraw(){
        life=true;
        x=200;
        y=200;
    }
}

