package com.example.touristguide.controller;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping
    public String showAllAttractions(Model model) { // Model = container, bruges til at sende data til view
        List<TouristAttraction> all = service.getAllAttractions(); // henter alle attraktioner fra service
        model.addAttribute("attractions", all); // lægger listen i model under navnet "attractions"
        return "attractionList"; // returnerer Thymeleaf-viewet templates/attractionList.html
    }

    @GetMapping("/{name}/tags")
    public String showAttractionsByName(@PathVariable String name, Model model) {
        TouristAttraction a = service.getByName(name);

        if (a == null) {
            return "error"; // TODO redirect
        }

        model.addAttribute("attraction", a);
        model.addAttribute("tags", a.getTags());
        return "tags";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "form";
    }

    @PostMapping("/save")
    public String saveAttraction(@RequestParam String name,
                                 @RequestParam String description,
                                 @RequestParam String city,
                                 @RequestParam(required = false) List<String> tags) {
        // hvis ingen tags valg, håndterer vi det nu. Derfor (required=false) i parameter
        if (tags == null) tags = new ArrayList<>(); // tom
        TouristAttraction a = new TouristAttraction(name, description, city, tags);
        service.saveAttraction(a);
        return "redirect:/attractions";
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