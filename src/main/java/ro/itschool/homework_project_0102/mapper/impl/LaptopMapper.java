package ro.itschool.homework_project_0102.mapper.impl;


import org.springframework.stereotype.Component;
import ro.itschool.homework_project_0102.dto.LaptopDto;
import ro.itschool.homework_project_0102.mapper.ObjectMapper;
import ro.itschool.homework_project_0102.persistence.entity.Laptop;

@Component("laptopMapper")
public class LaptopMapper implements ObjectMapper <LaptopDto, Laptop> {


    @Override
    public LaptopDto mapToDto(Laptop laptop) {
        return new LaptopDto(
                laptop.getId(),
                laptop.getMake(),
                laptop.getModel(),
                laptop.getSsdCapacity(),
                laptop.getRamCapacity()
        );
    }

    @Override
    public Laptop mapToEntity(LaptopDto laptopDto) {
        Laptop laptop = new Laptop();
        laptop.setMake(laptopDto.getMake());
        laptop.setModel(laptopDto.getModel());
        laptop.setSsdCapacity(laptopDto.getSsdCapacity());
        laptop.setRamCapacity(laptopDto.getRamCapacity());

        return laptop;
    }
}
