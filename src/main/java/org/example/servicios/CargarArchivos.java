package org.example.servicios;

import org.example.metro.*;

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
                            String readSection = br.readLine().trim();
                            String[] sectionParts = readSection.split(",");
                            int id1 = Integer.parseInt(sectionParts[0].trim());
                            String name1 = sectionParts[1].trim();
                            StationType type1 = new StationType(sectionParts[2].trim());
                            int stopTime1 = Integer.parseInt(sectionParts[3].trim());
                            int id2 = Integer.parseInt(sectionParts[4].trim());
                            String name2 = sectionParts[5].trim();
                            StationType type2 = new StationType(sectionParts[6].trim());
                            int stopTime2 = Integer.parseInt(sectionParts[7].trim());
                            int distance = Integer.parseInt(sectionParts[8].trim());
                            int cost = Integer.parseInt(sectionParts[9].trim());
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

    public List<Train> cargarTrenes(String fileName) {
        List<Train> newTrains = new ArrayList<>();

        try {
            File archivo = new File(fileName);
            if (archivo.exists()) {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String data;
                while ((data = br.readLine()) != null) {
                    if (data.startsWith("Tren")) {
                        String[] parts = data.split(" ");
                        int trainId = Integer.parseInt(parts[1]);
                        String trainMaker = br.readLine();
                        int speed = Integer.parseInt(br.readLine());
                        int stationStayTime = Integer.parseInt(br.readLine());
                        int cantidadPcars = Integer.parseInt(br.readLine());
                        List<PassengerCar> pcars = new ArrayList<>(cantidadPcars);
                        for (int j = 0; j < cantidadPcars; j++) {
                            String readPcar = br.readLine().trim();
                            String[] pcarParts = readPcar.split(",");
                            int id = Integer.parseInt(pcarParts[0].trim());
                            int passengerCapacity = Integer.parseInt(pcarParts[1].trim());
                            String model = pcarParts[2].trim();
                            String pcarTrainMaker = pcarParts[3].trim();
                            CarType carType = new CarType(pcarParts[4].trim());
                            PassengerCar pcar = new PassengerCar(id, passengerCapacity, model, pcarTrainMaker, carType);
                            pcars.add(pcar);
                        }
                        Train newTrain = new Train(trainId, trainMaker, speed, stationStayTime, pcars);
                        newTrains.add(newTrain);
                    }
                }
                br.close();
            } else {
                System.out.println("El archivo " + fileName + " no existe.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newTrains;
    }
    
    
}
