package com.strontech.imgautam.setdatahttpclientapp1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

/**
 * Created by imgautam on 25/3/18.
 */

public class JSONParser {

  static InputStream inputStream;
  static JSONObject object;
  static String son;

  public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {

    try {

      if (method == "POST") {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        inputStream = httpEntity.getContent();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {

      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"),
          8);

      StringBuilder builder = new StringBuilder();
      String line = null;

      while ((line = reader.readLine()) != null) {
        builder.append(line + "\n");
      }

      inputStream.close();
      son = builder.toString();

    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      object = new JSONObject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return object;
  }
}
