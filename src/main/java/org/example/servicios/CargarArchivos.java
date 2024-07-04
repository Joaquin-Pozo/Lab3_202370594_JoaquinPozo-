package org.example.servicios;

import org.example.metro.Line;
import org.example.metro.Section;
import org.example.metro.Station;
import org.example.metro.StationType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CargarArchivos {

    public List<Line> cargarLineas(String fileName) {
        List<Line> newLines = new ArrayList<>();

        try {
            File archivo = new File(fileName);
            if (archivo.exists()) {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String data;
                while ((data = br.readLine()) != null) {
                    if (data.startsWith("Linea")) {
                        String[] parts = data.split(" ");
                        int lineId = Integer.parseInt(parts[1]);
                        String railType = br.readLine();
                        int cantidadSecciones = Integer.parseInt(br.readLine());
                        List<Section> sections = new ArrayList<>();
                        for (int j = 0; j < cantidadSecciones; j++) {
                            String readSection = br.readLine();
                            String[] sectionParts = readSection.split(",");
                            int id1 = Integer.parseInt(sectionParts[0]);
                            String name1 = sectionParts[1];
                            StationType type1 = new StationType(sectionParts[2]);
                            int stopTime1 = Integer.parseInt(sectionParts[3]);
                            int id2 = Integer.parseInt(sectionParts[4]);
                            String name2 = sectionParts[5];
                            StationType type2 = new StationType(sectionParts[6]);
                            int stopTime2 = Integer.parseInt(sectionParts[7]);
                            int distance = Integer.parseInt(sectionParts[8]);
                            int cost = Integer.parseInt(sectionParts[9]);
                            Station station1 = new Station(id1, name1, type1, stopTime1);
                            Station station2 = new Station(id2, name2, type2, stopTime2);
                            Section section = new Section(station1, station2, distance, cost);
                            sections.add(section);
                        }
                        Line newLine = new Line(lineId, "Linea " + lineId, railType, sections);
                        newLines.add(newLine);
                    }
                }
                br.close();
            } else {
                System.out.println("El archivo " + fileName + " no existe.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newLines;
    }
}
