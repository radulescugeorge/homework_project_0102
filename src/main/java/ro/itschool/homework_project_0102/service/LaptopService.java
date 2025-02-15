package ro.itschool.homework_project_0102.service;

import org.springframework.stereotype.Service;
import ro.itschool.homework_project_0102.dto.LaptopDto;
import ro.itschool.homework_project_0102.mapper.ObjectMapper;
import ro.itschool.homework_project_0102.persistence.entity.Laptop;
import ro.itschool.homework_project_0102.persistence.repository.LaptopRepository;

import java.util.List;

@Service
public class LaptopService {


    private final LaptopRepository laptopRepository;
    private final ObjectMapper<LaptopDto, Laptop> laptopMapper;

    public LaptopService(LaptopRepository laptopRepository,
                         ObjectMapper<LaptopDto, Laptop> laptopMapper) {
        this.laptopRepository = laptopRepository;
        this.laptopMapper = laptopMapper;
    }

    public List<LaptopDto> getLaptopsByMake(String make){
        List<Laptop> laptopsByMake = laptopRepository.getLaptopsByMake(make);
        return laptopsByMake.stream()
                .map(laptopMapper::mapToDto)
                .toList();
    }

    public List<LaptopDto> getLaptopsByMakeAndModel(String make, String model){

        // List<Laptop> laptops = laptopRepository.findLaptopsByNameNative(make, model)

        List<Laptop> laptops = laptopRepository.findLaptopsByNameJPQL(make, model);
        return laptops.stream()
                .map(laptopMapper::mapToDto)
                .toList();
    }

    public List<LaptopDto> getAllLaptops() {
        List<Laptop> allLaptops = laptopRepository.findAll();
        return allLaptops.stream()
                .map(laptopMapper::mapToDto)
                .toList();
    }

    public Laptop getLaptopById(int id) {
       return laptopRepository.findById(id).orElse(null);
    }

    public void addLaptop(LaptopDto laptopDto) {
        Laptop laptop = laptopMapper.mapToEntity(laptopDto);
        laptopRepository.save(laptop);
    }

    public void deleteLaptopById(int id) {
        laptopRepository.deleteById(id);
    }

    public LaptopDto updateLaptop(int id, LaptopDto laptopDto) {

        Laptop existingLaptop = getLaptopById(id);

        if(existingLaptop == null) return null;

        if (laptopDto.getMake() != null) {
            existingLaptop.setMake(laptopDto.getMake());
        }
        if (laptopDto.getModel() != null) {
            existingLaptop.setModel(laptopDto.getModel());
        }
        if (laptopDto.getSsdCapacity() != 0) {
            existingLaptop.setSsdCapacity(laptopDto.getSsdCapacity());
        }
        if (laptopDto.getRamCapacity() != 0) {
            existingLaptop.setRamCapacity(laptopDto.getRamCapacity());
        }

        return laptopMapper.mapToDto(laptopRepository.save(existingLaptop));
    }


    public LaptopDto replaceLaptop(int id, LaptopDto laptopDto) {

        Laptop existingLaptop = getLaptopById(id);

        existingLaptop.setMake(laptopDto.getMake());
        existingLaptop.setModel(laptopDto.getModel());
        existingLaptop.setSsdCapacity(laptopDto.getSsdCapacity());
        existingLaptop.setRamCapacity(laptopDto.getRamCapacity());

        return laptopMapper.mapToDto(laptopRepository.save(existingLaptop));
    }


}
