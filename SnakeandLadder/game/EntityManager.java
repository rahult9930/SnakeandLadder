package game;
import java.util.HashSet;
import java.util.Set;
public class EntityManager {
    private static EntityManager entityManagerinstance;
    private int noofladder;
    private int noofSnakes;
    private Set<Snake> Snakes;
    private Set<Ladder> Ladders;

    private EntityManager() {
        Snakes=new HashSet<>();
        Ladders=new HashSet<>();
    }
     public static EntityManager getEntityManager(){
        if(entityManagerinstance==null){
          entityManagerinstance=new EntityManager();
        }
        return entityManagerinstance;
     }
       
     public void createEntities(int snakesCount,int ladderCount){

        for(int i=0;i<snakesCount;i++){
           int startInd = (int) (Math.random() * (98)) + 2; // Between 2 and 99
           int endInd = (int) (Math.random() * (100 - startInd)) + startInd + 1;
            Snakes.add(new Snake(startInd, endInd,i+1));
        }
        for(int i=0;i<ladderCount;i++){
           int startInd = (int) (Math.random() * (98)) + 2; // Between 2 and 99
           int endInd = (int) (Math.random() * (100 - startInd)) + startInd + 1;
            Ladders.add(new Ladder(startInd, endInd,i+1));
        }
     }
      
     public Set<Snake> getSnakes() {
        return Snakes;
    }

    // Method to get the set of ladders
    public Set<Ladder> getLadders() {
        return Ladders;
    }

}