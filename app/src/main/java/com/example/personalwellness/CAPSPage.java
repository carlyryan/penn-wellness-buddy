package com.example.personalwellness;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.URL;
import java.util.List;

public class CAPSPage extends AppCompatActivity {

    ResourceDB resourceDB = ResourceDB.getResourceDB();
    List<Resource> resources = resourceDB.getResourceList();
    Button[] button = new Button[resources.size()];
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_page);

        ConstraintLayout ll = (ConstraintLayout) findViewById(R.id.constraint_layout);

        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).getCategory().trim().equals("mh")) {
                button[index] = new Button(this);
                button[index].setId(i+1);
                button[index].setText(resources.get(i).getName());
                ll.addView(button[index],
                    new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT));
                index++;
            }
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(ll);

        for (int i = 0; i < index-1; i++) {
            if (button[i] != null) {
                constraintSet.connect(button[i].getId(), ConstraintSet.TOP, button[i+1].getId(),
                        ConstraintSet.BOTTOM);
            }
        }

        constraintSet.applyTo(ll);

        for (int i = 0; i < button.length; i++) {
            if (button[i] != null) {
                button[i] = (Button) findViewById(button[i].getId());
                final int b = button[i].getId();
                button[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CAPSPage.this,
                                GriefReferralPage.class);
                        intent.putExtra("buttonid", b + "");
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
