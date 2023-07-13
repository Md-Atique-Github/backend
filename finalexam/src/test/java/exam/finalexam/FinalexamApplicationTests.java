package exam.finalexam;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import exam.finalexam.Controller.CarController;
import exam.finalexam.Pojo.Car;
import exam.finalexam.Repository.CarRepo;




@SpringBootTest
class FinalexamApplicationTests {

private MockMvc mvc;
	@Mock
	private CarRepo carsRepo;

	@InjectMocks
	private CarController carsController;

	private JacksonTester<Car> jsonCar;

	private JacksonTester<Collection<Car>> jsonCars;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(carsController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void AddCar() throws Exception {
		Car car = new Car(1, "image1", "Civic1", "Short Discription", 100, "Long Discription");
       when(carsRepo.save(car)).thenReturn((car));
		mvc.perform(post("/RoadRunner/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonCar.write(car).getJson()))
				.andExpect(status().isOk());

	}

	@Test
	public void canGetAllCars() throws Exception {
		Car car1 = new Car(1, "image2", "Civic2", "Short Discription", 100, "Long Discription");
		Car car2 = new Car(1, "image3", "Civic3", "Short Discription", 100, "Long Discription");

       
		List<Car> carList = new ArrayList<>();
		carList.add(car1);
		carList.add(car2);
		when(carsRepo.findAll()).thenReturn(carList);
		mvc.perform(get("/RoadRunner/get")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonCars.write(carList).getJson()));
	}

	@Test
	public void GetACar() throws Exception {
		Car car3 = new Car(1,"image2","Civic2","Short Discription", 10, "Long Discription");
		when(carsRepo.findById(1)).thenReturn(Optional.of(car3));
		mvc.perform(MockMvcRequestBuilders.get("/RoadRunner/get/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonCar.write(car3).getJson()));
	}

}
