package managers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Order;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileManager {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void exportOrdersToJSON(List<Order> orders, String fileName) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), orders);
            System.out.println("Orders successfully exported to " + fileName);
        } catch (IOException e) {
            System.out.println("Error exporting orders: " + e.getMessage());
        }
    }

    public static List<Order> importOrdersFromJSON(String fileName) {
        try {
            return mapper.readValue(new File(fileName), new TypeReference<List<Order>>() {});
        } catch (IOException e) {
            System.out.println("Error importing orders: " + e.getMessage());
            return List.of();
        }
    }
}
