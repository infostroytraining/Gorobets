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

import java.util.ArrayList;
import java.util.List;

/**
 * CustomAppender class - it's a own custom log4j appender that send a post
 * request to the other server application with logEvents
 */
public class CustomAppender extends AppenderSkeleton {

    List<String> eventsList = new ArrayList();


    /**
     * Append method - get loggingEvent and send it to the other server application
     *
     * @param loggingEvent -LoggingEvent type
     */
    @Override
    protected void append(LoggingEvent loggingEvent) {
        eventsList.add(loggingEvent.getMessage().toString());

        try {
            sendPost(eventsList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * SendPost method - get List<String> loggingEvent and  send it to the other server application
     *
     * @param loggingEvent -LoggingEvent type
     * @throws Exception
     */
    private void sendPost(List<String> loggingEvent) throws Exception {

        final String USER_AGENT = "Mozilla/5.0";

        String url = "http://localhost:8080/logs";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        // add header
        httppost.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<>(2);
        urlParameters.add(new BasicNameValuePair("LogValue", loggingEvent.toString()));
        httppost.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

        //Execute and get the response.

        HttpResponse response = httpclient.execute(httppost);


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

