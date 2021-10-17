package ch.zhaw.ads;

public class SnowflakeServer implements CommandExecutor{
    Turtle turtle = new Turtle();
    public void snowflake(int stufe, double dist){
        if (stufe == 0){
            turtle.move(dist);
        } else {
            stufe--;
            dist = dist/3;
            snowflake(stufe,dist);
            turtle.turn(60);
            snowflake(stufe,dist);
            turtle.turn(-120);
            snowflake(stufe,dist);
            turtle.turn(60);
            snowflake(stufe,dist);
        }
    }










    @Override
    public String execute(String command) throws Exception {
        return null;
    }
}
