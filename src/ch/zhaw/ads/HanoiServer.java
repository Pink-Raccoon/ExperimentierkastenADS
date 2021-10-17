package ch.zhaw.ads;

public class HanoiServer implements CommandExecutor {
    public void moveDisk (int n, char from, char to, char help){

        if(n > 0){
            // Bewege Stapel n-1 from A nach C
            moveDisk(n-1,from,help,to);
            System.out.println("Move " + from + " to " + to);
            moveDisk(n-1,help,to,from);
            System.out.println("Move " + help + " to " + to);
        }



    }




























    @Override
    public String execute(String command) throws Exception {
        moveDisk(3,'A','B','C');
        return null;
    }
}
