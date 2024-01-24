package gb.ru.task2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudentService studentService;

	@Test
	@DirtiesContext
	public void testSearchStudentsByName() throws Exception {
		// Создаем студентов с именем, содержащим подстроку
		Student student1 = studentService.createStudent(new Student("John Doe", "Group1"));
		Student student2 = studentService.createStudent(new Student("Jane Smith", "Group2"));

		// Выполняем запрос GET /student/search?name='Doe'
		mockMvc.perform(get("/student/search").param("name", "Doe"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(student1.getId().intValue())))
				.andExpect(jsonPath("$[0].name", is(student1.getName())))
				.andExpect(jsonPath("$[0].groupName", is(student1.getGroupName())));
	}

	@Test
	@DirtiesContext
	public void testGetStudentsByGroup() throws Exception {
		// Создаем студентов в разных группах
		Student student1 = studentService.createStudent(new Student("John Doe", "Group1"));
		Student student2 = studentService.createStudent(new Student("Jane Smith", "Group2"));

		// Выполняем запрос GET /group/Group1/student
		mockMvc.perform(get("/student/group/{groupName}/student", "Group1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(student1.getId().intValue())))
				.andExpect(jsonPath("$[0].name", is(student1.getName())))
				.andExpect(jsonPath("$[0].groupName", is(student1.getGroupName())));
	}

	@Test
	@DirtiesContext
	public void testCreateStudent() throws Exception {
		// Создаем JSON-представление студента
		String studentJson = "{ \"name\": \"John Doe\", \"groupName\": \"Group1\" }";

		// Выполняем запрос POST /student
		mockMvc.perform(post("/student")
						.contentType("application/json")
						.content(studentJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name", is("John Doe")))
				.andExpect(jsonPath("$.groupName", is("Group1")));
	}

	@Test
	@DirtiesContext
	public void testDeleteStudent() throws Exception {
		// Создаем студента и сохраните его в базе данных
		Student student = studentService.createStudent(new Student("John Doe", "Group1"));

		// Выполняем запрос DELETE /student/{id}
		mockMvc.perform(delete("/student/{id}", student.getId()))
				.andExpect(status().isOk());

		// Проверяем, что студент удален
		mockMvc.perform(get("/student/{id}", student.getId()))
				.andExpect(status().isNotFound());
	}
}

