package ch.zhaw.ads;




public class KgvServer implements CommandExecutor {



    public int findeKgv(int a, int b){
        int ggt = findeGgt(a, b);
        int kgv = (a*b)/ggt;
        return kgv;

    }

    public int findeGgt(int a, int b) {

        if (a == 0)
            return b;
        while (b != 0) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }

        return a;
    }



    @Override
    public String execute(String command) {
        System.out.println("Schreibe zwei Integer und gebe sie so ein: 56 ,76");

        StringBuilder result = new StringBuilder(100);

        result.append("Die Eingabe war \"");
        result.append(command);
        result.append("\"\n");

        String[] numbers  = command.split("[ ,]+");
        int resA = Integer.parseInt(numbers[0]);
        int resB = Integer.parseInt(numbers[1]);
        int returnValue = findeKgv(resA,resB);
        result.append("\"\n");

        result.append("Der Kgv ist: "+ returnValue);
        result.append("\"\n");
        return result.toString();

    }
}






