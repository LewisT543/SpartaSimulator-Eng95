package com.sparta.simulation.view;

import com.sparta.simulation.model.*;

import java.util.*;
import java.util.stream.Collectors;
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

    public static String[] prepareDisplayTraineeGranular(ArrayList<Trainee> trainees, String message) {
        ArrayList<Trainee> devops = trainees
                .stream()
                .filter(e -> e.getTraineeCourse().equals(Simulation.Courses.DEVOPS))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Trainee> java = trainees
                .stream()
                .filter(e -> e.getTraineeCourse().equals(Simulation.Courses.JAVA))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Trainee> data = trainees
                .stream()
                .filter(e -> e.getTraineeCourse().equals(Simulation.Courses.DATA))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Trainee> csharp = trainees
                .stream()
                .filter(e -> e.getTraineeCourse().equals(Simulation.Courses.CSHARP))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Trainee> business = trainees
                .stream()
                .filter(e -> e.getTraineeCourse().equals(Simulation.Courses.BUSINESS))
                .collect(Collectors.toCollection(ArrayList::new));

        String[] results = new String[6];
        results[0] = message + " Trainees";
        results[1] = ("Devops: " + devops.size());
        results[2] = ("Java: " + java.size());
        results[3] = ("Data: " + data.size());
        results[4] = ("C#: " + csharp.size());
        results[5] = ("Business: " + business.size());
        return results;
    }

    public static String[] prepareDisplayCentreGranular(ArrayList<Centre> centres, String message) {
        ArrayList<Centre> tHub = centres
                .stream()
                .filter(e -> e instanceof TrainingHub)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Centre> bCamp = centres
                .stream()
                .filter(e -> e instanceof BootCamp)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Centre> tCent = centres
                .stream()
                .filter(e -> e instanceof TechCentre)
                .collect(Collectors.toCollection(ArrayList::new));

        String[] results = new String[4];
        results[0] = message + " Centres";
        results[1] = "TrainingHub: " + tHub.size();
        results[2] = "BootCamp: " + bCamp.size();
        results[3] = "TechCentre: " + tCent.size();
        return results;
    }

    public static void displayAllResults(Simulation sim, ArrayList<String> headers) {
        String[] open = SimulationCLIView.prepareDisplayCentreGranular(sim.getOpenCentres(), "Open");
        String[] closed = SimulationCLIView.prepareDisplayCentreGranular(sim.getClosedCentres(), "Closed");
        String[] full = SimulationCLIView.prepareDisplayCentreGranular(sim.getFullCentres(), "Full");
        String[] current = SimulationCLIView.prepareDisplayTraineeGranular(sim.getAllTrainees(), "Current");
        String[] waiting = SimulationCLIView.prepareDisplayTraineeGranular(sim.getTraineesInWaiting(), "Waiting");
        System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", headers.get(0), headers.get(1), headers.get(2), headers.get(3), headers.get(4));
        System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", open[1], closed[1], full[1], current[1], waiting[1]);
        System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", open[2], closed[2], full[2], current[2], waiting[2]);
        System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", open[3], closed[3], full[3], current[3], waiting[3]);
        System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", "", "", "", current[4], waiting[4]);
        System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", "", "", "", current[5], waiting[5]);
        System.out.println();
    }

    public static <T> void printArray(ArrayList<T> arr) {
        for (T item : arr) {
            System.out.print(item.toString() + ", ");
        }
        System.out.println();
    }
}
