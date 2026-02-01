package com.example.curddemo;


import com.example.curddemo.model.User;
import com.example.curddemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CurddemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Test
	void testCreateUser() throws Exception {
		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"John\",\"email\":\"john@example.com\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("John"));
	}

	@Test
	void testGetUsers() throws Exception {
		userRepository.save(new User() {{ setName("Alice"); setEmail("alice@example.com"); }});
		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").exists());
	}
//
//	@Test
//	void testUpdateUser() throws Exception {
//		User user = userRepository.save(new User() {{ setName("Bob"); setEmail("bob@example.com"); }});
//		mockMvc.perform(put("/api/users/" + user.getId())
//						.contentType(MediaType.APPLICATION_JSON)
//						.content("{\"name\":\"Bob Updated\",\"email\":\"bob.updated@example.com\"}"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.name").value("Bob Updated"));
//	}
//
//	@Test
//	void testDeleteUser() throws Exception {
//		User user = userRepository.save(new User() {{ setName("Charlie"); setEmail("charlie@example.com"); }});
//		mockMvc.perform(delete("/api/users/" + user.getId()))
//				.andExpect(status().isOk());
//	}
}

