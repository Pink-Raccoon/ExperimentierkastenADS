package ch.zhaw.ads;

import java.io.*;
import java.util.List;

public class WellformedXmlServer implements CommandExecutor{

    File file = new File("src/ExcludedFromCompiling/books.xml");
    ListStack listStack = new ListStack();


    public String readFile(){

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;

            while ((line = br.readLine()) != null)  {

                line = line.trim();


                // Creating array of string length
                char[] ch = new char[line.length()];
                String newToken = "";

                boolean starter = false;
                boolean enderli = false;
                boolean ender = false;

                // Copy character by character into array
                for (int i = 0; i < line.length(); i++) {
                    ch[i] = line.charAt(i);
                }

                // Printing content of array
                for (char c : ch) {

                    if (c == '<') {
                        starter = true;
                        System.out.println("start Tag");
                        continue;
                    }

                    if (c == '/' && starter == true) {
                        enderli = true;
                        System.out.println("ender Tag");
                    }

                    if (c == '>' && starter == true) {
                        if (enderli == true) {
                            ender = true;
                            starter = false;
                            System.out.println("end Tag");
                            continue;
                        }
                        ender = true;
                        starter = false;
                        System.out.println("end Tag");
                        continue;
                    }


                        newToken += c;










                }








        }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        } catch(IOException e){
            System.out.println("IO Error: " + e.getMessage());
        }

        return null;
    }


 public boolean checkWellformed(String command){

     if(!(command.equals(listStack.peek()))){
         listStack.push(command);

     }else {
         listStack.pop();

     }






        return (listStack.isEmpty());
    }

    @Override
    public String execute(String command) throws Exception {

        StringBuilder result = new StringBuilder(100);

        String input = command.replaceAll("\\s+", "");;


        System.out.println("Im am execute");
        System.out.println(input);

        if (input.equals("xml")) {
            readFile();
        }


        if (checkWellformed(command)) {
            result.append("Wohlgeformt");
            result.append("\n");
        } else{
            result.append("Nicht wohlgeformt");
            result.append("\n");

        }return result.toString();


    }
}
