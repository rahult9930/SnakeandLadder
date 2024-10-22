
import game.GameManager;
import game.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameStart {
    public static void main(String[] args) {
        List<Player>players=new ArrayList<>();
        Scanner scan=new Scanner(System.in);
        System.out.println("How many players want to play ? ");
        int totalPlayers=scan.nextInt();
        for(int i=1;i<=totalPlayers;i++){
            System.out.println("Enter name of "+i+" player");
            String name=scan.next();
            
            players.add(new Player(name,i));
        }
        System.out.println("please type your difficulty level:(easy,medium,hard) ");
        String level=scan.next();
        GameManager manager=GameManager.getGameManager();
        
        manager.start(level, players, totalPlayers);
     
    }
}


