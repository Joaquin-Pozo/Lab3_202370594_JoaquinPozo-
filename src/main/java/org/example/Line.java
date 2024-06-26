package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        } return len;
    }

    public int lineCost() {
        List<Section> sections = getSections();
        int costo = 0;
        for (Section section : sections) {
            costo = section.getCost() + costo;
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

    public int getId() {
        return this.id;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLine{id=").append(id);
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
        sb.append("]\n}");
        return sb.toString();
    }
}
