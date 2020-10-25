package com.example.pmsu_projekat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvFrom = (TextView)findViewById(R.id.tv_from);
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        TextView tvMessage = (TextView)findViewById(R.id.tv_message);
        TextView tvDate = (TextView)findViewById(R.id.tv_date);

        tvMessage.setMovementMethod(new ScrollingMovementMethod());

        tvFrom.setText(getIntent().getStringExtra("from"));
        tvTitle.setText(getIntent().getStringExtra("title"));
        tvMessage.setText(getIntent().getStringExtra("message"));
        tvDate.setText(getIntent().getStringExtra("date"));
    }

    /*android:resource="@xml/activity_email_items"*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_email_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item){

        int id = item.getItemId();

        if(id == R.id.delete){
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.reply) {
            Toast.makeText(this, "Reply", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.forward){
            Toast.makeText(this, "Forward", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.replytoall){
            Toast.makeText(this, "Reply to all", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.save_attachment){
            Toast.makeText(this, "Save attachment", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
