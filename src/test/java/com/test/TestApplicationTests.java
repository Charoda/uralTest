package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.TestEntity;
import com.test.entity.projects.Project;
import com.test.entity.tasks.ManagerTask;
import com.test.entity.tasks.Status;
import com.test.entity.tasks.UserTask;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class TestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void testSerialization() throws Exception {
//        Project myObject = new Project();
//        myObject.setProjectName("project1");
//        myObject.setId(1L);
//        myObject.addUserTask(ManagerTask.builder()
//                .taskName("taskManager")
//                .creationDate(ZonedDateTime.now())
//                .)
//
//        mockMvc.perform(post("/myendpoint")
//                        .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(myObject)))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"name\":\"test\",\"value\":123}"));
//    }

//	@PersistenceContext
//	private EntityManager entityManager;
//
//	public TestApplicationTests(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
//
//	@Test
//	@Transactional
//	void managerTask() {
//		ManagerTask mt = ManagerTask
//				.builder()
//				.taskName("taskN1")
//				.status(Status.NEW)
//				.creationDate(ZonedDateTime.now())
//				.build();
////		entityManager.persist(test);
//		entityManager.persist(mt);
//		ManagerTask mt2 =  entityManager.find(ManagerTask.class,1);
//		System.out.println(mt2.getProjectName());
//	}

//	@Test
//	@Transactional
//	void usertest() {
//		TestEntity test1 = TestEntity.builder()
//				.name("testN1").build();
//
////		entityManager.persist(test);
//		entityManager.persist(test1);
//		TestEntity test2 =  entityManager.find(TestEntity.class,1);
//		System.out.println(test2.getName());
//	}


}
