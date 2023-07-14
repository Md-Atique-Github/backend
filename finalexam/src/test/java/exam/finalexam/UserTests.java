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
import exam.finalexam.Controller.UserController;
import exam.finalexam.Pojo.User;
import exam.finalexam.Repository.UserRepo;


@SpringBootTest
class UserTests {


private MockMvc mvc;
	@Mock
	private UserRepo userRepo;

	@InjectMocks
	private UserController userController;

	private JacksonTester<User> jsonUser;

	private JacksonTester<Collection<User>> jsonUsers;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void AddUser() throws Exception {
		User user = new User(1, "address", "address", 12345678, 100, true,null, null);
       when(userRepo.save(user)).thenReturn((user));
		mvc.perform(post("/user/post")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonUser.write(user).getJson()))
				.andExpect(status().isOk());

	}

	@Test
	public void canGetAllCars() throws Exception {
		User user1 = new User(1, "address", "address", 12345678, 100, true, "2023-07-18 19:49:00", "2023-07-18 19:49:00");
		User user2 = new User(2, "address", "address", 12345678, 100, true, "2023-07-18 19:49:00", "2023-07-18 19:49:00");

       
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		when(userRepo.findAll()).thenReturn(userList);
		mvc.perform(get("/user/get")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonUsers.write(userList).getJson()));
	}

	@Test
	public void GetACar() throws Exception {
		User user3 = new User(1, "address", "address", 12345678, 100, true, "2023-07-18 19:49:00", "2023-07-18 19:49:00");
		when(userRepo.findById(1)).thenReturn(Optional.of(user3));
		mvc.perform(MockMvcRequestBuilders.get("/user/get/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonUser.write(user3).getJson()));
	}

}
