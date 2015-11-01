package com.andrewlord.snackbarbuildersample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andrewlord.snackbarbuilder.SnackbarBuilder;
import com.andrewlord.snackbarbuilder.SnackbarCallback;

public class SampleActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SnackbarBuilder(SampleActivity.this)
                        .message("Test snackbar really long message")
                        .duration(Snackbar.LENGTH_LONG)
                        .snackbarCallback(getCallback())
                        .actionText("UNDO")
                        .actionClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Stuff
                            }
                        })
                        .build()
                        .show();
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1));
    }

    private SnackbarCallback getCallback() {
        return new SnackbarCallback() {

            public void onSnackbarShown(Snackbar snackbar) {
                super.onSnackbarShown(snackbar);
            }

            public void onSnackbarActionPressed(Snackbar snackbar) {
                super.onSnackbarActionPressed(snackbar);
            }

            public void onSnackbarSwiped(Snackbar snackbar) {
                super.onSnackbarSwiped(snackbar);
            }

            public void onSnackbarTimedOut(Snackbar snackbar) {
                super.onSnackbarTimedOut(snackbar);
            }

            public void onSnackbarManuallyDismissed(Snackbar snackbar) {
                super.onSnackbarManuallyDismissed(snackbar);
            }

            public void onSnackbarDismissedAfterAnotherShown(Snackbar snackbar) {
                super.onSnackbarDismissedAfterAnotherShown(snackbar);
            }

        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
