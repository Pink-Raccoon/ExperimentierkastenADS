package ch.zhaw.ads;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BracketServer implements CommandExecutor{

    private boolean prepareWeirdBracketsForStack(String command){
        Pattern pattern = Pattern.compile("[/][*][\\*\\+][*][/]");
        Matcher matcher = pattern.matcher(command);
        boolean matches = matcher.matches();

        if(!matches) {
            return  false;
        }

        return true;
    }

    public boolean checkBrackets (String command){

        ListStack listStack = new ListStack();

        if(command.equals(")")){
            return false;
        }




         for (int i = 0; i < command.length(); i++)
        {
            char x = command.charAt(i);

            if (x == '(' || x == '[' || x == '{' || x== '<')
            {

                listStack.push(x);
                continue;
            }


            char check;
            switch (x) {
                case ')':
                    check = (char) listStack.pop();
                    if (check == '{' || check == '['|| check == '<' )
                        return false;
                    break;

                case '}':
                    check = (char) listStack.pop();
                    if (check == '(' || check == '['|| check == '<')
                        return false;
                    break;

                case ']':
                    check = (char) listStack.pop();
                    if (check == '(' || check == '{'|| check == '<')
                        return false;
                    break;

                case '>':
                    check = (char) listStack.pop();
                    if (check == '(' || check == '[' || check == '{')
                        return false;
                    break;


                default:
                    continue;
            }
        }


        return (listStack.isEmpty());
    }

    @Override
    public String execute(String command) throws Exception {
        StringBuilder result = new StringBuilder(100);

        String input = command.replaceAll("\\s+", "");;


        System.out.println(input);



        if (checkBrackets(command) || prepareWeirdBracketsForStack(command)) {
            result.append("Wohlgeformt");
            result.append("\n");
        } else{
            result.append("Nicht wohlgeformt");
            result.append("\n");

        }return result.toString();
    }
}
