package main;

import util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//游戏的前景类
public class GameFrontGround {
//    云彩的个数
    private static final int CLOUND_COUNT=2;
//    存放云彩的容器
    private List<Cloud> clouds;
//    云彩的飞行速度
    private static final int CLOUND_SPEED=1;
//    使用图片资源
    private BufferedImage[] img;
//  随机造函数
    private Random random;

//    构造器初始化数据


    public GameFrontGround() {
        clouds=new ArrayList<>();
        img=new BufferedImage[CLOUND_COUNT];
        random=new Random();
        for (int i = 0; i < CLOUND_COUNT; i++) {
            img[i]= GameUtil.lodeBufferedImage("img/img/cloud"+i+".png");
        }
    }

    public void draw(Graphics g){
        logic();
        for (int i = 0; i < clouds.size(); i++) {
                clouds.get(i).draw(g);
            }



    }
//    用于控制云彩的个数
    private void logic() {
        if ((int) (500 * Math.random()) < 5) {
            Cloud cloud = new Cloud(img[random.nextInt(CLOUND_COUNT)], CLOUND_SPEED, 600, random.nextInt(150));
            clouds.add(cloud);
        }
        for (int i = 0; i < clouds.size(); i++) {
            Cloud cloud=clouds.get(i);
            if(cloud.isOutFrame()){
                clouds.remove(i);
                i--;
                System.out.println("云被移除了");
            }



        }
    }
}
