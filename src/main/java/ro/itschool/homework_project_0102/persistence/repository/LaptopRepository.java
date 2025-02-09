package ro.itschool.homework_project_0102.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.itschool.homework_project_0102.persistence.entity.Laptop;

import java.util.List;

public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
    List<Laptop> getLaptopsByMake(String make);

    //JPQL
    @Query("SELECT l FROM Laptop l WHERE l.make = ?1 AND l.model = ?2")
    List<Laptop> findLaptopsByNameJPQL(String make, String model);

    @Query(value = "SELECT * FROM laptop WHERE make = ?1 AND model = ?2", nativeQuery = true)
    List<Laptop> findLaptopsByNameNative(String make, String model);
}
