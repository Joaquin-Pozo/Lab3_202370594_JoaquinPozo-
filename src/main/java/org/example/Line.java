package org.example;

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

    public void addSection(Section section) {
        sections.add(section);
    }

    public List<Section> getSections() {
        return sections;
    }

    public int lineLength() {
        return sections.size();
    }

    public int lineSectionLength(String station1Name, String station2Name) {
        List<Section> sections = getSections();
        boolean indicador = false;
        int len = 0;

        for (Section section : sections) {
            if (section.getPoint1().getName().equals(station1Name)) {
                indicador = true;
            }
            if (indicador) {
                len++;
            }
            if (section.getPoint2().getName().equals(station2Name)) {
                break;
            }
        }
        return len;
    }

    public int lineCost() {
        List<Section> sections = getSections();
        int costo = 0;
        for (Section section : sections) {
            costo = section.getCost() + costo;
        }
        return costo;
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
        }
        return costo;
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Line{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", railType='").append(railType).append('\'');
        sb.append(", sections=[");
        for (int i = 0; i < sections.size(); i++) {
            sb.append("[Section ").append(i + 1).append(": ");
            sb.append(sections.get(i).getPoint1().getName()).append(" -> ");
            sb.append(sections.get(i).getPoint2().getName()).append(", ");
            sb.append("distance=").append(sections.get(i).getDistance()).append(", ");
            sb.append("cost=").append(sections.get(i).getCost()).append("], ");
        }
        if (!sections.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length()); // Elimina la última coma y espacio
        }
        sb.append("]}");
        return sb.toString();
    }
}
