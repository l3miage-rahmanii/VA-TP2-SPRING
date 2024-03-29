package fr.uga.l3miage.spring.tp2.exo1.repositories;


import fr.uga.l3miage.spring.tp2.exo1.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.context.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testRequestFindAllByMailContaining(){
        //given
        UserEntity userEntity = UserEntity
                .builder()
                .mail("test@gmail.com")
                .name("google 1")
                .build();

        UserEntity userEntity2 = UserEntity
                .builder()
                .mail("test@test.com")
                .name("non google")
                .build();

        userRepository.save(userEntity);
        userRepository.save(userEntity2);

        // when
        Set<UserEntity> userEntitiesResponses = userRepository.findAllByMailContaining("gmail");

        //then
        assertThat(userEntitiesResponses).hasSize(1);
        assertThat(userEntitiesResponses.stream().findFirst().get().getMail()).isEqualTo("test@gmail.com");
    }

    @Test
    void testRequestFindAllByMailNotContainig(){
        //given
        UserEntity userEntity = UserEntity
                .builder()
                .mail("test@test.com")
                .name("non google")
                .build();

        userRepository.save(userEntity);


        //when
        Set<UserEntity> userEntitiesResponses = userRepository.findAllByMailContaining("gmail");


        //then
        assertThat(userEntitiesResponses).hasSize(0);


    }
}
