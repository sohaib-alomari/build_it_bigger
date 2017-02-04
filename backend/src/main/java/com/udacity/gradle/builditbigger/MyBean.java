package com.udacity.gradle.builditbigger;

import com.example.JokesLibrary;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;
    private JokesLibrary jk;

    public MyBean(){

        jk=new JokesLibrary();
    }

    public String getData() {
        return jk.getJoke();
    }

    public void setData(String data) {
        myData = data;
    }
}