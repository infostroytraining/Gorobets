package com.customAppender;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.*;

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.*;
import org.apache.logging.log4j.core.layout.PatternLayout;


// note: class name need not match the @Plugin name.

import java.io.Serializable;


// note: class name need not match the @Plugin name.
@Plugin(name = "MyCustomAppender", category = "Core", elementType = "appender", printObject = true)
public final class MyCustomAppenderImpl extends AbstractAppender {
    ArrayList<LogEvent> eventsList = new ArrayList();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();

    protected MyCustomAppenderImpl(String name, Filter filter,
                                   Layout<? extends Serializable> layout, final boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    // The append method is where the appender does the work.
    // Given a log event, you are free to do with it what you want.
    // This example demonstrates:
    // 1. Concurrency: this method may be called by multiple threads concurrently
    // 2. How to use layouts
    // 3. Error handling
    @Override
    public void append(LogEvent event) {

        readLock.lock();
        eventsList.add(event);
        try {
            final byte[] bytes = getLayout().toByteArray(event);
            System.out.write(bytes);
            System.out.println(event+"1");
            System.out.println(eventsList+"2");
            sendPost(eventsList);
        } catch (Exception ex) {
            if (!ignoreExceptions()) {
                throw new AppenderLoggingException(ex);
            }
        } finally {
            readLock.unlock();
        }
    }

    // Your custom appender needs to declare a factory method
    // annotated with `@PluginFactory`. Log4j will parse the configuration
    // and call this factory method to construct an appender instance with
    // the configured attributes.
    @PluginFactory
    public static MyCustomAppenderImpl createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter,
            @PluginAttribute("otherAttribute") String otherAttribute) {
        if (name == null) {
            LOGGER.error("No name provided for MyCustomAppenderImpl");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new MyCustomAppenderImpl(name, filter, layout, true);
    }

    public void sendPost(ArrayList<LogEvent> eventsList) {


        final String USER_AGENT = "Mozilla/5.0";

        String url = "http://localhost:8081/logs";

        HttpClient httpclient = HttpClientBuilder.create().build();


        HttpPost httppost = new HttpPost(url);
        String LogValue = eventsList.toString();
        // add header
        httppost.setHeader("User-Agent", USER_AGENT);

        List<BasicNameValuePair> urlParameters = new ArrayList<>(2);
        urlParameters.add(new BasicNameValuePair("LogValue", LogValue));
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


}
//    static {
//          final org.apache.logging.log4j.Logger logger =(org.apache.logging.log4j.core.Logger)LogManager.getRootLogger();
//        PatternLayout layout = PatternLayout.createLayout("%d{HH:mm:ss.SSS} [%t] %-5level %logger{1} - %msg%n%ex",
//                null, null,null, Charset.forName("GBK"), true, true, null,null);
//        MyCustomAppenderImpl myCustomAppender = createAppender( "MyCustomAppenderImpl",layout,null, "true");
//        myCustomAppender.start();
//        LogManager.getContext().getConfiguration().addLoggerAppender(logger, myCustomAppender);
//    }
//
//    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
//    private final Lock readLock = rwLock.readLock();
//    ArrayList<LogEvent> eventsList = new ArrayList();
//
//    protected MyCustomAppenderImpl(String name, Filter filter,
//                               Layout<? extends Serializable> layout, final boolean ignoreExceptions) {
//        super(name, filter, layout, ignoreExceptions);
//    }
//
//    // The append method is where the appender does the work.
//    // Given a log event, you are free to do with it what you want.
//    // This example demonstrates:
//    // 1. Concurrency: this method may be called by multiple threads concurrently
//    // 2. How to use layouts
//    // 3. Error handling
//    @Override
//    public void append(LogEvent event) {// надо реализовать так ,что бы писал или в файл, чтобу потом его обрабатывать или....
//        readLock.lock();
//        eventsList.add(event);
//        try {
//
//            final byte[] bytes = getLayout().toByteArray(event);
//            System.out.write(bytes);
//        } catch (Exception ex) {
//            if (!ignoreExceptions()) {
//                throw new AppenderLoggingException(ex);
//            }
//        } finally {
//            sendPost(eventsList);
//            readLock.unlock();
//        }
//    }
//
//    // Your custom appender needs to declare a factory method
//    // annotated with `@PluginFactory`. Log4j will parse the configuration
//    // and call this factory method to construct an appender instance with
//    // the configured attributes.
//    @PluginFactory
//    public static MyCustomAppenderImpl createAppender(
//            @PluginAttribute("name") String name,
//            @PluginElement("Layout") Layout<? extends Serializable> layout,
//            @PluginElement("Filter") final Filter filter,
//            @PluginAttribute("otherAttribute") String otherAttribute) {
//        if (name == null) {
//            LOGGER.error("No name provided for MyCustomAppenderImpl");
//            return null;
//        }
//        if (layout == null) {
//            layout = PatternLayout.createDefaultLayout();
//        }
//        return new MyCustomAppenderImpl(name, filter, layout, true);
//    }
//
//    private void sendPost(ArrayList<LogEvent> eventsList) {
//
////        final String USER_AGENT = "Mozilla/5.0";
//
//        String url = "http://localhost:8081/logs";
//
//        HttpClient httpclient = HttpClientBuilder.create().build();
//        HttpPost httppost = new HttpPost(url);
//
//        // add header
////        httppost.setHeader("User-Agent", USER_AGENT);
//
//        List<NameValuePair> urlParameters = new ArrayList<>(2);
//        urlParameters.add(new BasicNameValuePair("logEvent", eventsList.toString()));
//        try {
//            httppost.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
//
//
////Execute and get the response.
//            httppost.setEntity(new UrlEncodedFormEntity(urlParameters));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        HttpResponse response = null;
//        try {
//            response = httpclient.execute(httppost);
//
//
//            System.out.println("\nSending 'POST' request to URL : " + url);
//            System.out.println("Post parameters : " + httppost.getEntity());
//            System.out.println("Response Code : " +
//                    response.getStatusLine().getStatusCode());
//
//            BufferedReader rd = new BufferedReader(
//                    new InputStreamReader(response.getEntity().getContent()));
//
//            StringBuffer result = new StringBuffer();
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//            System.out.println(result.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//}

//
//import org.apache.logging.log4j.core.Filter;
//import org.apache.logging.log4j.core.Layout;
//import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
//import org.apache.logging.log4j.core.appender.ManagerFactory;
//import org.apache.logging.log4j.core.appender.OutputStreamManager;
//import org.apache.logging.log4j.core.config.plugins.Plugin;
//import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
//import org.apache.logging.log4j.core.config.plugins.PluginElement;
//import org.apache.logging.log4j.core.config.plugins.PluginFactory;
//import org.apache.logging.log4j.core.layout.PatternLayout;
//import org.apache.logging.log4j.core.util.Booleans;
//
//import java.io.ByteArrayOutputStream;
//import java.io.OutputStream;
//import java.io.Serializable;
//
//
//@Plugin(name = "MyCustomAppenderImpl", category = "Core", elementType = "appender", printObject = true)
//public final class MyCustomAppenderImpl extends AbstractOutputStreamAppender<OutputStreamManager> {
//    private static MemoryManagerFactory factory = new MemoryManagerFactory();
//
//    private MyCustomAppenderImpl(final String name, final Layout<? extends Serializable> layout, final Filter filter,
//                             final OutputStreamManager manager, final boolean ignoreExceptions) {
//        super(name, layout, filter, ignoreExceptions, true, manager);
//    }
//
//    @PluginFactory
//    public static MyCustomAppenderImpl createAppender(@PluginElement("Layout") Layout<? extends Serializable> layout,
//            @PluginElement("Filters") final Filter filter, @PluginAttribute("name") final String name,
//            @PluginAttribute(value = "ignoreExceptions", defaultBoolean = true) final String ignore) {
//        if (name == null) {
//            LOGGER.error("No name provided for MemoryAppender");
//            return null;
//        }
//        if (layout == null) {
//            layout = PatternLayout.createDefaultLayout();
//        }
//
//        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
//
//        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        return new MyCustomAppenderImpl(name, layout, filter, getManager(outputStream, name, layout), ignoreExceptions);
//    }
//
//    private static OutputStreamManager getManager(final ByteArrayOutputStream outputStream, final String name,
//            final Layout<? extends Serializable> layout) {
//        final OutputStream os = getOutputStream(outputStream);
//        return OutputStreamManager.getManager(name, new FactoryData(os, layout), factory);
//    }
//
//    private static OutputStream getOutputStream(final ByteArrayOutputStream outputStream) {
//        return new MemoryStream(outputStream);
//    }
//
//    private static class MemoryStream extends OutputStream {
//        private final ByteArrayOutputStream output;
//
//        public MemoryStream(ByteArrayOutputStream outputStream) {
//            this.output = outputStream;
//        }
//
//        @Override
//        public void write(final int b) {
//            output.write(b);
//        }
//    }
//
//    private static class FactoryData {
//        private final OutputStream os;
//        private final Layout<? extends Serializable> layout;
//
//        public FactoryData(final OutputStream os, final Layout<? extends Serializable> layout) {
//            this.os = os;
//            this.layout = layout;
//        }
//    }
//
//    private static class MemoryManagerFactory implements ManagerFactory<OutputStreamManager, FactoryData> {
//
//        @Override
//        public OutputStreamManager createManager(final String name, final FactoryData data) {
//            return new MemoryOutputStreamManager(data.os, name, data.layout);
//        }
//    }
//
//    private static class MemoryOutputStreamManager extends OutputStreamManager {
//        public MemoryOutputStreamManager(OutputStream os, String streamName, Layout<?> layout) {
//            super(os, streamName, layout,true);
//        }
//    }
//}

/* Реализация апенд метода конечная в jdk

  @Override
    public void append(final LogEvent event) {
        readLock.lock();
        try {
            final byte[] bytes = getLayout().toByteArray(event);
            if (bytes.length > 0) {
                manager.write(bytes);
                if (this.immediateFlush || event.isEndOfBatch()) {
                    manager.flush();
                }
            }
        } catch (final AppenderLoggingException ex) {
            error("Unable to write to stream " + manager.getName() + " for appender " + getName());
            throw ex;
        } finally {
            readLock.unlock();
        }
    }

*/