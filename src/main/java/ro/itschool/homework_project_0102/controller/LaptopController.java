package ro.itschool.homework_project_0102.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.homework_project_0102.dto.LaptopDto;
import ro.itschool.homework_project_0102.mapper.impl.LaptopMapper;
import ro.itschool.homework_project_0102.service.LaptopService;

import java.util.List;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    private final LaptopService laptopService;
    private final LaptopMapper laptopMapper;

    public LaptopController(LaptopService laptopService, LaptopMapper laptopMapper){
        this.laptopService = laptopService;
        this.laptopMapper = laptopMapper;
    }

    @GetMapping
    public List<LaptopDto> getAllLaptops(){
        return laptopService.getAllLaptops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaptopDto> getLaptopById(@PathVariable int id){
        LaptopDto laptopDtoById = laptopMapper.mapToDto(laptopService.getLaptopById(id));

        if(laptopDtoById == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(laptopDtoById);
    }

    @GetMapping("/make")
    public List<LaptopDto> getAllLaptops(@RequestParam String make){
        return laptopService.getLaptopsByMake(make);
    }


    @GetMapping("/make/model")
    public List<LaptopDto> getLaptopsByMakeAndModel(@RequestParam String make,
                                                    @RequestParam String model){
        return laptopService.getLaptopsByMakeAndModel(make, model);
    }

    @PostMapping
    public ResponseEntity<String> addLaptop(@RequestBody LaptopDto laptopDto){
        laptopService.addLaptop(laptopDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added beautifully.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LaptopDto> updateLaptopById(@PathVariable int id,
                                                      @RequestBody LaptopDto laptopDto){

        LaptopDto updatedLaptop = laptopService.updateLaptop(id, laptopDto);

        if(updatedLaptop == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedLaptop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaptopDto> replaceLaptop(@PathVariable int id,
                                                   @RequestBody LaptopDto laptopDto){

        LaptopDto updatedLaptop = laptopService.updateLaptop(id, laptopDto);

        if(updatedLaptop == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedLaptop);
    }

    @DeleteMapping("/{id}")
    public void deleteLaptopById(@PathVariable int id){
        laptopService.deleteLaptopById(id);
    }
}
