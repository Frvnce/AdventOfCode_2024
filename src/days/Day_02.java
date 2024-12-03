package days;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day_02 {
    File file;
    int choose;
    static int levelToRemove;

    public Day_02(int choose) {
        this.file = new File("src/inputs/day02.txt");
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

    private long getMaxLines() throws FileNotFoundException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        return br.lines().count();
    }

    private void getLine(HashMap<Integer, List<Integer>> map) throws IOException {
        // FileReader that read the input file.
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);


        for (int i = 0; i < getMaxLines(); i++) {
            Scanner sc = new Scanner(br.readLine());
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()){
                list.add(sc.nextInt());
            }
            map.put(i, list);
        }

    }

    private boolean isSafe(List<Integer> list){
        boolean isDecreasing = false;
        boolean isIncreasing = false;
        for (int j = 0; j < list.size()-1; j++) {
            int currentNumber = list.get(j);
            int nextNumber = list.get(j+1);

            if(currentNumber==nextNumber){
                levelToRemove=j;
                return false;
            }

            int diff = Math.abs(currentNumber-nextNumber);
            if(currentNumber>nextNumber){
                if(diff < 1 || diff > 3){
                    levelToRemove=j;
                    return false;
                }
                if(j==0){
                    isDecreasing = true;
                }
            }

            if(currentNumber<nextNumber){
                if(diff < 1 || diff > 3){
                    levelToRemove=j;
                    return false;
                }
                if(j==0){
                    isIncreasing = true;
                }
            }

            if(currentNumber>nextNumber && isIncreasing){
                levelToRemove=j;
                return false;
            }

            if(currentNumber<nextNumber && isDecreasing){
                levelToRemove=j;
                return false;
            }

        }
        return true;
    }

    private boolean isSafe2(List<Integer> list){
        if(isSafe(list)){return true;}
        list.remove(levelToRemove);
        return isSafe(list);
    }

    private void solvePuzzleOne() throws IOException {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        getLine(map);

        int safeCombination = 0;

        for (int i = 0; i < map.size(); i++) {
            List<Integer> list = map.get(i);
            if(isSafe(list)){
                safeCombination++;
            }
        }

        System.out.println("safeCombination");
    }

    private void solvePuzzleTwo() throws IOException {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        getLine(map);

        int safeCombination = 0;

        for (int i = 0; i < map.size(); i++) {
            List<Integer> list = map.get(i);
            if(isSafe2(list)){
                safeCombination++;
            }
        }

        System.out.println("Part 2 not working... " + safeCombination);
    }
}
