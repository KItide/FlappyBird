package main;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBarrierLayer {
    private List<Barrier> lists;
    private Random random=new Random();
    private GameTime gameTime;
    private int txt;

    public GameBarrierLayer() {
        lists=new ArrayList<>();
        gameTime=new GameTime();
    }
    public void draw(Graphics g,Brid brid){
        for (int i = 0; i <lists.size(); i++) {
            Barrier barrier=lists.get(i);
            if(barrier.isVisible()){
            barrier.draw(g);
            }else {
                Barrier remove = lists.remove(i);
                Barrierpool.setPool(remove);
                i--;

            }
        }
        collideBird(brid);
        logic(g);

    }
    public void logic(Graphics g) {
        if (lists.size() == 0) {
            ran();
            gameTime.begin();
            insert(600,0,numberTop,0);
            insert(600,0,500 - numberDown,2);
        } else {
            long differ = gameTime.differ();
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",1,20));
            g.drawString("坚持了"+differ+"秒",30,50);
            txt=getTxt();
            if (differ<=txt){
                g.drawString("最高纪录"+txt,200,50);
            } else {
                setTxt(String.valueOf(differ));
                g.drawString("最高纪录"+differ,200,50);
            }

            Barrier last = lists.get(lists.size() - 1);
            if (last.isInFrame()) {
                ran();
                if (number<50){
                    insert(600,32,440,4);

                }else if(number>450) {
                    insert(600,125,200,6);

                } else {
                insert(600,0,numberTop,0);
                insert(600,0,500 - numberDown,2);}


            }

        }
    }
    File file=new File("D:\\game.txt ");
//    用于得到文件中的数据

    public int getTxt(){
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int read= 0;
        try {
            read = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;

    }
//    用于储存数据
    public void setTxt(String str){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insert(int x, int y, int height, int type ){
        Barrier top = Barrierpool.getPool();
        top.setX(x);
        top.setY(y);
        top.setHeigth(height);
        top.setType(type);
        top.setVisible(true);
        lists.add(top);

    }
//    上方的随机高度
    private int numberTop;
//    下方的随机高度
    private int numberDown;
    private int number;

    private void  ran(){
        numberTop=random.nextInt(400)+100;
        numberDown=random.nextInt(400)+100;
        number=random.nextInt(500);
//        如果管道重合则重新随机
         if(numberDown+numberTop>400){
             ran();
                }

            }
    //  判断障碍物和小鸟发生碰撞
    public boolean collideBird(Brid brid){
        for (int i = 0; i < lists.size(); i++) {
            Barrier barrier = lists.get(i);
//            判断矩形是否相交
            if(barrier.getRect().intersects(brid.getRect())){
                System.out.println("撞上了");
                brid.life=false;

            }

        }
        return false;
    }
//    用于清空障碍物的池子
    public void restant(){
        lists.clear();
    }


}


