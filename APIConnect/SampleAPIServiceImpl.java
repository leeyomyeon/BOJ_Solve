package SampleAPIConnect;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class SampleAPIServiceImpl implements SampleAPIService {
    public static final String CONTENT_TYPE = "application/json";
    public static final String X_AUTH_TOKEN = "2744bd7214625696f2675d0eaffc8284";
    public static String AUTH_KEY = null;
    public static final String URL = "URL";

    public void start(int param) throws Exception {
        HttpsURLConnection conn = getHttpsUrlConnection(URL, "POST");
        // 기타 파라미터 설정
        conn.setRequestProperty("X-Auth-Token", X_AUTH_TOKEN);
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        // body에 보낼 parameter 세팅
        JSONObject data = new JSONObject();

        data.put("param", param);
        wr.write(data.toString());
        wr.close();
        // connect 실행
        conn.connect();

        StringBuffer jsonString = getJsonString(conn);
        JSONObject object = (JSONObject) new JSONParser().parse(String.valueOf(jsonString));
        // JSON으로 받음
        AUTH_KEY = object.get("auth_key").toString();
        object.get("Something");

        System.out.println("AUTH_KEY : " + AUTH_KEY);
    }

    public ArrayList<HashMap<String, Integer>> sampleAPI1() throws Exception{
        HttpsURLConnection conn = getHttpsUrlConnection(URL, "GET");
        conn.connect();

        StringBuffer jsonString = getJsonString(conn);
        JSONObject object = (JSONObject) new JSONParser().parse(String.valueOf(jsonString));

        return (ArrayList<HashMap<String, Integer>>) object.get("sampleParam");
    }

    public long sampleAPI3(ArrayList<int[]> list) throws Exception {
        HttpsURLConnection conn = getHttpsUrlConnection(URL, "PUT");

        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

        // body에 보낼 parameter 세팅 --------------------
        JSONObject data = new JSONObject();
        JSONArray arrayList = new JSONArray();
        for(int[] pair : list) {
            JSONArray array = new JSONArray();
            array.add(pair[0]);
            array.add(pair[1]);
            arrayList.add(array);
        }
        // ----------------------------------------------
        data.put("arrayList", arrayList);
        wr.write(data.toString());
        wr.close();
        // connect 실행
        conn.connect();

        StringBuffer jsonString = getJsonString(conn);
        JSONObject object = (JSONObject) new JSONParser().parse(String.valueOf(jsonString));
        // JSON으로 받음

        return 0;
    }
    public void sampleAPI2(ArrayList<SampleDTO> list) throws Exception {
        HttpsURLConnection conn = getHttpsUrlConnection(URL, "PUT");

        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        // body에 보낼 parameter 세팅 ------------------
        JSONObject data = new JSONObject();
        JSONArray array = new JSONArray();
        for(SampleDTO dto : list) {
            JSONObject user = new JSONObject();
            user.put("a", dto.a);
            user.put("b", dto.b);
            array.add(user);
        }
        data.put("array", array);
        // -------------------------------------------
        wr.write(data.toString());
        wr.close();
        conn.connect();

        StringBuffer jsonString = getJsonString(conn);
        JSONObject object = (JSONObject) new JSONParser().parse(String.valueOf(jsonString));
    }

    // Http 연결 호출하는 부분
    public HttpsURLConnection getHttpsUrlConnection(String requestUrl, String requestMethod) throws Exception {
        HttpsURLConnection conn;
        URL url = new URL(requestUrl);
        conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        if(AUTH_KEY != null) {
            conn.setRequestProperty("Authorization", AUTH_KEY);
        }
        conn.setRequestProperty("Content-Type", CONTENT_TYPE);
        conn.setRequestProperty("Accept", CONTENT_TYPE);
        return conn;
    }
    // HttpConnection된 결과를 StringBuffer에 담는 함수
    public StringBuffer getJsonString(HttpsURLConnection connection) throws Exception {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        while(br.ready()) {
            sb.append(br.readLine());
        }
        return sb;
    }
}