package main;

import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        Bob.PrintStart();

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                Bob.PrintEnd();
                break;
            }
            else {
                PrintBack(input);
            }
        }
    }

    protected static void Line(){
        System.out.println("____________________________________________________________");
    }

    protected static void PrintBack(String input) {
        String out = "____________________________________________________________\n"
                + input + "\n"
                + "____________________________________________________________";
        System.out.println(out);
    }

    protected static void PrintStart() {
        String out = "____________________________________________________________\n"
                + "Hello! I'm Bob!\n"
                + "What do you want today?\n"
                + "____________________________________________________________";
        System.out.println(out);
    }

    protected static void PrintEnd() {
        String out = "____________________________________________________________\n"
                + "Bye!!!! seeee you soooon!!!!!\n"
                + "____________________________________________________________";
        System.out.println(out);
    }
}
