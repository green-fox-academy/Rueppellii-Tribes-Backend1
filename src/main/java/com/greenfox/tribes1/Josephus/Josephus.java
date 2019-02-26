package com.greenfox.tribes1.Josephus;

import java.util.ArrayList;
import java.util.Scanner;

public class Josephus {
    public static void main(String[] args) {
       // System.out.println("Give me the number of the players");
        System.out.println("Give me a number");

        Scanner input = new Scanner(System.in);
        int number =input.nextInt();
       // int members = input.nextInt();
        //josephus(members);
        System.out.println(test(number));
    }

    public static int test(int b){
        int input = b;
        try{
            int result = 5/b;
            //System.out.println("HAHOOO");
            //System.exit(0);
            //System.out.println("Text");
            return 1;
        }catch (ArithmeticException ex){
            return 2;
        }
        finally {
            return 3;
        }

    }

    public static void josephus(int a) {
        int winning = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i <= a; i++) {
            arrayList.add(i);

        }
        while (arrayList.size() >= 2) {

            if (arrayList.size() % 2 == 0) {
                for (int i = 0; i < arrayList.size(); i++) {
                    // System.out.print(arrayList.get(i)+ " ");
                    arrayList.remove(i + 1);

                }
                winning = arrayList.get(0);

            } else {
                for (int i = 0; i < arrayList.size(); i++) {
                    // System.out.print(arrayList.get(i) + " ");
                    if (arrayList.size() > i + 1) {
                        arrayList.remove(i + 1);
                    }
                }
                arrayList.add(0, arrayList.get(arrayList.size() - 1));
                arrayList.remove(arrayList.size() - 1);
            }
        }
        System.out.println("The winning position is " + winning);
    }
}



