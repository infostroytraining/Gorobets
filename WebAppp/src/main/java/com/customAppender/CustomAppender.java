package com.customAppender;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.logging.log4j.core.LogEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by invincible_g_d on 12/18/15.
 */
public class CustomAppender  extends AppenderSkeleton {

        List<String> eventsList = new ArrayList();


        public void close() {
        }

        public boolean requiresLayout() {
            return false;
        }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        eventsList.add(loggingEvent.getMessage().toString());

        try {
            sendPost(eventsList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        }



    private void sendPost(List<String> loggingEvent) throws Exception {

        final String USER_AGENT = "Mozilla/5.0";

        String url = "http://localhost:8080/logs";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        // add header
        httppost.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<>(2);
        urlParameters.add(new BasicNameValuePair("loggingEvent", loggingEvent.toString()));
        httppost.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);

        httppost.setEntity(new UrlEncodedFormEntity(urlParameters));


        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + httppost.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

    }
}

