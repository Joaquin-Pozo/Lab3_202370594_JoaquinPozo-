package org.example.servicios;

import org.example.metro.Line;
import org.example.metro.Section;
import org.example.metro.Station;
import org.example.metro.StationType;
import org.example.metro.Subway;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CargarLineas {

    public static void loadLineFromFile(Subway subway, String fileName) {
        Path path = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length > 0) {
                        String firstPart = parts[0].trim();
                        if (firstPart.startsWith("Linea")) {
                            int id = Integer.parseInt(firstPart.substring(6).trim()); // Obtener el número de línea
                            String railType = line.trim(); // Leer el tipo de riel
                            int numSections = Integer.parseInt(line.trim()); // Leer el número de secciones
                            List<Section> sections = new ArrayList<>();
                            for (int i = 0; i < numSections; i++) {
                                String sectionInfo = br.readLine().trim();
                                String[] sectionParts = sectionInfo.split(",");
                                int id1 = Integer.parseInt(sectionParts[0]);
                                String name1 = sectionParts[1];
                                StationType type1 = new StationType(sectionParts[2]);
                                int stopTime1 = Integer.parseInt(sectionParts[3]);
                                int id2 = Integer.parseInt(sectionParts[4]);
                                String name2 = sectionParts[5];
                                StationType type2 = new StationType(sectionParts[6]);
                                int distance = Integer.parseInt(sectionParts[7]);
                                int cost = Integer.parseInt(sectionParts[8]);
                                int sectionId = Integer.parseInt(sectionParts[9]);
                                Station station1 = new Station(id1, name1, type1, stopTime1);
                                Station station2 = new Station(id2, name2, type2, stopTime1);
                                Section section = new Section(station1, station2, distance, cost);
                                sections.add(section);
                            }
                            Line newLine = new Line(id, "Linea " + id, railType, sections);
                            subway.addLine(newLine);
                        }
                    }
                }
            }
            System.out.println("Líneas cargadas correctamente desde el archivo.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}
