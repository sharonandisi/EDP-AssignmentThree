package Database;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.eventdrivenprogramming.R;
import com.google.android.material.textfield.TextInputEditText;

public class DatabaseActivity extends AppCompatActivity {
    private StudentViewModel viewModel;
    private TextInputEditText edtfirstName, edtsurname, edtage;
    private Button submit;
    private TextView count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        viewModel.getStudentCount();

        edtfirstName = findViewById(R.id.firstName);
        edtsurname = findViewById(R.id.surname);
        edtage = findViewById(R.id.age);
        submit = findViewById(R.id.submit);
        count = findViewById(R.id.count);

        submit.setOnClickListener(v -> {
            NewStudentObj student = new NewStudentObj(edtfirstName.getText().toString(),
                    edtsurname.getText().toString(),
                    Integer.parseInt(edtage.getText().toString()));

            viewModel.createStudent(student);
        });

        viewModel.count.observe(this, aLong -> {
            count.setText("Records: "+ aLong);
        });

   }
}
