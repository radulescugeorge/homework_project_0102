package ro.itschool.homework_project_0102.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.homework_project_0102.model.Laptop;
import ro.itschool.homework_project_0102.service.LaptopService;

import java.util.List;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService){
        this.laptopService = laptopService;
    }

    @GetMapping
    public List<Laptop> getAllLaptops(){
        return laptopService.getAllLaptops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laptop> getLaptopById(@PathVariable int id){
        Laptop laptopById = laptopService.getLaptopById(id);
        if(laptopById == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(laptopById);
    }

    @PostMapping
    public ResponseEntity<String> addLaptop(@RequestBody Laptop laptop){
        laptopService.addLaptop(laptop);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added beautifully.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Laptop> updateLaptopById(@PathVariable int id,
                                                   @RequestBody Laptop laptop){

        Laptop existingLaptop = laptopService.getLaptopById(id);
        if(existingLaptop == null){
            return ResponseEntity.notFound().build();
        }
        laptopService.updateLaptop(laptop, existingLaptop);
        return ResponseEntity.ok(existingLaptop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laptop> replaceLaptop(@PathVariable int id,
                                                @RequestBody Laptop laptop){
        Laptop existingLaptop = laptopService.getLaptopById(id);
        if(existingLaptop == null){
            return ResponseEntity.notFound().build();
        }
        laptopService.replaceLaptop(laptop, existingLaptop);
        return ResponseEntity.ok(existingLaptop);
    }

    @DeleteMapping("/{id}")
    public void deleteLaptopById(@PathVariable int id){
        laptopService.deleteLaptopById(id);
    }
}
