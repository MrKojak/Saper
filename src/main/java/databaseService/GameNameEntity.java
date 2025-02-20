package databaseService;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "gameNames")
public class GameNameEntity {

        public GameNameEntity(String name){
                this.name = name;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int game_id;

        @Column(name = "gameName")
        private String  name;

        @OneToMany(mappedBy = "gameName", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<FieldEntity> fieldEnities;


        // getters and setters

}
