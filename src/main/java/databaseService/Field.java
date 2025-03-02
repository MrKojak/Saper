package databaseService;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Field {

    @Getter
    private int id;
    private boolean isBombed = false;
    private int bombsAround = 0;
    @Getter
    private FieldStatus fieldStatus = FieldStatus.COVER;
    public Field(int id) {
        this.id = id;
    }
    public void setUncover() {
        this.fieldStatus = FieldStatus.UNCOVER;
    }

    public void setFlag() {
        this.fieldStatus = FieldStatus.FLAGGED;
    }
    public void unFlag() {
        if(fieldStatus == FieldStatus.FLAGGED){
        this.fieldStatus = FieldStatus.COVER;}
        else{
            System.out.println("This field is not flagged");
        }
    }
    public void setBomb() {
        this.isBombed = true;
    }
    public boolean isBombed() {
        return this.isBombed;
    }

    public int getQuantityBombsAround() {
        return this.bombsAround;
    }

    public void
    increaseBombsAround() {
        this.bombsAround = this.bombsAround + 1;
    }
}
