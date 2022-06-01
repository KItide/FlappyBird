package main;
//游戏计时器

public class GameTime {
//    开始
    private long beginTime;
//    结束
    private long endTime;
//    时间差
    private long differ;
    public GameTime(){}
    public void begin(){
        beginTime=System.currentTimeMillis();
    }
    public long differ(){
        endTime=System.currentTimeMillis();
        differ=(endTime-beginTime)/1000;
        return differ;
    }
}
