package databaseService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "fields")
public class FieldEntity {

    public FieldEntity( boolean isBombed, int bombsAround, FieldStatus fieldStatus){

        this.isBombed = isBombed;
        this.bombsAround = bombsAround;
        this.fieldStatus = fieldStatus;

    }
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isBombed")
    private boolean isBombed;

    @Column(name = "bombsAround")
    private int bombsAround;

    @Column(name = "fieldStatus")
    @Enumerated(EnumType.STRING)
    private FieldStatus fieldStatus;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameNameEntity gameName;

}
