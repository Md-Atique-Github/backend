package exam.finalexam.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exam.finalexam.Pojo.User;
import exam.finalexam.Repository.UserRepo;

@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UserController {
    
@Autowired
    private UserRepo userRepo;

    @PostMapping("/post")
    public void saveData(@RequestBody User user) {
        userRepo.save(user);
    }


    @GetMapping("/get")

    public List<User> getData() {
        List<User> turist = userRepo.findAll();
        return turist;
    }

    @GetMapping("/get/{id}")
    public Optional<User> getById(@PathVariable Integer id) {
         return userRepo.findById(id);
    }

}
