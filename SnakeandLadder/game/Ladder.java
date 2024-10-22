package game;

public class Ladder{
    private int start;
    private int end;
    private int id;
    Ladder(int start,int end,int id){
       this.start=start;
       this.end=end;
       this.id=id;
    }
    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
    public int getId(){
        return id;
    }
}
