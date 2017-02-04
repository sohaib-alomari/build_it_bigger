package com.udacity.gradle.builditbigger.test;


import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class EndpointsAsyncTaskTest {

    String joker=null;

    public final CountDownLatch mSignal = new CountDownLatch(1);

    @Test
    public void tstJoke() throws Exception {

        new EndpointsAsyncTask(new TestJokeListener()).execute();
        try {
            boolean success = mSignal.await(10, TimeUnit.SECONDS);

            if (!success) {
                fail("null  Value or Server is down");
            }
        } catch (InterruptedException e) {

        }
    }


    private class TestJokeListener implements EndpointsAsyncTask.JokeReceivedListener {

        @Override
        public void onJokeRetrieved(String joke) {

    assertNotNull(joke);
            assertNotSame("",joke);
    mSignal.countDown();




        }
    }


}



