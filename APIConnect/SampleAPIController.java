package APIConnect;

import javax.net.ssl.HttpsURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class SampleAPIController {
    public static SampleAPIService sampleApiService = new SampleAPIServiceImpl();
    public void start(int a) throws Exception {
        sampleApiService.start(a);
    }
    public ArrayList<HashMap<String, Integer>> sampleAPI1() throws Exception {
        return sampleApiService.sampleAPI1();
    }
    public void sampleAPI2(ArrayList<SampleDTO> list) throws Exception {
        sampleApiService.sampleAPI2(list);
    }
    public long sampleAPI3(ArrayList<int[]> list) throws Exception {
        return sampleApiService.sampleAPI3(list);
    }
    public HttpsURLConnection getHttpsUrlConnection(String requestUrl, String requestMethod) throws Exception {
        return sampleApiService.getHttpsUrlConnection(requestUrl, requestMethod);
    }
    public StringBuffer getJsonString(HttpsURLConnection connection) throws Exception {
        return sampleApiService.getJsonString(connection);
    }
}