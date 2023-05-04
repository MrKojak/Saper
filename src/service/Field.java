package service;

public class Field {
    private final int id;
    private boolean isBombed = false;
    private int bombsAround = 0;
    private FieldStatus fieldStatus = FieldStatus.COVER;
    public Field(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setUncover() {
        this.fieldStatus = FieldStatus.UNCOVER;
    }

    public void setFlag() {
        this.fieldStatus = FieldStatus.FLAGGED;
    }

    public FieldStatus getFieldStatus() {
        return fieldStatus;
    }

    void setBomb() {
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
