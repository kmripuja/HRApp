package employeeapp.pooja.com.hrapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEmployee extends AppCompatActivity {

    EditText et_empname, et_empid;
    Button btn_addemp;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        et_empname = (EditText) findViewById(R.id.et_empname);
        et_empid = (EditText) findViewById(R.id.et_empid);
        btn_addemp = (Button) findViewById(R.id.btn_addemp);
        btn_addemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });
    }

    private void addStudent() {
        //getting the values to save
        String name = et_empname.getText().toString().trim();
        String empid = et_empid.getText().toString().trim();

        ref = FirebaseDatabase.getInstance().getReference();
//        System.out.println(villagename);
        //checking if the value is provided
        if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(empid))
        {
            HRclass emp = new HRclass(name, empid);
            String key = ref.child( "Employee/" ).push().getKey();
            ref.child("Employee/" + key ).setValue(emp);

            //setting edittext to blank again
            et_empname.setText("");
            et_empid.setText("");

            //displaying a success toast
            Toast.makeText(this, "Employee added", Toast.LENGTH_LONG).show();
        }

        else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name, empid", Toast.LENGTH_LONG).show();
        }

    }

}
