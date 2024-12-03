package days;

/*
    Per esempio:
    3   4
    4   3
    2   5
    1   3
    3   9
    3   3

    Abbina i numeri e misura la loro distanza.
    Abbina il numero più piccolo nella lista di sinistra con il numero più piccolo nella lista di destra
    poi il secondo numero più piccolo a sinistra con il secondo numero più piccolo a destra , e così via.

    All'interno di ogni coppia, calcola quanto sono distanti i due numeri;
    dovrai sommare tutte quelle distanze .
    Ad esempio, se abbini a 3dall'elenco di sinistra con a 7dall'elenco di destra
    la distanza è 4; se abbini a 9con a 3, la distanza è 6.
    */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day_01 {
    File file;
    int choose;

    public Day_01(int choose) {
        this.file = new File("src/inputs/day01.txt");
        this.choose = choose;
    }

    public void solvePuzzle() throws IOException {
        switch(choose){
            case 1:
                solvePuzzleOne();
                break;
            case 2:
                solvePuzzleTwo();
                break;
        }
    }

    private List<Integer> getList(boolean isLeft) throws FileNotFoundException {
        // FileReader that read the input file.
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        Scanner sc = new Scanner(br);

        List<Integer> dx_list = new ArrayList<>();
        List<Integer> sx_list = new ArrayList<>();

        int i=0;
        while (sc.hasNext()){
            if(i==0){
                sx_list.add(sc.nextInt());
                i++;
            }else{
                dx_list.add(sc.nextInt());
                i=0;
            }
        }

        if(isLeft){
            return sx_list;
        }else{
            return dx_list;
        }
    }

    private void solvePuzzleOne() throws IOException {
        List<Integer> dx_list = getList(false);
        List<Integer> sx_list = getList(true);

        // (key) first one: position (value) second one: number
        HashMap<Integer,Integer> dx_old_map = new HashMap<>();
        HashMap<Integer,Integer> sx_old_map = new HashMap<>();

        int sumDistance = 0;
        int totalSize = 0;
        if(dx_list.size()==sx_list.size()){
            totalSize = dx_list.size();
        }else{
            System.out.println("Error");
            return;
        }


        for (int j = 0; j < totalSize; j++) {
            int dx_min = getMin(totalSize, dx_list, dx_old_map);
            int sx_min = getMin(totalSize, sx_list, sx_old_map);

            int diff = Math.abs(dx_min-sx_min);

            sumDistance += diff;

        }
        System.out.println("Result: " + sumDistance);
    }

    private int getMin(int totalSize, List<Integer> list, HashMap<Integer,Integer> map){
        int min = Integer.MAX_VALUE;

        int finalPosition = 0;
        for (int j = 0; j < totalSize; j++) {
            int dx_currentNumber = list.get(j);

            if(min>dx_currentNumber){
                if(!map.containsKey(j)){
                    min = dx_currentNumber;
                    finalPosition = j;
                }
            }
        }

        map.put(finalPosition,min);
        return min;
    }

    private void solvePuzzleTwo() throws FileNotFoundException {
        List<Integer> dx_list = getList(false);
        List<Integer> sx_list = getList(true);
        int totalSize = 0;

        if(dx_list.size()==sx_list.size()){
            totalSize = dx_list.size();
        }else{
            System.out.println("Error");
            return;
        }

        System.out.println(getNumber(totalSize,sx_list,dx_list));
    }

    private int getNumber(int totalSize, List<Integer> sx_list, List<Integer> dx_list){
        int sum = 0;
        for (int j = 0; j < totalSize; j++) {
            int multiple = 0;
            int num = sx_list.get(j);
            for (int i = 0; i < totalSize; i++) {
                if(dx_list.get(i)==num){
                    multiple++;
                }
            }
            sum = sum + (num * multiple);
        }
        return sum;
    }
}
