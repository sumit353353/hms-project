package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    /**
     * Generate CSV report
     */
    public static boolean generateCSVReport(String filename, List<String[]> data) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String[] row : data) {
                writer.write(String.join(",", row) + "\n");
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error generating report: " + e.getMessage());
            return false;
        }
    }

    /**
     * Generate text report
     */
    public static boolean generateTextReport(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
            return true;
        } catch (IOException e) {
            System.err.println("Error generating report: " + e.getMessage());
            return false;
        }
    }
}