package main;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import static util.Constant.*;

public class GameFrame extends Frame {
//    实例化gamebackGround类
    private GameBackGround gameBackGround;
//    实例化鸟类
    private Brid brid;
//    实例化GameFrontGround
    private GameFrontGround gameFrontGround;
//    实例化GameBarrierLayer
    private GameBarrierLayer gameBarrierLayer;
//存放图片的图片
    private BufferedImage buffimg=new BufferedImage(FRAM_WIDRH,FRAM_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
    public GameFrame (){
//        窗口是否可见
        setVisible(true);
//        窗口的大小
        setSize(FRAM_WIDRH, FRAM_HEIGHT);
//        窗口的标题
        setTitle(FRAM_TITLE);
//        窗口的初始化位置
        setLocation(FRAM_X,FRAM_Y);
        setResizable(false);
//        窗口关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//结束运行
            }
        });
        initGamg();
        new run().start();

        //    添加按键监听器
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);
            }
        });

    }
//    初始化游戏对象
    public void initGamg(){
        gameBackGround=new GameBackGround();
        brid=new Brid();
        gameFrontGround=new GameFrontGround();
        gameBarrierLayer=new GameBarrierLayer();
    }

    class run extends Thread{
        @Override
        public void run() {
            while (true){
                repaint();
                try {
                   Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            }
        }

    }
    @Override
    public void update(Graphics g){
        if(brid.life){
        Graphics graphics = buffimg.getGraphics();
        gameBackGround.draw(graphics);
        brid.draw(graphics);
        g.drawImage(buffimg,0,0,null);
        gameFrontGround.draw(g);
        gameBarrierLayer.draw(g,brid);
        }else {
            String over="游戏结束";
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",1,60));
            g.drawString(over,250,250);
            String reset="Space Reset Game";
            g.drawString(reset,100,350);

        }

    }
    public void add(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                brid.fly(1);
                break;
            case KeyEvent.VK_SPACE:
                if (brid.life==false){
                    restart();
                }

        }
    }
    public void minu(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_DOWN:
                brid.fly(5);
                break;

        }
    }
//    重置游戏
    public void restart(){
        gameBarrierLayer.restant();
        brid.restartDraw();

    }
}