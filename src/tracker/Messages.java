package tracker;

public class Messages {
    public final static String greeting = "Learning Progress Tracker";
    public final static String error = "Unknown command!";
    public final static String emptyInput = "No input";
    public final static String byeMessage = "Bye!";
    public final static String enterStudentMessage = "Enter student credentials or 'back' to return:";
    public final static String addStudentMessage = "The student has been added.";
    public final static String exitMessage = "Enter 'exit' to exit the program";
    public final static String addPointMessage = "Enter an id and points or 'back' to return";
    public final static String findStudentMessage = "Enter an id or 'back' to return";
    public final static String statisticMessage = "Type the name of a course to see details or 'back' to quit";

    public static void printTotal(int count) {
        System.out.printf("Total %d students have been added.\n", count);
    }
}
