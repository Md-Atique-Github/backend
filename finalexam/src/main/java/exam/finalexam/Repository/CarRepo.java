package exam.finalexam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exam.finalexam.Pojo.Car;

public interface CarRepo extends JpaRepository<Car,Integer> {
    
}
