package main;

import java.util.ArrayList;
import java.util.List;

public class Barrierpool {
//    用于管理池中所有对象的容器
    private static List<Barrier>pool=new ArrayList<>();
//    池中初始的对象个数
    public static int initCount=16;
//    对象池中最大个数
    public static int maxCount=20;
    static {
        //初始化池中的对象
        for (int i = 0; i < initCount; i++) {
            pool.add(new Barrier());

        }
    }
    public static Barrier getPool(){
        int size=pool.size();
//        如果池中有对象才能从池中拿走对象
        if(size>0){

//            移除并返回对象
        System.out.println("拿走一个！");
            return pool.remove(size-1);

        }else {
            System.out.println("新的对象");
            return new Barrier();
            }

    }
//    将对象归还给容器中
    public static void setPool(Barrier barrier){
        if (pool.size()<maxCount){
            pool.add(barrier);
        }

    }


}
