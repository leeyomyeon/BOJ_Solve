package SampleAPIConnect;

import javax.net.ssl.HttpsURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public interface SampleAPIService {
    void start(int problem) throws Exception;
    ArrayList<HashMap<String, Integer>> sampleAPI1() throws Exception;
    void sampleAPI2(ArrayList<SampleDTO> list) throws Exception;
    long sampleAPI3(ArrayList<int[]> list) throws Exception;
    HttpsURLConnection getHttpsUrlConnection(String requestUrl, String requestMethod) throws Exception;
    StringBuffer getJsonString(HttpsURLConnection connection) throws Exception;
}
