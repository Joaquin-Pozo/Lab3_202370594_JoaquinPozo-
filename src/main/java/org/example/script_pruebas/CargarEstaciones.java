package org.example.script_pruebas;

import org.example.Station;
import org.example.Type;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarEstaciones {
    private List<Station> stations = new ArrayList<>();

    public void loadStations(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("//") || line.isEmpty()) {
                    continue; // Saltar comentarios y líneas vacías
                }
                // Procesar la línea
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim().replaceAll("^\"|\"$", ""); // Quitar comillas
                String typeStr = parts[2].trim().replaceAll("^\"|\"$", "");
                int stopTime = Integer.parseInt(parts[3].trim());
                Type type = new Type(typeStr); // Crear instancia de Type

                Station station = new Station(id, name, type, stopTime);
                stations.add(station);
            }
            System.out.println("Estaciones cargadas correctamente.");
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void showStations() {
        for (Station station : stations) {
            System.out.println(station);
        }
    }

    public void start() {
        String fileName = "src/main/java/org/example/script_pruebas/estaciones.txt"; // Ruta relativa desde el directorio base de IntelliJ
        loadStations(fileName);
        showStations();
    }
}
