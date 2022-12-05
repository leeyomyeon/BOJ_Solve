package SampleAPIConnect;

public class Main {
    public static SampleAPIController sampleAPIController = new SampleAPIController();
    public static void main(String[] args) throws Exception {
        sampleAPIController.start(0);
        sampleAPIController.sampleAPI1();
        sampleAPIController.sampleAPI2(null);
        sampleAPIController.sampleAPI3(null);
        sampleAPIController.getHttpsUrlConnection("URL", "METHOD");
        sampleAPIController.getJsonString(null);
    }
}
