package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.exo1.enums.GenreMusical;
import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.context.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testRequestCountByGenreMusicalEquals(){
        //given
        ArtistEntity artistEntity = ArtistEntity
                .builder()
                .name("Bonjovi")
                .biography("a legacy")
                .genreMusical(GenreMusical.RANDB)
                .build();

        ArtistEntity artistEntity1 = ArtistEntity
                .builder()
                .name("All time low")
                .biography("do I need to say anything ?")
                .genreMusical(GenreMusical.RANDB)
                .build();

        artistRepository.save(artistEntity);
        artistRepository.save(artistEntity1);

        //when
        int artistEntitiesRespons = artistRepository.countByGenreMusicalEquals(GenreMusical.RANDB);

        //then
        assertThat(artistEntitiesRespons).isEqualTo(2);


    }

    @Test
    void testRequestCountByGenreMusicalNotEquals(){
        //given
        ArtistEntity artistEntity = ArtistEntity
                .builder()
                .name("Bonjovi")
                .biography("a legacy")
                .genreMusical(GenreMusical.RANDB)
                .build();

        ArtistEntity artistEntity1 = ArtistEntity
                .builder()
                .name("All time low")
                .biography("do I need to say anything ?")
                .genreMusical(GenreMusical.RANDB)
                .build();

        artistRepository.save(artistEntity);
        artistRepository.save(artistEntity1);

        //when
        int artistEntitiesRespons = artistRepository.countByGenreMusicalEquals(GenreMusical.SLAM);

        //then
        assertThat(artistEntitiesRespons).isEqualTo(0);


    }
}
