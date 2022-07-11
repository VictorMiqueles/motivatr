
package com.davmt.motivatr;

import com.dbtesting.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@DataJpaTest
@Sql(scripts = "/create-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
public class UserRepositoryH2Test {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUsersByNameLongerThanTest() {
        List<User> users = userRepository.findUsersByNameLongerThan(4);
        Assertions.assertEquals("Victor", users.stream().findAny().get().getName());
    }    

    @Test
    void findUserByEmailTest() {
        User user = userRepository.findByEmail("vic@example.org");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Victor", user.getName());
    }

}

// package com.davmt.motivatr;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// class MotivatrApplicationTests {

//   @Test
//   void contextLoads() {
//   }

// }