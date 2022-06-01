package main;

import util.Constant;
import util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Barrier {
    private static  BufferedImage[] images;
    static {
        final int COUNT=3;
        images=new BufferedImage[COUNT];
        for (int i = 0; i < COUNT; i++) {
            images[i]= GameUtil.lodeBufferedImage(Constant.BARRIER_IMG_PATH[i]);
        }

    }
//    矩形参数
    private Rectangle rectangle;
    private boolean mob=true;
//    位置
    private int x,y;
//    障碍物的存活状态
    private boolean visible;
//    宽度和高度
    private int width,heigth;
//    障碍物移动速度
    private int speed=3;
//    障碍物类型
    private int type;
    private static final int TYPE_TOP_NORMAL=0;
    private static final int TYPE_BOTTOM_NORMAL=2;
    private static final int TYPE_HOVER_NORMAL=4;
    private static final int TYPE_MOBILE=6;

    public Barrier (){
        rectangle=new Rectangle();
    }
//    获得障碍物的宽度和高度
    public static final int BARRIER_WIDTH=images[0].getWidth();
    public static final int BARRIER_HEIGTH=images[0].getHeight();
    public static final int BARRIER_HEAD_WIDTH=images[1].getWidth();
    public static final int BARRIER_HEAD_HEIGTH=images[1].getHeight();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Barrier(int x, int y, int heigth, int type) {
        this.x = x;
        this.y = y;
        this.width =BARRIER_WIDTH ;
        this.heigth = heigth;
        this.type = type;
    }
//    根据不同的类型绘制障碍物
public void draw(Graphics g){
        switch (type){
            case TYPE_TOP_NORMAL:
                drawTopNormal(g);
                break;
            case TYPE_BOTTOM_NORMAL:
                drawNormalTop(g);
                break;
            case TYPE_HOVER_NORMAL:
                drawHoverNormal(g);
                break;
            case TYPE_MOBILE:
                drawMobile(g);
                break;
        }

    }
//    绘制从上往下的障碍物
    private void drawTopNormal(Graphics g){
        int count=(heigth-BARRIER_HEAD_HEIGTH)/BARRIER_HEIGTH+1;
        for (int i = 0; i < count; i++) {
            g.drawImage(images[0],x,y+i*BARRIER_HEIGTH,null);
        }
//        绘制头
        int y=heigth-BARRIER_HEAD_HEIGTH;
        g.drawImage(images[2],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
        x-=speed;
        if(x<-50){
            visible=false;
        }
        rect(g);
    }
    //绘制从下往上的障碍物
    private void drawNormalTop(Graphics g){
        int count=heigth/BARRIER_HEIGTH+1;
        for (int i = 0; i < count; i++) {
            g.drawImage(images[0],x,Constant.FRAM_HEIGHT-i*BARRIER_HEIGTH,null );
        }
        int y=Constant.FRAM_HEIGHT-heigth;
        g.drawImage(images[1],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null );
        x-=speed;
        if(x<-50){
            visible=false;
        }
        rect(g);

    }
    //绘制中间的障碍物
    private void drawHoverNormal(Graphics g){
        int count=(heigth-BARRIER_HEAD_HEIGTH)/BARRIER_HEIGTH;
//        绘制上头
        g.drawImage(images[1],x,y,null );
        for (int i = 0; i < count; i++) {
            g.drawImage(images[0],x,y+BARRIER_HEAD_HEIGTH+i*BARRIER_HEIGTH,null );
        }
        rect(g);
//        绘下头
        int yll=y+heigth-BARRIER_HEAD_HEIGTH;
        g.drawImage(images[2],x,yll,null );
        x-=speed;
        if(x<-50){
            visible=false;
        }
        rect(g);

    }
    //绘制可以移动的的障碍物
    private void drawMobile(Graphics g){
        int count=(heigth-BARRIER_HEAD_HEIGTH)/BARRIER_HEIGTH;
//        绘制上头
        g.drawImage(images[1],x,y,null );
        for (int i = 0; i < count; i++) {
            g.drawImage(images[0],x,y+BARRIER_HEAD_HEIGTH+i*BARRIER_HEIGTH,null );
        }
        rect(g);
//        绘下头
        int yll=y+heigth-BARRIER_HEAD_HEIGTH;
        g.drawImage(images[2],x,yll,null );
        x-=speed;
        if(x<-50){
            visible=false;
        }
        if (mob){
            y+=5;
            if (y>=250){
                mob=false;
            }
        }else if(!mob){
            y-=5;
            if (y<100){
                mob=true;
            }
        }
        rect(g);

    }
//    判断什么时候绘制下个障碍物
    public boolean isInFrame(){
        return 600-x>150;
    }
//
    public  void  rect(Graphics g){
        int x1=this.x;
        int y1=this.y;
        int w1=images[0].getWidth();
        g.setColor(Color.BLUE);
        g.drawRect(x1,y1,w1,heigth);
        setRect(x1,y1,w1,heigth);


    }
    //        障碍物的碰撞矩形参数
    public void setRect(int x,int y,int width,int heigth){
        rectangle.x=x;
        rectangle.y=y;
        rectangle.width=width;
        rectangle.height=heigth;

    }
    public Rectangle getRect(){
        return rectangle;
    }

}

