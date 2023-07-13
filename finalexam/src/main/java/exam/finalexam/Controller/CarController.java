package exam.finalexam.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.finalexam.Pojo.Car;
import exam.finalexam.Repository.CarRepo;

@CrossOrigin("*")
@RestController
@RequestMapping("/RoadRunner")
public class CarController {

     @Autowired
    private CarRepo carRepo;

    @PostMapping("/post")
    public void saveData(@RequestBody Car car) {
        carRepo.save(car);
    }

     @GetMapping("/get")

    public List<Car> getData() {
        List<Car> car = carRepo.findAll();
        return car;
    }

     @GetMapping("/get/{id}")
    public Optional<Car> getById(@PathVariable Integer id) {
       return carRepo.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        carRepo.deleteById(id);
        return ResponseEntity.ok().body("Deleted successfully");
}

    
}
