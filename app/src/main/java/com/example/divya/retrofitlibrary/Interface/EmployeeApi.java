package com.example.divya.retrofitlibrary.Interface;

import com.example.divya.retrofitlibrary.Common.Employee;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by divya on 09/01/17.
 */

public interface EmployeeApi {

    //retrofit get annotatn wid our url
    //return list of employee

    @GET("/employee.json")
    public void getEmployes(Callback<List<Employee>> response);
}
