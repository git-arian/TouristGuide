package com.example.touristguide.service;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TouristService {
    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractions();
    }

    public TouristAttraction getByName(String name) {
        return repository.getByName(name);
    }

    public List<TouristAttraction> getByCity(String city) {
        return repository.getByCity(city);
    }

    public TouristAttraction createAttraction(TouristAttraction attraction){
        return repository.createAttraction(attraction);
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction){
        return repository.updateAttraction(name, updatedAttraction);
    }

    public boolean deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }
}