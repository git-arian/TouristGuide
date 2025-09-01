package com.example.touristguide.controller;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    /*
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TouristAttraction>> getAllAttractions() {
        List<TouristAttraction> all = service.getAllAttractions();
        return ResponseEntity.ok(all);
    }
    I Del 2 fjernes @ResponseBody og ResponseEntity, da der nu ikke skal returneres JSON

    Nu bruger vi Thymeleaf, hvor vi i stedet returner et view (html side) i stedet for JSON.
    */

    @GetMapping
    public String showAllAttractions(Model model) { // Model = container, bruges til at sende data til view
        List<TouristAttraction> all = service.getAllAttractions(); // henter alle attraktioner fra service
        model.addAttribute("attractions", all); // lægger listen i model under navnet "attractions"
        return "attractionList"; // returnerer Thymeleaf-viewet templates/attractionList.html
    }

    @GetMapping("/name/{name}")
    @ResponseBody
    public ResponseEntity<List<TouristAttraction>> getAttractionsByName(@PathVariable String name) {
        List<TouristAttraction> attractions = service.getByName(name);
        if (attractions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    @ResponseBody
    public ResponseEntity<List<TouristAttraction>> getAttractionsByCity(@PathVariable String city) {
        List<TouristAttraction> attractions = service.getByCity(city);
        if (attractions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attractions);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<TouristAttraction> createAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction saved = service.createAttraction(attraction);
        if (saved == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/update/{name}")
    @ResponseBody
    public ResponseEntity<TouristAttraction> updateAttraction(@PathVariable String name, @RequestBody TouristAttraction updated) {
        TouristAttraction saved = service.updateAttraction(name, updated);
        if (saved == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/delete/{name}")
    @ResponseBody
    public ResponseEntity<Void> deleteAttraction(@PathVariable String name) {
        boolean deleted = service.deleteAttraction(name);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}