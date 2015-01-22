package com.m2dl.toulousependu.resourcehelpers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ilja on 22/01/2015.
 */
public class ToulouseDataDownloader {
    public static final String URI = "http://data.toulouse-metropole.fr/les-donnees/-/opendata/card/19151-prenoms-declares-a-l-etat-civil-de-toulouse-de-2003-a-2012/resource/document?p_p_state=exclusive&_5_WAR_opendataportlet_jspPage=%2Fsearch%2Fview_card.jsp";

    public static String downloadCsv() {
        InputStream input = null;
        OutputStream output = null;

        try {
            URL url = new URL(URI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Could not update CSV: server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }

            int fileLength = connection.getContentLength();

            input = connection.getInputStream();
            output = new FileOutputStream("/sdcard/prenomsopentoulouse.csv");

            int b;
            while ((b = input.read()) != -1) {
                output.write(b);
            }
        } catch (Exception e) {
            return "Could not update CSV: " + e;
        } finally {
            try {
                input.close();
                output.close();
            } catch (Exception e) { }
        }

        return "Success";
    }
}
