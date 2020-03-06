package Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NewStudentObj.class}, version = 1)

public abstract class EventDrivenDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
}
