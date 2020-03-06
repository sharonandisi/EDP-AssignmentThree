package Database;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StudentViewModel extends AndroidViewModel {

    private EventDrivenDatabase db;

    public StudentViewModel(@NonNull Application application){
        super(application);
        db = Room.databaseBuilder(application, EventDrivenDatabase.class, "NewStudentObj-db").build();
    }

    public void createStudent(NewStudentObj student){
        Completable.fromAction(()->{
            db.studentDao().insert(student);
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(()-> {
                    Toast.makeText(getApplication(), "Inserted",
                            Toast.LENGTH_LONG).show();
                }).subscribe();

    }

    private MutableLiveData<Long> _count = new MutableLiveData<>();
    public LiveData<Long> count = _count;
    public void getStudentCount(){
        db.studentDao()
                .countRecordsInStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    _count.postValue(aLong);

                });
                }
    private void getStudents(NewStudentObj,student){
        StringBuilder builder = new StringBuilder();
        for (int i = student, i>0, i++)
        builder.append(String.format("%d, \n", i));
        return builder.toString();
    }

    }

