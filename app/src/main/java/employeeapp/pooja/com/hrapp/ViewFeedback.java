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

import java.util.ArrayList;
import java.util.List;

public class ViewFeedback extends AppCompatActivity {

    ArrayList<HRclass> emplist;
    ListView lv_stud;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        ref = FirebaseDatabase.getInstance().getReference();
        lv_stud = (ListView) findViewById(R.id.lv_stud);
        emplist = new ArrayList<HRclass>();
        showlist();
    }

    public void showlist()
    {

        ref = FirebaseDatabase.getInstance().getReference().child("Employee" );
        emplist.clear();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HRclass studlist = snapshot.getValue(HRclass.class);
                    if( (studlist.getfeedback() != null) && (studlist.getfeedback() != ""))
                      emplist.add(studlist);
                }

                //Init adapter
                HRt_listAdapter adapter = new HRt_listAdapter(ViewFeedback.this, R.layout.fblistcontent, emplist);

                //Set adapter for listview
                lv_stud.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public class HRt_listAdapter extends ArrayAdapter<HRclass> {
        private Activity context;
        private int resource;
        private List<HRclass> liststud;

        private LayoutInflater mInflater;

        public HRt_listAdapter(@NonNull FragmentActivity context, @LayoutRes int resource, @NonNull List<HRclass> objects) {
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
            TextView tv_itemname = (TextView) v.findViewById(R.id.tv_itemname);
            TextView tv_itemfb = (TextView) v.findViewById(R.id.tv_itemfb);


                tv_itemname.setText(liststud.get(position).getName());
                tv_itemfb.setText(liststud.get(position).getfeedback());


            return v;
        }
    }


}
