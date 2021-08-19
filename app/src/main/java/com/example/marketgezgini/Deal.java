package com.example.marketgezgini;

public class Deal {
    private String B;
    private String C;
    private String D;
    private String E;

    public Deal(){

    }

    public Deal(String B, String C, String D, String E) {
        this.B = B;
        this.C = C;
        this.D = D;
        this.E = E;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }

    public String getE() {
        return E;
    }

    public void setE(String E) {
        this.E = E;
    }
}
