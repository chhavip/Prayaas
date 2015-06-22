package helper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.OtpContent;

/**
 * Created by shikharkhetan on 6/19/15.
 */
public class OtpSenderAsyncTask extends AsyncTask<OtpContent, Void, Boolean> {

    Context context;
    OtpContent otpContent;
    String token;
    String identity;
    public OtpSenderAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(OtpContent... params) {
        otpContent = params[0];
        Log.i("doIn", otpContent.message);

        token = "r9emUeS9t9wCKeUHM77j";
        identity = "Demo";
        URL url = null;
        try {
            url = new URL("http://api.sparrowsms.com/v2/sms/");
        } catch (MalformedURLException e) {
            return false;
        }

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            return false;
        }

        if (urlConnection == null) {
            return false;
        }

        try {
            urlConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            return false;
        }

//        urlConnection.setRequestProperty("Content-Type", "application/json");
//        JSONObject otp = new JSONObject();

        try {
//            otp.put("token", token);
//            otp.put("from", identity);
//            otp.put("to", otpContent.phone);
//            otp.put("text", otpContent.message);

            List<NameValuePair> param = new ArrayList<NameValuePair>();
            param.add(new BasicNameValuePair("token", token));
            param.add(new BasicNameValuePair("from", identity));
            param.add(new BasicNameValuePair("to", otpContent.phone));
            param.add(new BasicNameValuePair("text",otpContent.message));

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(param));
            writer.flush();
            writer.close();
            os.close();

//            conn.connect();
//        } catch (JSONException e) {
//            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            urlConnection.connect();
//            OutputStream os = urlConnection.getOutputStream();
//            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//
//            osw.write(otp.toString());
//            osw.flush();
//            osw.close();

        } catch (IOException e) {
            return false;
        }

        InputStream inputStream = null;
        try {
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            inputStream = urlConnection.getErrorStream();
        }

        if (inputStream == null) {
//            Toast.makeText(context,"input stream null", Toast.LENGTH_SHORT).show();
            return false;
        }

        StringBuffer buffer = new StringBuffer();
        String temp =   "";
        Scanner s = new Scanner(inputStream);
        while (s.hasNext()) {
            buffer.append(s.nextLine());
            // temp = temp + s.nextLine();
        }

        Log.i("jsondata", buffer.toString());

        return true;
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
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
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean)    {
Log.i("Executed" , " ");
        }
    }
}
