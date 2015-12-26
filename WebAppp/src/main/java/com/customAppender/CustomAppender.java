package com.customAppender;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomAppender class - it's a own custom log4j appender that send a post
 * request to the other server application with logEvents
 */
public class CustomAppender extends AppenderSkeleton {

    ArrayList<LoggingEvent> eventsList = new ArrayList();


    /**
     * Append method - get loggingEvent and send it to the other server application
     *
     * @param logEvent -LoggingEvent type
     */
    @Override
    protected void append(LoggingEvent logEvent) {
        eventsList.add(logEvent);


    }

    /**
     * SendPost method - get List<String> loggingEvent and  send it to the other server application
     *
     *
     */
    public void sendPost(CustomAppender customAppender)  {



        ArrayList<LoggingEvent> loggingEventList = customAppender.eventsList;

//        final String USER_AGENT = "Mozilla/5.0";

        String url = "http://localhost:8080/logs";

        HttpClient httpclient  = HttpClientBuilder.create().build();


        HttpPost httppost = new HttpPost(url);

        // add header
//        httppost.setHeader("User-Agent", USER_AGENT);

        List<BasicNameValuePair> urlParameters = new ArrayList<>(2);
        urlParameters.add(new BasicNameValuePair("LogValue", loggingEventList.toString()));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Execute and get the response.

        try {
            HttpResponse response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     *
     */
    @Override
    public void close() {

    }

    /**
     * @return
     */
    @Override
    public boolean requiresLayout() {
        return false;
    }
}

