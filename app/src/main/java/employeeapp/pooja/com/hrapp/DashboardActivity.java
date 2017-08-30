package employeeapp.pooja.com.hrapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    DatabaseReference ref;
    LineGraphSeries<DataPoint> series;
    public static ArrayList<Integer> x;
    public static ArrayList<Integer> y;
    ArrayList<HRclass> emplist;
    ListView lv_emp;
    TextView abname, abvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        lv_emp = (ListView) findViewById(R.id.absentemp);
        emplist = new ArrayList<>();

        final int[] i = {0};
        final GraphView graph = (GraphView) findViewById(R.id.graph);

       x =new ArrayList<Integer>();
       y =new ArrayList<Integer>();
        ref = FirebaseDatabase.getInstance().getReference().child("Employee" );

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    HRclass studlist = snapshot.getValue(HRclass.class);


                    if (studlist.getRating() == 0) {

                    }
                    else
                    {
                           x.add(i[0]);  y.add(studlist.getRating());

                        System.out.println(i[0] + " : " + studlist.getRating());

                        i[0] += 1;
                    }
                }
                series= new LineGraphSeries<DataPoint>(data(x, y));

                graph.addSeries(series);
            }

                @Override
                public void onCancelled (DatabaseError databaseError){

                }

        });

        showlist();

    }
    public DataPoint[] data(ArrayList<Integer> x, ArrayList<Integer> y ){
        DataPoint[] values = new DataPoint[x.size()];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<x.size();i++){
            DataPoint v = new DataPoint(x.get(i),y.get(i));
            values[i] = v;
        }
        return values;
    }

    public void showlist()
    {

        ref = FirebaseDatabase.getInstance().getReference().child("Employee" );

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HRclass studlist = snapshot.getValue(HRclass.class);
                    if(studlist.getRating() == 0)
                        emplist.add(studlist);
                }

                //Init adapter
                AbsentEmpAdapter adapter = new AbsentEmpAdapter(DashboardActivity.this, R.layout.absentemp_content, emplist);

                //Set adapter for listview
                lv_emp.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public class AbsentEmpAdapter extends ArrayAdapter<HRclass> {
        private Activity context;
        private int resource;
        private List<HRclass> liststud;

        private LayoutInflater mInflater;

        public AbsentEmpAdapter(@NonNull FragmentActivity context, @LayoutRes int resource, @NonNull List<HRclass> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            liststud = objects;
        }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
        TextView tv_itemname = (TextView) v.findViewById(R.id.abname);
        TextView tv_itemfb = (TextView) v.findViewById(R.id.abvalue);


        tv_itemname.setText(liststud.get(position).getName());
        tv_itemfb.setText("NA");


        return v;
    }
}

}
