package exam.finalexam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exam.finalexam.Pojo.User;

public interface UserRepo extends JpaRepository<User,Integer> {
    
}
