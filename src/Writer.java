package src;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    static BufferedWriter myWriter;
    static private boolean printInConsole = true;

    private static void write(String string) {
        if (printInConsole) {
            System.out.println(string);
        }
        try {
            myWriter.write(string);
            myWriter.write("\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void write(Object... ob) {
        for (Object i : ob) {
            write(i.toString());
        }
    }

    public static void writeln(Object... ob) {
        // write("\n");

        write(ob);
    }

    public static void end() {
        try {
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setFile(String filename) {
        try {
            myWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPrintInConsoleEnabled(boolean bool) {
        printInConsole = bool;
    }

}