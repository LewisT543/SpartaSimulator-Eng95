package com.sparta.simulation.view;

import java.util.*;
import java.util.stream.Stream;

public class SimulationCLIView {
    private static Scanner scan = new Scanner(System.in);
    // It's a welcome banner - does what it says on the tin.
    public static void displayWelcomeBanner() {
        System.out.println("+----------- WELCOME -----------+");
        System.out.println("|    To the Sparta Simulator!   |");
        System.out.println("|      Input the required       |");
        System.out.println("|      simulation length.       |");
        System.out.println("|                               |");
        System.out.println("|      The results will be      |");
        System.out.println("|     output to the console.    |");
        System.out.println("|                               |");
        System.out.println("+-------------------------------+");
    }

    public static String getInput(LinkedHashMap<String, String> acceptableChoices, String message) {
        String choice = "";
        boolean valid = false;
        while (!valid) {
            System.out.println(buildChoicesString(acceptableChoices, message));
            choice = scan.next();
            if (acceptableChoices.containsKey(choice.toLowerCase()))
                valid = true;
        }
        if (choice.equals("x")) {
            System.out.println("Program exiting - Thanks for sorting.");
            System.exit(0);
        }
        return choice;
    }

    private static String buildChoicesString(LinkedHashMap<String, String> acceptableChoices, String choiceType) {
        StringBuilder myString = new StringBuilder();
        myString.append("Please select ").append(choiceType).append(":\s");
        for (String choice : acceptableChoices.keySet()) {
            myString.append("\n -> ").append("'").append(choice).append("'").append(" for ")
                    .append(acceptableChoices.get(choice)).append("\s");
        }
        return myString.toString();
    }

    // This method asks the user for an integer until they return a valid input between lowerBound and upperBound.
    public static int getIntegerInput(int lowerBound, int upperBound, String message) {
        int choice = 0;
        while (choice < lowerBound || choice > upperBound) {
            System.out.println("Please enter " + message);
            while (!scan.hasNextInt()) {
                scan.nextLine();
                System.err.println("Not a valid number, please try again: ");
                scan.nextLine();
            }
            choice = scan.nextInt();
        }
        return choice;
    }

    // This method displays a nicely formatted table using headings and results
    public static void displayResultsTable(ArrayList<String> headingsArr, ArrayList<String> resultArr, boolean lJust) {
        String[] headings = headingsArr.toArray(new String[headingsArr.size()]);
        String[] results = resultArr.toArray(new String[resultArr.size()]);
        String[][] table = new String[][] { headings, results };

        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            columnLengths.putIfAbsent(i, 0);
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        StringBuilder formatString = new StringBuilder();
        String flag = lJust ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %").append(flag).append(e.getValue()).append("s "));
        formatString.append("|\n");

        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table[a]));
        System.out.println();
    }
}
