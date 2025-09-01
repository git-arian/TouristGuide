package com.example.touristguide.repository;
import com.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> data = new ArrayList<>();

    public TouristRepository() {
        data.add(new TouristAttraction("Tivoli",
                "Second oldest amusement park in the world", "København",
                new ArrayList<>(List.of("Kids friendly", "Fun for the whole family", "$$"))));

        data.add(new TouristAttraction("Geranium",
                "3 star Michelin restaurant", "København",
                new ArrayList<>(List.of("Fine dining", "$$$"))));

        data.add(new TouristAttraction("Rosenborg Castle",
                "A renaissance castle built in 1606", "København",
                new ArrayList<>(List.of("History & culture", "Kids friendly", "$"))));
    }

    public List<TouristAttraction> getAllAttractions() {
        return data;
    }

    public TouristAttraction getByName(String name) {
        for (TouristAttraction a : data) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public List<String> getCities() {
        return List.of("Copenhagen", "Lyngby", "Klampenborg", "Aarhus", "Aalborg");

    }

    public List<String> getTags() {
        return List.of("Fine dining", "Kids friendly", "Fun for the whole family",
                "$", "$$", "$$$", "Free", "History & culture");
    }

    public List<TouristAttraction> getByCity(String city) {
        List<TouristAttraction> result = new ArrayList<>();
        for (TouristAttraction a : data) {
            if (a.getCity().equalsIgnoreCase(city)) {
                result.add(a);
            }
        }
        return result;
    }

    public TouristAttraction saveAttraction(TouristAttraction attraction) {
        data.add(attraction);
        return attraction;
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction u) {
        for (TouristAttraction a : data) {
            if (a.getName().equalsIgnoreCase(name)) {
                a.setDescription(u.getDescription());
                a.setCity(u.getCity());
                List<String> newTags = (u.getTags() == null) ? List.of() : u.getTags();
                // u.getTags() er null? ja: brug en tom liste i stedet (List.of()). nej: brug gamle tags
                a.getTags().clear(); // fjerne gamle
                a.getTags().addAll(newTags); // tilføjer de nye
                return a;
            }
        }
        return null;
    }

    public boolean deleteAttraction(String name) {
        for (int i = 0; i < data.size(); i++) {
            TouristAttraction a = data.get(i);
            if (a.getName().equalsIgnoreCase(name)) {
                data.remove(a);
                return true;
            }
        }
        return false;
    }
}