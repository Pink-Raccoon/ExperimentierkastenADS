package ch.zhaw.ads;

import java.util.Scanner;

public class HanoiServer implements CommandExecutor {
    public void moveDisk (int n, char from, char to, char help){

        if(n > 0){
            // Bewege Stapel n-1 from A nach C
            moveDisk(n-1,from,help,to);
            System.out.println("Move " + from + " to " + help);;

            moveDisk(n-1,help,from,to);
            System.out.println("Move " + help + " to " + to);
        }



    }







    @Override
    public String execute(String command) throws Exception {
        System.out.println("Enter number of disks on pole A: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        moveDisk(n,'A','B','C');
        scanner.close();
        return null;
    }
}
