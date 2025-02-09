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

    public LaptopDto getLaptopById(int id) {
        Laptop laptopById = laptopRepository.getReferenceById(id);
        return laptopMapper.mapToDto(laptopById);
    }

    public void addLaptop(LaptopDto laptopDto) {
        Laptop laptop = laptopMapper.mapToEntity(laptopDto);
        laptopRepository.save(laptop);
    }

    public void deleteLaptopById(int id) {
        laptopRepository.deleteById(id);
    }

    public void updateLaptop(LaptopDto laptopDto, LaptopDto existingLaptopDto) {

        if (laptopDto.getMake() != null) {
            existingLaptopDto.setMake(laptopDto.getMake());
        }
        if (laptopDto.getModel() != null) {
            existingLaptopDto.setModel(laptopDto.getModel());
        }
        if (laptopDto.getSsdCapacity() != 0) {
            existingLaptopDto.setSsdCapacity(laptopDto.getSsdCapacity());
        }
        if (laptopDto.getRamCapacity() != 0) {
            existingLaptopDto.setRamCapacity(laptopDto.getRamCapacity());
        }
    }


    public void replaceLaptop(LaptopDto laptopDto, LaptopDto existingLaptopDto) {
        existingLaptopDto.setMake(laptopDto.getMake());
        existingLaptopDto.setModel(laptopDto.getModel());
        existingLaptopDto.setSsdCapacity(laptopDto.getSsdCapacity());
        existingLaptopDto.setRamCapacity(laptopDto.getRamCapacity());
    }


}
