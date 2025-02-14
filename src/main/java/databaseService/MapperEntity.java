package databaseService;

import java.util.ArrayList;
import java.util.List;

public class MapperEntity {
    List<FieldEntity> listOfEntity = new ArrayList<>();

    public List<FieldEntity>mapDtoToEntityList(Field[][] tableOfFields) {
        for (Field[] fields : tableOfFields) {
            for(Field field : fields){
                FieldEntity fieldEntity = new FieldEntity(field.isBombed(), field.getQuantityBombsAround(), field.getFieldStatus());
                listOfEntity.add(fieldEntity);
            }
        }
        return listOfEntity;
    }
}
