package databaseService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@Entity
@Table(name = "gameNames")
public class GameNameEntity {

        public GameNameEntity(String name){

                this.name = name;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int game_id;

        @Getter
        @Setter
        @Column(name = "gameName")
        private String  name;

        @Getter
        @Setter
        @OneToMany(mappedBy = "gameName", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<FieldEntity> fieldEnities;


        // getters and setters

}
