package ch.zhaw.ads;
import java.io.ByteArrayInputStream;
import java.util.*;

public class BracketServer implements CommandExecutor{



    public boolean checkBrackets (String command){

        ListStack listStack = new ListStack();

        // Über String input iterieren
        for (int i = 0; i < command.length(); i++)
        {
            char x = command.charAt(i);

            if (x == '(' || x == '[' || x == '{')
            {

                listStack.push(x);
                continue;
            }

            if (listStack.isEmpty())
                return false;
            char check;
            switch (x) {
                case ')':
                    check = (char) listStack.pop();
                    if (check == '{' || check == '[')
                        return false;
                    break;

                case '}':
                    check = (char) listStack.pop();
                    if (check == '(' || check == '[')
                        return false;
                    break;

                case ']':
                    check = (char) listStack.pop();
                    if (check == '(' || check == '{')
                        return false;
                    break;
            }
        }


        return (listStack.isEmpty());
    }

    @Override
    public String execute(String command) throws Exception {
        StringBuilder result = new StringBuilder(100);
        Scanner scanner = new Scanner(new ByteArrayInputStream(command.getBytes()));
        command = scanner.next();


        if (checkBrackets(command)) {
            result.append("Wohlgeformt");
            result.append("\n");
        } else{
            result.append("Nicht wohlgeformt");
            result.append("\n");

        }return result.toString();
    }
}
