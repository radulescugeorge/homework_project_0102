package ro.itschool.homework_project_0102.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


// using lombok annotation. Check in Settings -> Compiler -> Annotation
@Setter
@Getter
@AllArgsConstructor
public class LaptopDto {

    private final int id;
    private String make;
    private String model;
    private int ssdCapacity;
    private int ramCapacity;

}
