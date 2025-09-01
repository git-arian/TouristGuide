package com.example.touristguide.repository;
import com.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final ArrayList<TouristAttraction> data = new ArrayList<>();

    public TouristRepository() {
        data.add(new TouristAttraction("Tivoli", "Forlystelspark midt i KBH, og DK's mest besøgte turistattraktion med 4m årlige besøg.", "cph"));
        data.add(new TouristAttraction("Strøget", "Berømt og livlig gågade i indre by.", "cph"));
        data.add(new TouristAttraction("Eiffeltårnet", "Populært turistmål, opført i 1889 på 312m, og var verdens højeste bygning over 40 år.", "paris"));
        data.add(new TouristAttraction("Louvre", "Enormt museum i Paris, med en samling på 38000+ genstande fra fortiden til det 21. århundrede, som fx Mona Lisa.", "paris"));
    }

    public ArrayList<TouristAttraction> getAllAttractions() {
        return data;
    }

    public List<TouristAttraction> getByName(String name) {
        List<TouristAttraction> result = new ArrayList<>();
        for (TouristAttraction a : data) {
            if (a.getName().equalsIgnoreCase(name)) {
                result.add(a);
            }
        }
        return result; //
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

    public TouristAttraction createAttraction(TouristAttraction attraction) {
        data.add(attraction);
        return attraction;
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction) {
        for (TouristAttraction a : data) {
            if (a.getName().equalsIgnoreCase(name)) {
                a.setName(updatedAttraction.getName());
                a.setDescription(updatedAttraction.getDescription());
                a.setCity(updatedAttraction.getCity());
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