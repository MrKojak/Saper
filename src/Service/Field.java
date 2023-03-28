package Service;

public class Field {

    private Integer id = 0;

    private Integer valueOnField;

    public Integer getId() {
        return id;
    }
    public boolean isBombed() {
        return this.isBombed;
    }

    private boolean isBombed = false;
    private int quantityBombsAround = 0;

    public FieldStatus getFieldStatus() {
        return fieldStatus;
    }

    private FieldStatus fieldStatus = FieldStatus.COVER;

    public void uncoverField() {
         this.fieldStatus = FieldStatus.UNCOVER;
    }
    public void setFlagueStatus() {
         this.fieldStatus = FieldStatus.FLAGUE;
    }

    void setBombToField(){this.isBombed = true;}
    public Field(int id) {
        this.id = id;
    }

    public int getQuantityBombsAround(){
        return this.quantityBombsAround;
    }

    public void increaseNumberOfBombsAround() {
        this.quantityBombsAround= this.quantityBombsAround+1;
    }
}
