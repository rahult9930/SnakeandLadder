package game;

public class Player {
    private String name;
    private int id;
    private int position=1;
    
    public Player(String name,int id){
        this.name=name;
        this.id=id;
    }

    public int getId(){
        return id;
    }
    public int getPosition(){
        return position;
    }
    public String getName(){
        return name;
    }
    public void setPosition(int newpos){
        this.position=newpos;
    }
}
