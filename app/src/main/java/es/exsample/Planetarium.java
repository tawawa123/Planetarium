package es.exsample;

public class Planetarium {
    static double[] AA;

    static double[] ALe;

    static double[] ALs;

    static double[] CLs;

    static double[] MG;

    static double[] XX = new double[1000];

    static double[] YY = new double[1000];

    static int con_counter;

    static String[] coname;

    static double[] hLe;

    static double[] hLs;

    static double[] hh = new double[1000];

    static int line_counter;

    static double[] nA;

    static double[] nh;

    static int[] rd;

    static int star_counter;

    static double[] x1;

    static double[] x2;

    static double[] xn;

    static double[] y1;

    static double[] y2;

    static double[] yn;

    static {
        AA = new double[1000];
        MG = new double[1000];
        CLs = new double[1000];
        rd = new int[1000];
        hLs = new double[500];
        hLe = new double[500];
        ALs = new double[500];
        ALe = new double[500];
        x1 = new double[1000];
        y1 = new double[1000];
        x2 = new double[1000];
        y2 = new double[1000];
        nh = new double[60];
        nA = new double[60];
        xn = new double[60];
        yn = new double[60];
        coname = new String[60];
        star_counter = 0;
        line_counter = 0;
        con_counter = 0;
    }

    public double[] get_AA() {
        return AA;
    }

    public double[] get_ALe() {
        return ALe;
    }

    public double[] get_ALs() {
        return ALs;
    }

    public double[] get_CLs() {
        return CLs;
    }

    public double[] get_MG() {
        return MG;
    }

    public double[] get_XX() {
        return XX;
    }

    public double[] get_YY() {
        return YY;
    }

    public int get_con_counter() {
        return con_counter;
    }

    public String[] get_coname() {
        return coname;
    }

    public double[] get_hLe() {
        return hLe;
    }

    public double[] get_hLs() {
        return hLs;
    }

    public double[] get_hh() {
        return hh;
    }

    public int get_line_counter() {
        return line_counter;
    }

    public double[] get_nA() {
        return nA;
    }

    public double[] get_nh() {
        return nh;
    }

    public int[] get_rd() {
        return rd;
    }

    public int get_star_counter() {
        return star_counter;
    }

    public double[] get_x1() {
        return x1;
    }

    public double[] get_x2() {
        return x2;
    }

    public double[] get_xn() {
        return xn;
    }

    public double[] get_y1() {
        return y1;
    }

    public double[] get_y2() {
        return y2;
    }

    public double[] get_yn() {
        return yn;
    }

    public void set_AA(double[] paramArrayOfdouble) {
        AA = paramArrayOfdouble;
    }

    public void set_ALe(double[] paramArrayOfdouble) {
        ALe = paramArrayOfdouble;
    }

    public void set_ALs(double[] paramArrayOfdouble) {
        ALs = paramArrayOfdouble;
    }

    public void set_CLs(double[] paramArrayOfdouble) {
        CLs = paramArrayOfdouble;
    }

    public void set_MG(double[] paramArrayOfdouble) {
        MG = paramArrayOfdouble;
    }

    public void set_XX(double[] paramArrayOfdouble) {
        XX = paramArrayOfdouble;
    }

    public void set_YY(double[] paramArrayOfdouble) {
        YY = paramArrayOfdouble;
    }

    public void set_con_counter(int paramInt) {
        con_counter = paramInt;
    }

    public void set_coname(String[] paramArrayOfString) {
        coname = paramArrayOfString;
    }

    public void set_hLe(double[] paramArrayOfdouble) {
        hLe = paramArrayOfdouble;
    }

    public void set_hLs(double[] paramArrayOfdouble) {
        hLs = paramArrayOfdouble;
    }

    public void set_hh(double[] paramArrayOfdouble) {
        hh = paramArrayOfdouble;
    }

    public void set_line_counter(int paramInt) {
        line_counter = paramInt;
    }

    public void set_nA(double[] paramArrayOfdouble) {
        nA = paramArrayOfdouble;
    }

    public void set_nh(double[] paramArrayOfdouble) {
        nh = paramArrayOfdouble;
    }

    public void set_rd(int[] paramArrayOfint) {
        rd = paramArrayOfint;
    }

    public void set_star_counter(int paramInt) {
        star_counter = paramInt;
    }

    public void set_x1(double[] paramArrayOfdouble) {
        x1 = paramArrayOfdouble;
    }

    public void set_x2(double[] paramArrayOfdouble) {
        x2 = paramArrayOfdouble;
    }

    public void set_xn(double[] paramArrayOfdouble) {
        xn = paramArrayOfdouble;
    }

    public void set_y1(double[] paramArrayOfdouble) {
        y1 = paramArrayOfdouble;
    }

    public void set_y2(double[] paramArrayOfdouble) {
        y2 = paramArrayOfdouble;
    }

    public void set_yn(double[] paramArrayOfdouble) {
        yn = paramArrayOfdouble;
    }
}
