package managers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Order;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final ObjectMapper mapper = new ObjectMapper();

    // Экспорт заказов в файл JSON
    public static void exportOrdersToJSON(List<Order> orders, String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, orders);
            System.out.println("Orders successfully exported to " + fileName);
        } catch (IOException e) {
            System.out.println("Error exporting orders: " + e.getMessage());
        }
    }


    public static List<Order> importOrdersFromJSON(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(file, new TypeReference<List<Order>>() {});
        } catch (IOException e) {
            System.out.println("Error importing orders: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
