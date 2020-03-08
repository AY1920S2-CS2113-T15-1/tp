/*
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
 */
import java.util.Scanner;

public class Parser {

    public static void parseUserCommands(Scanner scanner, ActivityList activityList) {
        boolean exit = false;
        while (exit == false) {
            String userInput = scanner.nextLine();
            String[] tokenizedInputs = userInput.split(" ", 2);
            String instruction = tokenizedInputs[0];

            switch (instruction) {
            case "bye":
                System.out.println("Bye! See you again.\n");
                exit = true;
                break;
            case "start":
                System.out.println("Started " + tokenizedInputs[1] + "\n");
                break;
            case "end":
                System.out.println("Ended " + tokenizedInputs[1] + "\n");
                break;
            case "list":
                break;
            case "delete":
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }
    }
}
