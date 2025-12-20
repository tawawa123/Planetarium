package es.exsample;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.core.app.ActivityCompat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class compass extends Activity implements SensorEventListener, LocationListener {
    private Data_Star DS;

    double[] Loc = new double[2];

    private final float[] accelerometerReading = new float[3];

    private Button button;

    private final float[] magnetometerReading = new float[3];

    private final float[] orientationAngles = new float[3];

    public Planetarium pla;

    private ReadCSV readCSV;

    private final float[] rotationMatrix = new float[9];

    private String[] selectStar;

    private SensorManager sensorManager;

    int[] time = new int[6];

    private Timer timer;

    public double[] JDT(double paramDouble) {
        int i = (int)(paramDouble + 0.5D);
        if (i >= 2299161) {
            int n = (int)((i - 1867216.25D) / 36524.25D);
            i = i + 1 + n - n / 4;
        }
        int k = i + 1524;
        int j = (int)((k - 122.1D) / 365.25D);
        int m = (int)(j * 365.25D);
        i = (int)((k - m) / 30.6001D);
        paramDouble = (k - m - (int)(i * 30.6001D)) + paramDouble + 0.5D - (int)(paramDouble + 0.5D);
        if (i < 13.5D) {
            i--;
        } else {
            i -= 13;
        }
        if (i > 2.5D) {
            k = j - 4716;
        } else {
            k = j - 4715;
        }
        j = i;
        m = k;
        if (i >= 13) {
            m = k + 1;
            j = i - 12;
        }
        i = m;
        if (m <= 0)
            i = m - 1;
        double d1 = (int)paramDouble;
        double d2 = (int)paramDouble;
        double d3 = (paramDouble - d1) * 24.0D + Math.round((float)(this.Loc[1] / 15.0D));
        d1 = d3;
        paramDouble = d2;
        if (d3 >= 24.0D) {
            d1 = d3 - 24.0D;
            paramDouble = d2 + 1.0D;
        }
        d2 = paramDouble;
        m = j;
        if (paramDouble >= 32.0D) {
            d2 = paramDouble - 31.0D;
            m = j + 1;
        }
        k = m;
        j = i;
        if (m >= 13) {
            j = i + 1;
            k = m - 12;
        }
        paramDouble = Math.round(d1 * 10.0D) / 10.0D;
        return new double[] { j, k, d2, paramDouble };
    }

    public void Julian() {
        boolean bool;
        String[] arrayOfString = new String[2];
        arrayOfString = getTimer();
        this.timer.set_Ydate(Integer.parseInt(arrayOfString[0]));
        this.timer.set_Dtime(Integer.parseInt(arrayOfString[1]));
        this.timer.set_LAT(this.Loc[0]);
        this.timer.set_loc(this.Loc[1]);
        int i = this.timer.get_Ydate();
        int j = this.timer.get_Dtime();
        this.timer.get_LAT();
        double d1 = this.timer.get_loc();
        if (i != Math.abs(i)) {
            bool = true;
            i = Math.abs(i);
        } else {
            bool = true;
        }
        double d2 = (i / 10000);
        double d3 = (int)(i - 10000.0D * d2);
        double d4 = (int)(d3 / 100.0D);
        double d5 = d3 - d4 * 100.0D;
        double d6 = (j / 100);
        double d7 = j;
        if (bool)
            d2 *= 1;
        double d8 = (d4 - 1.0D) / 12.0D;
        double d9 = d5 / 365.25D;
        d3 = d2;
        double d10 = d4;
        if (d4 <= 2.0D) {
            d10 = d4 + 12.0D;
            d3 = d2 - 1.0D;
        }
        d4 = d3;
        if (d4 < 0.0D) {
            d3 = Math.floor(365.25D * d4) + (int)((d10 - 2.0D) * 30.59D) + d5 - d1 / 360.0D + 1721086.5D;
        } else {
            d3 = ((int)(d4 * 365.25D) + (int)((d10 - 2.0D) * 30.59D)) + d5 - d1 / 360.0D + 1721086.5D;
        }
        d5 = d3;
        if (d2 + d8 + d9 > 1582.78D)
            d5 = d3 + (int)(d4 / 400.0D) - (int)(d4 / 100.0D) + 2.0D;
        d2 = d4;
        if (d10 > 12.0D)
            d2 = d4 + 1.0D;
        d3 = d5 + d6 / 24.0D + (d7 - d6 * 100.0D) / 1440.0D;
        d4 = koseiji(d3, d1);
        d10 = (d3 - 2415021.0D) / 36525.0D;
        this.timer.set_JD(d3);
        this.timer.set_ST(d4 * 15.0D);
        this.timer.set_Cent(d10);
        this.timer.set_YY(d2);
    }

    public double[] dispXY(double paramDouble1, double paramDouble2) {
        paramDouble1 = 480 * Math.sin(Math.toRadians((90.0D - paramDouble1) / 2.0D));
        return new double[] { Math.sin(Math.toRadians(paramDouble2)) * paramDouble1 + 1000.0D, Math.cos(Math.toRadians(paramDouble2)) * paramDouble1 + 1200.0D };
    }

    public final float[] getCompass() {
        SensorManager.getRotationMatrix(this.rotationMatrix, null, this.accelerometerReading, this.magnetometerReading);
        SensorManager.getOrientation(this.rotationMatrix, this.orientationAngles);
        return this.orientationAngles;
    }

    public String[] getTimer() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmm");
        return new String[] { simpleDateFormat1.format(date), simpleDateFormat2.format(date) };
    }

    public double[] horizon(double[] paramArrayOfdouble) {
        double d1 = Math.sin(Math.toRadians(this.timer.get_ST()));
        double d2 = Math.cos(Math.toRadians(this.timer.get_ST()));
        double d3 = Math.sin(Math.toRadians(this.timer.get_LAT()));
        double d4 = Math.cos(Math.toRadians(this.timer.get_LAT()));
        double d5 = d3 * d2 * paramArrayOfdouble[0] + d3 * d1 * paramArrayOfdouble[1] - paramArrayOfdouble[2] * d4;
        double d6 = -d1;
        double d7 = paramArrayOfdouble[0];
        double d8 = paramArrayOfdouble[1];
        double d9 = paramArrayOfdouble[0];
        double d10 = paramArrayOfdouble[1];
        double d11 = paramArrayOfdouble[2];
        double d12 = d5;
        if (d5 == 0.0D)
            d12 = 0.01D;
        d10 = Math.toDegrees(Math.asin(d4 * d2 * d9 + d4 * d1 * d10 + d11 * d3));
        d8 = Math.toDegrees(Math.atan(-(d6 * d7 + d8 * d2) / d12));
        d5 = d8;
        if (d12 < 0.0D)
            d5 = d8 + 180.0D;
        return new double[] { d10, d5 };
    }

    public double koseiji(double paramDouble1, double paramDouble2) {
        paramDouble1 -= 2415020.0D;
        paramDouble1 = paramDouble1 * 24.0D * 1.0027379092558308D + 18.6461D + 3.24E-14D * paramDouble1 * paramDouble1 + paramDouble2 / 15.0D;
        paramDouble2 = (paramDouble1 / 24.0D - (int)(paramDouble1 / 24.0D)) * 24.0D;
        paramDouble1 = paramDouble2;
        if (paramDouble2 < 0.0D)
            paramDouble1 = paramDouble2 + 24.0D;
        return paramDouble1;
    }

    public int magnitude(double paramDouble) {
        byte b;
        if (paramDouble < 0.0D) {
            b = 7;
        } else if (paramDouble < 1.0D) {
            b = 6;
        } else if (paramDouble < 2.0D) {
            b = 5;
        } else if (paramDouble < 3.0D) {
            b = 4;
        } else if (paramDouble < 4.0D) {
            b = 3;
        } else {
            b = 2;
        }
        return b;
    }

    public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        this.DS = new Data_Star();
        this.readCSV = new ReadCSV();
        this.timer = new Timer();
        this.pla = new Planetarium();
        this.selectStar = new String[2];
        this.sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        updateLocation();
        ReadCSV.parse(getApplicationContext());
        setContentView(R.layout.activity_main);
    }

    public void onLocationChanged(Location paramLocation) {
        this.Loc[0] = paramLocation.getLatitude();
        this.Loc[1] = paramLocation.getLongitude();
        Julian();
        star_culc();
        setContentView(R.layout.activity_main);
    }

    protected void onPause() {
        super.onPause();
        this.sensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        Sensor sensor = this.sensorManager.getDefaultSensor(1);
        if (sensor != null)
            this.sensorManager.registerListener(this, sensor, 3, 2);
        sensor = this.sensorManager.getDefaultSensor(2);
        if (sensor != null)
            this.sensorManager.registerListener(this, sensor, 3, 2);
    }

    public void onSensorChanged(SensorEvent paramSensorEvent) {
        float[] arrayOfFloat;
        if (paramSensorEvent.sensor.getType() == 1) {
            arrayOfFloat = paramSensorEvent.values;
            float[] arrayOfFloat1 = this.accelerometerReading;
            System.arraycopy(arrayOfFloat, 0, arrayOfFloat1, 0, arrayOfFloat1.length);
        } else if (paramSensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float[] values = paramSensorEvent.values;
            System.arraycopy(values, 0, magnetometerReading, 0, magnetometerReading.length);
        }
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        byte b;
        double[] arrayOfDouble1 = this.pla.get_xn();
        double[] arrayOfDouble2 = this.pla.get_yn();
        String[] arrayOfString = this.pla.get_coname();
        switch (paramMotionEvent.getAction()) {
            case 1:
                for (b = 0; b < this.pla.get_con_counter(); b++) {
                    if (arrayOfDouble1[b] >= (paramMotionEvent.getX() + 400.0F) && arrayOfDouble1[b] <= (paramMotionEvent.getX() + 520.0F) && arrayOfDouble2[b] >= (paramMotionEvent.getY() - 50.0F) && arrayOfDouble2[b] <= (paramMotionEvent.getY() - 10.0F)) {
                        System.out.println(arrayOfString[b]);
                        String[] arrayOfString1 = this.DS.Test(arrayOfString[b]);
                        System.out.println(arrayOfString1[0] + "\n" + arrayOfString1[1]);
                    }
                }
                break;
        }
        return true;
    }

    public double[] proper_move(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
        double d = this.timer.get_Cent() - 1.0D;
        return new double[] { paramDouble3 * d / 3600000.0D / Math.cos(Math.toRadians(paramDouble2)) + paramDouble1, paramDouble2 + paramDouble4 * d / 3600000.0D };
    }

    public double[] saisa_hosei(double[] paramArrayOfdouble) {
        double d1 = this.timer.get_Cent() - 1.0D;
        double d2 = d1 * 0.640616D + 8.39E-5D * d1 * d1 + Math.pow(d1, 3.0D) * 5.0E-6D;
        double d3 = 0.640616D * d1 + 3.04E-4D * d1 * d1 + Math.pow(d1, 3.0D) * 5.06E-6D;
        double d4 = 0.556753D * d1 - 1.19E-4D * d1 * d1 - Math.pow(d1, 3.0D) * 1.16E-5D;
        d1 = Math.sin(Math.toRadians(d2));
        d2 = Math.cos(Math.toRadians(d2));
        double d5 = Math.sin(Math.toRadians(d3));
        double d6 = Math.cos(Math.toRadians(d3));
        d3 = Math.sin(Math.toRadians(d4));
        d4 = Math.cos(Math.toRadians(d4));
        return new double[] { (-d5 * d1 + d6 * d4 * d2) * paramArrayOfdouble[0] + (-d5 * d2 - d6 * d4 * d1) * paramArrayOfdouble[1] - d6 * d3 * paramArrayOfdouble[2], (d6 * d1 + d5 * d4 * d2) * paramArrayOfdouble[0] + (d6 * d2 - d5 * d4 * d1) * paramArrayOfdouble[1] - d5 * d3 * paramArrayOfdouble[2], d3 * d2 * paramArrayOfdouble[0] - d3 * d1 * paramArrayOfdouble[1] + paramArrayOfdouble[2] * d4 };
    }

    public String star_color(double paramDouble) {
        String str;
        if (paramDouble < -0.16D) {
            str = "#a09eff";
        } else if (paramDouble < 0.15D) {
            str = "#a0d7ff";
        } else if (paramDouble < 0.45D) {
            str = "#d7e8ff";
        } else if (paramDouble < 0.68D) {
            str = "#ffffff";
        } else if (paramDouble < 1.15D) {
            str = "#ffffdc";
        } else if (paramDouble < 1.6D) {
            str = "#ffe6aa";
        } else {
            str = "#ffd7b1";
        }
        return str;
    }

    public void star_culc() {
        double[] arrayOfDouble1 = new double[1000];
        double[] arrayOfDouble2 = new double[1000];
        double[] arrayOfDouble3 = new double[1000];
        double[] arrayOfDouble4 = new double[1000];
        double[] arrayOfDouble5 = new double[1000];
        double[] arrayOfDouble6 = new double[1000];
        int[] arrayOfInt = new int[1000];
        double[] arrayOfDouble7 = new double[500];
        double[] arrayOfDouble8 = new double[500];
        double[] arrayOfDouble9 = new double[500];
        double[] arrayOfDouble10 = new double[500];
        double[] arrayOfDouble11 = new double[500];
        double[] arrayOfDouble12 = new double[500];
        double[] arrayOfDouble13 = new double[500];
        double[] arrayOfDouble14 = new double[500];
        double[] arrayOfDouble15 = new double[60];
        double[] arrayOfDouble16 = new double[60];
        double[] arrayOfDouble17 = new double[60];
        double[] arrayOfDouble18 = new double[60];
        String[] arrayOfString = new String[60];
        double[] arrayOfDouble19 = new double[2];
        arrayOfDouble19 = new double[2];
        for (LineDataList lineDataList : ReadCSV.conLineList) {
            double[] arrayOfDouble = proper_move(lineDataList.get_linRas(), lineDataList.get_linDcs(), lineDataList.get_linV1s(), lineDataList.get_linV2s());
            lineDataList.set_linRas((float)arrayOfDouble[0]);
            lineDataList.set_linDcs((float)arrayOfDouble[1]);
            arrayOfDouble = proper_move(lineDataList.get_linRae(), lineDataList.get_linDce(), lineDataList.get_linV1e(), lineDataList.get_linV2e());
            lineDataList.set_linRae((float)arrayOfDouble[0]);
            lineDataList.set_linDce((float)arrayOfDouble[1]);
        }
        arrayOfDouble19 = new double[3];
        double[] arrayOfDouble20 = new double[3];
        arrayOfDouble20 = new double[2];
        Iterator<LineDataList> iterator = ReadCSV.conLineList.iterator();
        byte b1;
        for (b1 = 0; iterator.hasNext(); b1++) {
            LineDataList lineDataList = iterator.next();
            arrayOfDouble19 = saisa_hosei(yogen_AD(lineDataList.get_linRas(), lineDataList.get_linDcs()));
            double[] arrayOfDouble22 = horizon(arrayOfDouble19);
            if (arrayOfDouble22[0] < 0.0D)
                continue;
            double[] arrayOfDouble21 = horizon(saisa_hosei(yogen_AD(lineDataList.get_linRae(), lineDataList.get_linDce())));
            if (arrayOfDouble21[0] < 0.0D)
                continue;
            arrayOfDouble7[b1] = arrayOfDouble22[0];
            arrayOfDouble9[b1] = arrayOfDouble22[1];
            arrayOfDouble8[b1] = arrayOfDouble21[0];
            arrayOfDouble10[b1] = arrayOfDouble21[1];
        }
        arrayOfDouble19 = new double[2];
        byte b2;
        for (b2 = 0; b2 <= b1; b2++) {
            arrayOfDouble19 = dispXY(arrayOfDouble7[b2], arrayOfDouble9[b2]);
            arrayOfDouble11[b2] = arrayOfDouble19[0];
            arrayOfDouble12[b2] = arrayOfDouble19[1];
            arrayOfDouble19 = dispXY(arrayOfDouble8[b2], arrayOfDouble10[b2]);
            arrayOfDouble13[b2] = arrayOfDouble19[0];
            arrayOfDouble14[b2] = arrayOfDouble19[1];
        }
        Iterator<StarDataList> line = ReadCSV.starList.iterator();
        arrayOfDouble7 = arrayOfDouble20;
        while (line.hasNext()) {
            StarDataList starDataList = line.next();
            arrayOfDouble8 = proper_move(starDataList.get_stRA(), starDataList.get_stDC(), starDataList.get_stV1(), starDataList.get_stV2());
            starDataList.set_stRA((float)arrayOfDouble8[0]);
            starDataList.set_stDC((float)arrayOfDouble8[1]);
        }
        b2 = 0;
        for (StarDataList starDataList : ReadCSV.starList) {
            arrayOfDouble8 = horizon(saisa_hosei(yogen_AD(starDataList.get_stRA(), starDataList.get_stDC())));
            if (arrayOfDouble8[0] < 0.0D)
                continue;
            arrayOfDouble3[b2] = arrayOfDouble8[0];
            arrayOfDouble4[b2] = arrayOfDouble8[1];
            arrayOfDouble5[b2] = starDataList.get_stMG();
            arrayOfDouble6[b2] = starDataList.get_stCL();
            b2++;
        }
        byte b3;
        for (b3 = 0; b3 < b2; b3++) {
            arrayOfDouble7 = dispXY(arrayOfDouble3[b3], arrayOfDouble4[b3]);
            arrayOfDouble1[b3] = arrayOfDouble7[0];
            arrayOfDouble2[b3] = arrayOfDouble7[1];
        }
        b3 = 0;
        for (StarNameList starNameList : ReadCSV.consNameList) {
            arrayOfDouble4 = horizon(saisa_hosei(yogen_AD(starNameList.get_Con_Ra(), starNameList.get_Con_Dc())));
            if (arrayOfDouble4[0] < 0.0D)
                continue;
            arrayOfDouble15[b3] = arrayOfDouble4[0];
            arrayOfDouble16[b3] = arrayOfDouble4[1];
            arrayOfString[b3] = starNameList.get_Con_name();
            b3++;
        }
        for (byte b4 = 0; b4 < b3; b4++) {
            arrayOfDouble4 = dispXY(arrayOfDouble15[b4], arrayOfDouble16[b4]);
            arrayOfDouble17[b4] = arrayOfDouble4[0];
            arrayOfDouble18[b4] = arrayOfDouble4[1];
        }
        this.pla.set_XX(arrayOfDouble1);
        this.pla.set_YY(arrayOfDouble2);
        this.pla.set_rd(arrayOfInt);
        this.pla.set_MG(arrayOfDouble5);
        this.pla.set_CLs(arrayOfDouble6);
        this.pla.set_x1(arrayOfDouble11);
        this.pla.set_x2(arrayOfDouble13);
        this.pla.set_y1(arrayOfDouble12);
        this.pla.set_y2(arrayOfDouble14);
        this.pla.set_xn(arrayOfDouble17);
        this.pla.set_yn(arrayOfDouble18);
        this.pla.set_coname(arrayOfString);
        this.pla.set_con_counter(b3);
        this.pla.set_line_counter(b1);
        this.pla.set_star_counter(b2);
    }

    public void updateLocation() {
        String str;
        if (ActivityCompat.checkSelfPermission((Context)getApplication(), "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission((Context)getApplication(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this, new String[] { "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION" }, 1);
            return;
        }
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled("gps")) {
            str = "gps";
        } else {
            str = null;
        }
        locationManager.requestLocationUpdates(str, 500L, 1.0F, this);
        locationManager.getLastKnownLocation(str);
    }

    public double[] yogen_AD(float paramFloat1, float paramFloat2) {
        double d1 = Math.toRadians(paramFloat1);
        double d2 = Math.toRadians(paramFloat2);
        return new double[] { Math.cos(d1) * Math.cos(d2), Math.cos(d2) * Math.sin(d1), Math.sin(d2) };
    }

    class ButtonClickListener implements View.OnClickListener {
        public void onClick(View param1View) {
            System.out.println("hello world");
        }
    }
}
