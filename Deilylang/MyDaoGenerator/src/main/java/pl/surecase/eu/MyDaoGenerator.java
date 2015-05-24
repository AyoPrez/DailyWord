package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "deilyword");
        CreateUserMomentsEntity(schema);
        CreateUserWordsEntity(schema);
        new DaoGenerator().generateAll(schema, args[0]);
    }

    public static void CreateUserMomentsEntity(Schema schema){
        Entity moments = schema.addEntity("UserMoments");
        moments.addIdProperty();
        moments.addStringProperty("language");
        moments.addStringProperty("level");
        moments.addStringProperty("time");
        moments.addStringProperty("applanguage");
    }

    public static void CreateUserWordsEntity(Schema schema){
        Entity words = schema.addEntity("UserWords");
        words.addIdProperty();
        words.addIntProperty("IdWord");
        words.addStringProperty("language");
    }
}