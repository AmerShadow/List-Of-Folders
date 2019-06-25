package com.central.listoffolders;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> s=new ArrayList<>();
    String path="/storage/emulated/0";
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("B","Before super");
        super.onCreate(savedInstanceState);
        Log.i("B","After Anything");
        setContentView(R.layout.activity_main);
        Log.i("B","Before Anything");


        Log.i("B","After Initialization");

        int status= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        Log.i("B","After getting Permission status:"+status+" "+PackageManager.PERMISSION_GRANTED);


        if (status== PackageManager.PERMISSION_GRANTED){
            listView=findViewById(R.id.folders);
            showFolders();
        }
        else {
            Log.i("","Inside else");
            Toast.makeText(getApplicationContext(),"Permission Not granted",Toast.LENGTH_LONG).show();
        }
    }
    public void showFolders(){

        file=new File(path);
        String[] arr=file.list();
        Log.i("","Inside if");
        for (int i=0;i<arr.length;i++){
            s.add(arr[i]);
        }

        ArrayAdapter list=new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,arr);
        listView.setAdapter(list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str= "/"+ listView.getItemAtPosition(position);
                path=path+str;
                Log.i("path: ",path);
                showFolders();
            }
        });
    }

}
