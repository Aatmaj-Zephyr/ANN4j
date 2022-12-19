package ann4j;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    static BufferedWriter myWriter;
    static private boolean printInConsole = true;

    /**
     * If the user wants to print to the console, print to the console. Otherwise, write to the file
     * 
     * @param string The string to be written to the file.
     */
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

   /**
    * It takes in a variable number of arguments and prints them out
    */
    public static void write(Object... ob) {
        for (Object i : ob) {
            write(i.toString());
        }
    }

    public static void writeln(Object... ob) {
        // write("\n");

        write(ob);
    }

    /**
     * This function closes the file that we opened in the start() function
     */
    public static void end() {
        try {
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Setting the file that we want to write to.
    public static void setFile(String filename) {
        try {
            myWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * It sets the value of the static variable printInConsole to the value of the parameter bool.
     * 
     * @param bool true if you want to print in console as well, false if you don't.
     */
    public static void setPrintInConsoleEnabled(boolean bool) {
        printInConsole = bool;
    }

}