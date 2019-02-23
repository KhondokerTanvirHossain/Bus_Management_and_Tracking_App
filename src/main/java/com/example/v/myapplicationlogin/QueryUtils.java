package com.example.v.myapplicationlogin;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    static private URL url = null;
    static private JSONObject jsonresponse = null;
    private QueryUtils(){

    }
    static JSONObject makeHttpRequest(String urlString,String id, String name, String password, String email,String routeId, String seat,String bus){
        String s = null;
        if (id != null){
            id = encript(id);
        }
        if (name != null){
            name = encript(name);
        }
        if (password != null){
            password = encript(password);
        }
        if (email != null){
            email = encript(email);
        }
        try{
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (bus != null && seat != null ){
                jsonresponse = null;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(URLEncoder.encode("bus_id", "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(bus, "UTF-8"));
                stringBuilder.append("&");
                stringBuilder.append(URLEncoder.encode("seat", "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(seat, "UTF-8"));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                out.write(stringBuilder.toString());
                out.flush();
                out.close();
                Log.e("TA", "makeHttpRequest: SEATAAAAAAAAAA");

            }
            else if (bus != null){
                jsonresponse = null;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(URLEncoder.encode("bus", "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(bus, "UTF-8"));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                out.write(stringBuilder.toString());
                out.flush();
                out.close();
            }
            else if (!routeId.equals("ZERO") && seat != null ){
                jsonresponse = null;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(URLEncoder.encode("route_id", "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(routeId, "UTF-8"));
                stringBuilder.append("&");
                stringBuilder.append(URLEncoder.encode("seat", "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(seat, "UTF-8"));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                out.write(stringBuilder.toString());
                out.flush();
                out.close();

            }
            else if (!routeId.equals("ZERO")){
                jsonresponse = null;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(URLEncoder.encode("route_id", "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(routeId, "UTF-8"));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                out.write(stringBuilder.toString());
                out.flush();
                out.close();

            }
            else if (id != null || name != null || password != null || email != null) {
                ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new NameValuePair("student_id", id));
                list.add(new NameValuePair("username", name));
                list.add(new NameValuePair("password", password));
                list.add(new NameValuePair("email", email));

                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                out.write(getQuery(list));
                out.flush();
                out.close();
            }
            connection.connect();

            jsonresponse = new JSONObject(readJason(connection));



        }catch (MalformedURLException e){

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return jsonresponse;
        }

    }
    private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
    private static String readJason(HttpURLConnection connection) throws IOException{
        BufferedReader br;
        br = new BufferedReader((new InputStreamReader(connection.getInputStream())));
        StringBuilder builder = new StringBuilder("");
        String str = br.readLine();
        while (str != null && str.length() > 0){
            builder.append(str);
            str = br.readLine();
        }
        return new String(builder);
    }
    static String encript(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< s.length(); i++){
            String st = ""+ encript(s.charAt(i));
            sb.append(st);
        }
        return new String(sb);
    }
    static char encript(char c){
        int k = (int)c;
        if (k != '@')
            k += 4;
//        int d = 0;
//        if (k > 'Z' && k < 'z'){
//            d = k - 'Z';
//            c = (char)('A' + d);
//        }
//        else if (k > 'z'){
//            d = k - 'z';
//            c = (char)('a' + d);
//        }
//        else
        c = (char) k;
        return c;
    }
}
