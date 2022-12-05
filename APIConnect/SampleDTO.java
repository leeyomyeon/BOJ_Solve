package SampleAPIConnect;

public class SampleDTO implements Comparable<SampleDTO> {
    int a;
    int b;
    public SampleDTO(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int compareTo(SampleDTO o) {
        return this.a - o.a;
    }
    // getter and setter
}