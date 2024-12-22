import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SE115Maps {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error: Please provide exactly two arguments - input and output file names.");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];
        int lineNumber = 0; // To follow the lineNumber
        try (FileWriter fileWriter = new FileWriter(outputFileName)) {
            Scanner sc = new Scanner(new File(inputFileName));

            // City number control
            lineNumber++;
            int numCities = parseInt(sc.nextLine(), lineNumber, "Invalid city count");

            // Error line number
            lineNumber++;

            //City name trim
            String[] cityLabelsArray = sc.nextLine().trim().split(" ");
            if (cityLabelsArray.length != numCities) {
                printError(lineNumber, "Mismatch in the number of city labels.");
                return;
            }

            //Route number control
            lineNumber++;
            int numRoutes = parseInt(sc.nextLine(), lineNumber, "Invalid route count");

            //CountryMap object created
            CountryMap map = new CountryMap(numCities, numRoutes);
            for (String label : cityLabelsArray) map.addCity(new City(label));

            // Route parts control
            for (int i = 0; i < numRoutes; i++) {
                lineNumber++;
                String[] routeDataArray = sc.nextLine().trim().split(" ");
                if (routeDataArray.length != 3) {
                    printError(lineNumber, "Route should have 3 parts (city1, city2, time).");
                    return;
                }

                City city1 = map.getCityByName(routeDataArray[0]);
                City city2 = map.getCityByName(routeDataArray[1]);
                int time = parseInt(routeDataArray[2], lineNumber, "Invalid time format");

                if (city1 == null || city2 == null) {
                    printError(lineNumber, "One or both of the cities in the route do not exist.");
                    return;
                }
                //Route object created
                map.addRoute(new Route(city1, city2, time));
            }

            //StartCity and EndCity control
            lineNumber++;
            String[] startEndCities = sc.nextLine().trim().split(" ");
            if (startEndCities.length != 2) {
                printError(lineNumber, "Start and end city names are incorrect.");
                return;
            }

            String startCity = startEndCities[0];
            String endCity = startEndCities[1];

            System.out.println("File read is successful!");

            // WayFinder
            WayFinder wayFinderObject = new WayFinder(map);
            String result = wayFinderObject.findFastestRoute(startCity, endCity);

            fileWriter.write(result);
        } catch (IOException e) {
            printError(lineNumber, "Error while reading the file: " + e.getMessage());
        }
    }

    //String to int. In case of an error, error message will be printed.
    private static int parseInt(String value, int lineNumber, String errorMessage) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            printError(lineNumber, errorMessage);
            System.exit(1);
        }
        return -1; // Never used
    }

    //Prints the error message with the line number.
    private static void printError(int lineNumber, String errorMessage) {
        System.out.println("Error Line: " + lineNumber + " - " + errorMessage);
    }
}