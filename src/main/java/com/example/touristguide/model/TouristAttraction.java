package com.example.touristguide.model;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String city;
    private List<String> tags;

    public TouristAttraction() {
    }

    public TouristAttraction(String name, String description, String city, List<String> tags) {
        this.description = description;
        this.name = name;
        this.city = city;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void addTags(String tagToAdd) {
        tags.add(tagToAdd);
    }

    public void deleteTags(String tagToDelete) {
        tags.remove(tagToDelete);
    }

    public List<String> getTags() {
        return tags;
    }
}