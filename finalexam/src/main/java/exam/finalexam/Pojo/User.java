package exam.finalexam.Pojo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User Table")
public class User {

    // public User(int id2, String name2, String address2, int i, int driverLicenseNo2, boolean insurance2, Object pickUp2,
    //         Object dropOff2) {
    // }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String phone;
    private Integer driverLicenseNo;
    private boolean insurance;
    private LocalDateTime pickUp;
    private LocalDateTime dropOff;

}
