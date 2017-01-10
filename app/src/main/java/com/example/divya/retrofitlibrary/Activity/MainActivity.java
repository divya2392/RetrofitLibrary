package com.example.divya.retrofitlibrary.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.divya.retrofitlibrary.Common.Employee;
import com.example.divya.retrofitlibrary.Interface.EmployeeApi;
import com.example.divya.retrofitlibrary.R;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.divya.retrofitlibrary.Common.Constant.KEY_EMPLOYE_ID;
import static com.example.divya.retrofitlibrary.Common.Constant.KEY_EMPLOYE_NAME;
import static com.example.divya.retrofitlibrary.Common.Constant.SERVER_URL;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{


    //List view to show data
    private ListView listView;

    //List of type books this list will store type Book which is our data model
    private List<Employee> employes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewBooks);

        //Calling the method that will fetch data
        getEmployes();

        //Setting onItemClickListener to listview
        listView.setOnItemClickListener(this);

    }

    private void getEmployes(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(SERVER_URL)
                .build();

        //Creating an object of our api interface
        EmployeeApi api = adapter.create(EmployeeApi.class);

        //Defining the method
        api.getEmployes(new Callback<List<Employee>>() {
            @Override
            public void success(List<Employee> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
                employes = list;

                //Calling a method to show the list
                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    //Our method to show list
    private void showList(){
        //String array to store all the book names
        String[] items = new String[employes.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<employes.size(); i++){
            //Storing names to string array
            items[i] = employes.get(i).getName();
        }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);

        //Setting adapter to listview
        listView.setAdapter(adapter);
    }


    //This method will execute on listitem click
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, EmployeeDetails.class);

        //Getting the requested book from the list
        Employee employee = employes.get(position);

        //Adding book details to intent
        intent.putExtra(KEY_EMPLOYE_ID,employee.getEmployeId());
        intent.putExtra(KEY_EMPLOYE_NAME,employee.getName());
        //Starting another activity to show book details
        startActivity(intent);
        //Creating an intent
    }
}
