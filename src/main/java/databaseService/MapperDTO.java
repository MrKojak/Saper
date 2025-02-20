package databaseService;

import java.util.ArrayList;
import java.util.List;

public class MapperDTO {
    List<Field> listOfFields = new ArrayList<>();

    public Field[][] mapEntitiesToFieldList(List<FieldEntity> entities) {
        for (FieldEntity entity : entities) {
            Field field = convertEntityToDTO(entity);
            listOfFields.add(field);
        }

        return mapListFieldsToTable(listOfFields);
    }

    private Field convertEntityToDTO(FieldEntity entity) {
        return new Field(entity.getId(), entity.isBombed(), entity.getBombsAround(), entity.getFieldStatus());
    }

    public Field[][] mapListFieldsToTable(List<Field> listOfFields) {

        Field[][] fields = new Field[10][10];
        int fieldnumber = 0;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                fields[x][y] = listOfFields.get(fieldnumber);
                fieldnumber++;
            }
        }
        return fields;
    }
}