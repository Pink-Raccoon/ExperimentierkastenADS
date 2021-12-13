package ch.zhaw.ads;

import java.util.*;
import java.util.function.*;

public class SortServer implements CommandExecutor {
    private final int DATARANGE = 10000000;
    public int dataElems; // number of data
    private Random ran = new Random();

    public void swap(int[] a, int i, int j) {
        int h = a[i];
        a[i] = a[j];
        a[j] = h;
    }

    public void bubbleSort(int[] a) {
        for (int k = a.length-1; k > 0; k--){
            boolean noSwap = true;
            for (int i = 0; i < k; i++){
                if ( a[i] > a[i+1]) {
                    swap (a, i, i+1);
                    noSwap = false;
                }
            }
            if (noSwap) break;
        }
    }

    public void insertionSort(int[] a) {
        for (int k = 1; k < a.length; k++)
            if (a[k] < a[k-1]){
                int x = a[k];
                int i;
                for (i = k; ((i > 0) && (a[i-1] > x));i--)
                    a[i] = a[i-1];
                a[i] = x;
            }
    }

    public void selectionSort(int[] a) {
        for (int k = 0; k < a.length; k++){
            int min = k;
            for (int i = k+1; i < a.length; i ++) {
                if (a[i] < a[min]) min = i;
            }
            if (min != k) swap (a, min, k);
        }
    }

    public boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public int[] randomData() {
        int[] a = new int[dataElems];
        for (int i = 0; i < a.length; i++) {
            a[i] = ran.nextInt(DATARANGE);
        }
        return a;
    }

    public int[] ascendingData() {
        int[] a = new int[dataElems];
        for (int i = 0; i < a.length && i <= DATARANGE; i++) {
            a[i] = i;
        }
        return a;
    }

    public int[] descendingData() {
        int[] a = new int[dataElems];
        int counter = 0;
        for (int i = DATARANGE; counter < a.length; i--) {
            a[counter] = i;
            counter++;
        }
        return a;
    }

    // measure time of sorting algorithm
    // generator to generate the data
    // consumer sorts the data
    // return elapsed time in ms
    // if data is not sorted an exception is thrown
    public double measureTime(Supplier<int[]> generator, Consumer<int[]> sorter) throws Exception {
        double elapsed = 0;



        // if (!isSorted(b)) throw new Exception ("ERROR not eorted");
        return elapsed;
    }

    public String execute(String arg)  {
        // Java 9: use Map.of instead
        Map<String,Consumer<int[]>> sorter =  new HashMap<>();
        sorter.put("BUBBLE", this::bubbleSort);
        sorter.put("INSERTION", this::insertionSort);
        sorter.put("SELECTION", this::selectionSort);

        Map<String,Supplier<int[]>> generator =  new HashMap<>();
        generator.put("RANDOM", this::randomData);
        generator.put("ASC", this::ascendingData);
        generator.put("DESC", this::descendingData);

        String args[] = arg.toUpperCase().split("\\s+");
        dataElems = Integer.parseInt(args[2]);
        try {
            double time = measureTime(generator.get(args[1]), sorter.get(args[0]));
            return arg + " "+Double.toString(time)+" ms\n";
        } catch (Exception ex){
            return arg + " "+ ex.getMessage()+"\n";
        }
    }

    public static void main(String[] args) throws Exception {
        SortServer sorter = new SortServer();
        String sort;
        sort = "BUBBLE RANDOM 10000"; System.out.print(sorter.execute(sort));
        sort = "SELECTION RANDOM 10000"; System.out.print(sorter.execute(sort));
        sort = "INSERTION RANDOM 10000"; System.out.print(sorter.execute(sort));

        sort = "BUBBLE ASC 10000"; System.out.print(sorter.execute(sort));
        sort = "SELECTION ASC 10000"; System.out.print(sorter.execute(sort));
        sort = "INSERTION ASC 10000"; System.out.print(sorter.execute(sort));
    }
}
