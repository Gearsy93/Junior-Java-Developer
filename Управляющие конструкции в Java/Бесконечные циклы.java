package com.intellekta;

import java.util.Scanner;

public class WindowResolver {

    public static void main(String[] args){
        while (true) {
            switch (getUserInput()) {
                case 1:
                    System.out.println("По вашему вопросу обратитесь в окно 26");
                    break;
                case 4:
                    System.out.println("По вашему вопросу обратитесь в окно 26");
                    break;
                case 7:
                    System.out.println("По вашему вопросу обратитесь в окно 26");
                    break;
                case 2:
                    System.out.println("По вашему вопросу обратитесь в окно 27");
                    break;
                case 3:
                    System.out.println("По вашему вопросу обратитесь в окно 27");
                    break;
                case 5:
                    System.out.println("По вашему вопросу обратитесь в окно 27");
                    break;
                case 6:
                    System.out.println("По вашему вопросу обратитесь в окно 27");
                    break;
                case 8:
                    System.out.println("По вашему вопросу обратитесь в окно 28");
                    break;
                case 9:
                    System.out.println("По вашему вопросу обратитесь в окно 28");
                    break;
                case 10:
                    System.out.println("По вашему вопросу обратитесь в окно 28");
                    break;
                default:
                    System.out.println("По вашему вопросу обратитесь в окно 30");
                    break;

            }
        }
    }

    static Scanner in = new Scanner(System.in);
    private static int getUserInput(){
        return in.nextInt();
    }
}
