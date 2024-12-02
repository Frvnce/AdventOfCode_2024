import days.Day_01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int day;
        int choose;
        do {
            System.out.println("Write the day that you want to resolve: (A number between 1 and 25) ");
            day = sc.nextInt();
        } while (day < 1 || day >25);
        System.out.println("Your choose: " + day);
        do {
            System.out.println("Now you have to choose between the 2 puzzles of day " + day + ", write '1' or '2': ");
            choose = sc.nextInt();
        } while (choose < 1 || choose > 2);

        switch (day){
            case 1:
                Day_01 solution = new Day_01(choose);
                solution.solvePuzzle();
                break;
            case 2:
                //Day_02 sol = new Day_02(choose);
                //sol.solvePuzzle();
                break;
            case 3:
                //Day_03 sol3 = new Day_03(choose);
                //sol3.solvePuzzle();
                break;
        }

    }


}