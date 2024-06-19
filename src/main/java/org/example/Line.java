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
