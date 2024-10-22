package game;

import java.util.Set;
public class Board {
    private static Board boardinstance;
    private DiffLevel level;
    private String[][] boxes = new String[10][10];
  
    private Board(){
        int count = 100;
        boolean check = true;
        
        for (int m = 0; m < 10; m++) {
            if (check) {
                // Fill from left to right (snake going left to right)
                for (int n = 0; n < 10; n++) {
                    boxes[m][n] = String.valueOf(count--);
                }
                check = false;  // Switch direction
            } else {
                // Fill from right to left (snake going right to left)
                for (int n = 9; n >= 0; n--) {
                    boxes[m][n] = String.valueOf(count--);
                }
                check = true;  // Switch direction back
            }
        }
        
    }
    
    
     public int getRowofposition(int n){
        return  9 - ((n - 1) / 10);
     }
     public int getColofposition(int n){
        int row = getRowofposition(n); 
      

        // Calculate column index based on the row parity
        if (row % 2 != 0) {
           return (n - 1) % 10; // Left to right
        } else {
            return   9 - ((n - 1) % 10); // Right to left
        }
     }
    public static Board getBoard(){
        if(boardinstance==null){
            boardinstance=new Board();
        }
        return boardinstance;
    }  

      EntityManager entityManager=EntityManager.getEntityManager();
       public void createBoard(DiffLevel level){
        this.level=level;
        if(level==DiffLevel.MEDIUM)
       { entityManager.createEntities(8, 8);}
       else if(level==DiffLevel.HARD){
        entityManager.createEntities(12, 12);
       }
       else {
        entityManager.createEntities(5, 5);
       }
       }

       Set<Snake> snakes=entityManager.getSnakes();
       Set<Ladder> ladders=entityManager.getLadders();
      public void displayBoard(String namePlayer,int positionplayer,int oldPosition){
            for(Snake sna:snakes){
                String temp="S";
                boxes[getRowofposition(sna.getStart())][getColofposition(sna.getStart())]=temp+sna.getId()+"St";
                boxes[getRowofposition(sna.getEnd())][getColofposition(sna.getEnd())]=temp+sna.getId()+"En";
            }
            for(Ladder sna:ladders){
                String temp="L";
                boxes[getRowofposition(sna.getStart())][getColofposition(sna.getStart())]=temp+sna.getId()+"St";
                boxes[getRowofposition(sna.getEnd())][getColofposition(sna.getEnd())]=temp+sna.getId()+"En";
            }
            if(positionplayer!=0 && oldPosition!=0){
                boxes[getRowofposition(positionplayer)][getColofposition(positionplayer)]=namePlayer;
                boxes[getRowofposition(oldPosition)][getColofposition(oldPosition)]=String.valueOf(oldPosition);

            }
             
            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    System.out.printf("%12s", boxes[i][j]);
                }
                System.out.println();
            }
     
      }
      public int checkSnake(int n) {
        for (Snake snake : snakes) {
            if (snake.getEnd() == n) {
                return snake.getStart(); // A snake starts at position n
            }
        }
        return n; // No snake starts at position n
    }
    public int checkLadder(int n) {
        for (Ladder ladder : ladders) {
            if (ladder.getStart()== n) {
                return ladder.getEnd(); // A snake starts at position n
            }
        }
        return n; // No snake starts at position n
    }
}