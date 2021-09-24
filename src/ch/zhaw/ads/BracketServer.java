package ch.zhaw.ads;


public class BracketServer implements CommandExecutor{

    public boolean checkBrackets (String command){

        ListStack listStack = new ListStack();


        for (int i = 0; i < command.length(); i++)
        {
            char x = command.charAt(i);

            if (x == '(' || x == '[' || x == '{')
            {

                listStack.push(x);
                continue;
            }


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


        if (checkBrackets(command)) {
            result.append("Wohlgeformt");
            result.append("\n");
        } else{
            result.append("Nicht wohlgeformt");
            result.append("\n");

        }return result.toString();
    }
}
