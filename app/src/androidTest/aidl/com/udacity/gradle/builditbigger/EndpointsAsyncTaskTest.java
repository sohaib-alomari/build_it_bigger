package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 Created by AlomariSF on 1/30/2017.
*/
public class EndpointsAsyncTaskTest extends AndroidTestCase {

    private final CountDownLatch mSignal = new CountDownLatch(1);

    public void testJokeRetriever() {
        new EndpointsAsyncTask(new TestJokeListener()).execute();
        try {
            boolean success = mSignal.await(5, TimeUnit.SECONDS);
            if (!success) {
                fail("Test timed out, make sure the server is actually running.");
            }
        } catch (InterruptedException e) {
            fail();
        }
    }

    private class TestJokeListener implements EndpointsAsyncTask.JokeReceivedListener {

        @Override
        public void onJokeRetrieved(String joke) {
            assertTrue(joke != null && joke.length() > 0);
            mSignal.countDown();
        }
    }
}