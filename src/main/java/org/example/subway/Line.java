package org.example.subway;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private int id;
    private String name;
    private String railType;
    private List<Section> sections;

    public Line(int id, String name, String railType, List<Section> sections) {
        this.id = id;
        this.name = name;
        this.railType = railType;
        if (sections != null) {
            this.sections = new ArrayList<>(sections);
        } else {
            this.sections = new ArrayList<>();
        }
    }

    public int getId() {
        return this.id;
    }

    public List<Section> getSections() {
        return sections;
    }

    public int lineLength() {
        int distancia = 0;
        for (Section section : sections) {
            distancia += section.getDistance();
        }
        return distancia;
    }

    public int lineSectionLength(String station1Name, String station2Name) {
        List<Section> sections = getSections();
        boolean indicador = false;
        int distancia = 0;

        for (Section section : sections) {
            if (section.getPoint1().getName().equals(station1Name)) {
                indicador = true;
            }
            if (indicador) {
                distancia += section.getDistance();
            }
            if (section.getPoint2().getName().equals(station2Name)) {
                break;
            }
        } return distancia;
    }

    public int lineCost() {
        List<Section> sections = getSections();
        int costo = 0;
        for (Section section : sections) {
            costo += section.getCost();
        } return costo;
    }

    public int lineSectionCost(String station1Name, String station2Name) {
        List<Section> sections = getSections();
        boolean indicador = false;
        int costo = 0;

        for (Section section : sections) {
            if (section.getPoint1().getName().equals(station1Name)) {
                indicador = true;
            }
            if (indicador) {
                costo = section.getCost() + costo;
            }
            if (section.getPoint2().getName().equals(station2Name)) {
                break;
            }
        } return costo;
    }

    public void lineAddSection(Section section) {
        sections.add(section);
    }

    public boolean isLine(Line line) {
        List<Section> sections = line.getSections();
        boolean isTrue = true;
        if (line.getSections().isEmpty()) {
            return false;
        }
        for (int i = 0; i < sections.size() - 1; i++) {
            String station1Name = sections.get(i).getPoint1().getName();
            String station2Name = sections.get(i).getPoint2().getName();
            int station1Id = sections.get(i).getPoint1().getId();
            int station2Id = sections.get(i).getPoint2().getId();
            if (station1Name.equals(station2Name)) {
                isTrue = false;
                break;
            }
            if (station1Id == station2Id) {
                isTrue = false;
                break;
            }
            for (int j = i + 1; j < sections.size(); j++) {
                String station3Name = sections.get(j).getPoint1().getName();
                String station4Name = sections.get(j).getPoint2().getName();
                int station3Id = sections.get(j).getPoint1().getId();
                int station4Id = sections.get(j).getPoint2().getId();
                if (station1Name.equals(station3Name) || station1Name.equals(station4Name) || station2Name.equals(station4Name)) {
                    isTrue = false;
                    break;
                }
                if (station1Id == station3Id || station1Id == station4Id || station2Id == station4Id) {
                    isTrue = false;
                    break;
                }
            }
        } return isTrue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Line{id=%d, name='%s', railType='%s', sections=[", id, name, railType));
        if (sections.isEmpty()) {
            sb.append("No sections available");
        } else {
            for (Section section : sections) {
                sb.append("\n  ").append(section);
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
