package main;

public class Ui {
    public static void printer(String text) {
        String out = "____________________________________________________________\n"
                + text
                + "____________________________________________________________";
        System.out.println(out);
    }

    public static void printerN(String text) {
        String out = "____________________________________________________________\n"
                + text + "\n"
                + "____________________________________________________________";
        System.out.println(out);
    }

    public static void printStart() {
        Ui.printer("Hello! I'm Bob!\n"
                + "What do you want today?\n");
    }

    public static void printEnd() {
        Ui.printer("Bye!!!! seeee you soooon!!!!!\n");
    }

    public static void printBack(String input) {
        Ui.printer( "\"" + input + "\" What does that mean? \n");
    }
}
