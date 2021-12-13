package ch.zhaw.ads;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class QuickerSortServer extends RecursiveAction implements CommandExecutor {
    private final int DATARANGE = 10000000;  
    public int dataElems; // number of data
    public static int insertion_threshold;
    
    // for FJ
    private final int SPLIT_THRESHOLD = 10000;
    private int[] a;
    private int left;
    private int right;
    ForkJoinPool forkJoinPool;

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
            a[i] = (int) (Math.random() * DATARANGE);
        }
        return a;
    }

    public void swap(int[] a, int i, int j) {
        int h = a[i]; a[i] = a[j]; a[j] = h;
    }

    // insertion sort over interval
    void insertionSort(int[] a, int left, int right) {
        for (int k = left + 1; k < right + 1; k++) {
            if (a[k] < a[k - 1]) {
                int x = a[k];
                int i;
                for (i = k; ((i > 0) && (a[i - 1] > x)); i--) {
                    a[i] = a[i - 1];
                }
                a[i] = x;
            }
        }
    }

    int partition(int arr[], int left, int right) {
       int pivot = arr[(left + right)/2];
       while (left <= right){
           while (arr[left] < pivot){
               left++;
           }
           while (arr[right] > pivot){
               right--;
           }
           if (left <= right){

               swap(arr,left,right);
               left++;
               right--;
           }
       }
       return left;
    }

    void quickerSort(int arr[], int left, int right) {
       if (right - left < insertion_threshold){
           insertionSort(arr,left,right);
       }else{
           int l = partition (arr,left,right);
           quickerSort(arr,left,l-1);
           quickerSort(arr,l,right);
       }
    }
    
    void quickerSort(int arr[]) {
        quickerSort(arr, 0, arr.length - 1);
    }
    
    public QuickerSortServer() {}
    
    public QuickerSortServer(int[] a, int left, int right) {
        this.a = a;
        this.left = left;
        this.right = right;
    } 
    
    public void compute() {
        if (left < right) {
            int l = partition(a, left, right);
            if (l - left > SPLIT_THRESHOLD && right - l > SPLIT_THRESHOLD) {
                ForkJoinTask t1 = new QuickerSortServer(a, left, l - 1);
                ForkJoinTask t2 = new QuickerSortServer(a, l, right);
                invokeAll(t1, t2);
            } else {
                quickerSort(a, left, l - 1);
                quickerSort(a, l, right);
            }

        }
    }
    
    void quickerSortFJ(int[] a) {
        int parallelism = java.lang.Runtime.getRuntime().availableProcessors()* 2;

        forkJoinPool = new ForkJoinPool(parallelism );
        try {
            QuickerSortServer rootTask = new QuickerSortServer(a, 0, a.length - 1);
            forkJoinPool.invoke(rootTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public double measureTime(Supplier<int[]> generator, Consumer<int[]> sorter) throws Exception {
        double elapsed = 0;
        
        int[] a = generator.get();
        int[] b = new int[dataElems];
        
        long startTime = System.currentTimeMillis();
        long endTime = startTime;
        int count = 0;
        while (endTime < startTime + 1000) {
            System.arraycopy(a, 0, b, 0, a.length);
            sorter.accept(b);
            count++;
            endTime = System.currentTimeMillis();
        }
        elapsed = (double) (endTime - startTime) / count;   
        if (!isSorted(b)) throw new Exception ("ERROR not eorted");
        return elapsed;
    }

   /* public String execute(String arg) {
      // Java 9: use Map.of instead
        Map<String,Consumer<int[]>> sorter =  new HashMap<>();
        sorter.put("STREAM", this::streamSort);
        sorter.put("STREAMPARALLEL", this::streamSortParallel);
        sorter.put("QUICKER", this::quickerSort);
        sorter.put("QUICKERFJ", this::quickerSortFJ);
        
        Map<String,Supplier<int[]>> generator =  new HashMap<>();
        generator.put("RANDOM", this::randomData);
        
        String args[] = arg.toUpperCase().split("\\s+");
        dataElems = Integer.parseInt(args[2]);
        insertion_threshold = Integer.parseInt(args[3]);
        try {
            double time = measureTime(generator.get(args[1]), sorter.get(args[0]));
            return arg + " "+Double.toString(time)+" ms\n";
        } catch (Exception ex){
            return arg + " "+ ex.getMessage()+"\n";
        }  
    }*/
    
    public static void main(String[] args) throws Exception {
        QuickerSortServer sorter = new QuickerSortServer();
        String sort;
        sort = "QUICKER RANDOM 1000000 2"; System.out.print(sorter.execute(sort));
        sort = "QUICKER RANDOM 1000000 10"; System.out.print(sorter.execute(sort));
        sort = "QUICKERFJ RANDOM 1000000 20"; System.out.print(sorter.execute(sort));
    }

    @Override
    public String execute(String command) throws Exception {
        return null;
    }
}
