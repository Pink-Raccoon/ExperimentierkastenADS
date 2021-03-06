package ch.zhaw.ads;




public class KgvServer implements CommandExecutor {



    public int kgv(int a, int b){
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
        StringBuilder result = new StringBuilder(100);
        String[] numbers  = command.split("[ ,]+");
        int resA = Integer.parseInt(numbers[0]);
        int resB = Integer.parseInt(numbers[1]);
        int returnValue = kgv(resA,resB);
        result.append("\"\n");

        result.append("Der Kgv ist: "+ returnValue);
        result.append("\"\n");
        return result.toString();

    }



}






