package Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.eventdrivenprogramming.livedata.Student;

import io.reactivex.Flowable;
import io.reactivex.Maybe;


//data access object ->where we write our queries
@Dao
public interface StudentDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(NewStudentObj student);

    @Query("select count(*) from students")
    Flowable<Long> countRecordsInStudents();
    @Query("select * from students where firstName = :firstName")
    Maybe<Student> findStudentByName(String firstName);
}
