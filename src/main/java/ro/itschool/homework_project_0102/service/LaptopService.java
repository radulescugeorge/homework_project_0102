package ro.itschool.homework_project_0102.service;

import org.springframework.stereotype.Service;
import ro.itschool.homework_project_0102.model.Laptop;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaptopService {

    private static final List<Laptop> LAPTOPS = new ArrayList<>();

    public List<Laptop> getAllLaptops(){
        return LAPTOPS;
    }

    public Laptop getLaptopById(int id){
        return LAPTOPS.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addLaptop(Laptop laptop){
        LAPTOPS.add(laptop);
    }

    public void deleteLaptopById(int id){
        LAPTOPS.removeIf(l->l.getId() == id);
    }

    public void updateLaptop(Laptop laptop, Laptop existingLaptop){

        if(laptop.getMake() != null){
            existingLaptop.setMake(laptop.getMake());
        }
        if(laptop.getModel() != null){
            existingLaptop.setModel(laptop.getModel());
        }
        if(laptop.getSsdCapacity() != 0){
            existingLaptop.setSsdCapacity(laptop.getSsdCapacity());
        }
        if(laptop.getRamCapacity() != 0){
            existingLaptop.setRamCapacity(laptop.getRamCapacity());
        }
    }


    public void replaceLaptop(Laptop laptop, Laptop existingLaptop){
        existingLaptop.setMake(laptop.getMake());
        existingLaptop.setModel(laptop.getModel());
        existingLaptop.setSsdCapacity(laptop.getSsdCapacity());
        existingLaptop.setRamCapacity(laptop.getRamCapacity());
    }



}
