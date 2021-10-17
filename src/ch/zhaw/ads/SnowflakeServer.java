package ch.zhaw.ads;

import java.util.Scanner;
import java.lang.Math;

public class SnowflakeServer implements CommandExecutor{
    Turtle turtle = new Turtle();

   public void snowflake(int stufe, double dist) {

        if (stufe == 0) {
            turtle.move(dist);
        } else {

                stufe--;
                dist = (dist / 3);
                snowflake(stufe, dist);
                turtle.turn(60);
                snowflake(stufe, dist);
                turtle.turn(-120);
                snowflake(stufe, dist);
                turtle.turn(60);
                snowflake(stufe, dist);

            }

    }


    @Override
    public String execute(String command) throws Exception {
       Turtle turtle = new Turtle(0.1,0.7);
       Scanner scanner = new Scanner(command);
        int n = scanner.nextInt();
        double dist = 0.7;
        for(int i =0; i<3;i++) {
            snowflake(n, dist);
            turtle.turn(-120);
        }
        scanner.close();
        return turtle.getTrace();
    }
}
