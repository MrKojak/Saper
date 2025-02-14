package databaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;//jusz wiem
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
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
    @Getter
    @Setter
    @Column(name = "isBombed")
    private boolean isBombed;
    @Getter
    @Setter
    @Column(name = "bombsAround")
    private int bombsAround;
    @Getter
    @Setter
    @Column(name = "fieldStatus")
    @Enumerated(EnumType.STRING)
    private FieldStatus fieldStatus;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "gameName")
    private GameNameEntity gameName;

}
