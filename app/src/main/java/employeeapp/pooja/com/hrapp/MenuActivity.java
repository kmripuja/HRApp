package employeeapp.pooja.com.hrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button addEmployee, viewfb, Dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        addEmployee = (Button) findViewById(R.id.addEmployee);

        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AddEmployee.class);
                startActivity(intent);
            }

        });

        viewfb = (Button) findViewById(R.id.viewfb);

        viewfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ViewFeedback.class);
                startActivity(intent);
            }

        });

        Dashboard = (Button) findViewById(R.id.Dashboard);

        Dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DashboardActivity.class);
                startActivity(intent);
            }

        });
    }
}
