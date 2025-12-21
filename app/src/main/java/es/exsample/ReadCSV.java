package es.exsample;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV extends AppCompatActivity {
    public static List<LineDataList> conLineList;

    public static List<StarNameList> consNameList;

    public static List<StarDataList> starList;

    public static void parse(Context paramContext) {
        conLineList = new ArrayList<LineDataList>();
        starList = new ArrayList<StarDataList>();
        consNameList = new ArrayList<StarNameList>();
        try {
            InputStream inputStream = paramContext.getResources().getAssets().open("cons_lineData.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while (true) {
                LineDataList lineDataList;
                String str2 = bufferedReader.readLine();
                String str1 = ",";
                if (str2 != null) {
                    lineDataList = new LineDataList();
                    String[] arrayOfString = str2.split(",");
                    lineDataList.set_lunum(arrayOfString[0]);
                    lineDataList.set_linRas(Float.parseFloat(arrayOfString[1]));
                    lineDataList.set_linDcs(Float.parseFloat(arrayOfString[2]));
                    lineDataList.set_linV1s(Float.parseFloat(arrayOfString[3]));
                    lineDataList.set_linV2s(Float.parseFloat(arrayOfString[4]));
                    lineDataList.set_linRae(Float.parseFloat(arrayOfString[5]));
                    lineDataList.set_linDce(Float.parseFloat(arrayOfString[6]));
                    lineDataList.set_linV1e(Float.parseFloat(arrayOfString[7]));
                    lineDataList.set_linV2e(Float.parseFloat(arrayOfString[8]));
                    conLineList.add(lineDataList);
                    continue;
                }

                InputStream inputStream1 = paramContext.getResources().getAssets().open("starData1263.csv");
                InputStreamReader inputStreamReader1 = new InputStreamReader(inputStream1);
                BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);

                while (true) {
                    String str = bufferedReader1.readLine();
                    if (str != null) {
                        StarDataList starDataList = new StarDataList();
                        String[] arrayOfString = str.split(",");
                        starDataList.set_stnum(Integer.parseInt(arrayOfString[4]));
                        starDataList.set_stV1(Float.parseFloat(arrayOfString[1]));
                        starDataList.set_stV2(Float.parseFloat(arrayOfString[2]));
                        starDataList.set_stRA(Float.parseFloat(arrayOfString[6]));
                        starDataList.set_stDC(Float.parseFloat(arrayOfString[7]));
                        starDataList.set_stMG(Float.parseFloat(arrayOfString[5]));
                        starDataList.set_stCL(Float.parseFloat(arrayOfString[8]));
                        starList.add(starDataList);
                        continue;
                    }

                    InputStream inputStream2 = paramContext.getResources().getAssets().open("cons_nameData.csv");
                    InputStreamReader inputStreamReader2 = new InputStreamReader(inputStream2, "Shift-jis");
                    BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);

                    while (true) {
                        str = bufferedReader2.readLine();
                        if (str != null) {
                            StarNameList starNameList = new StarNameList();
                            String[] arrayOfString = str.split(",");
                            starNameList.set_Con_name(arrayOfString[0]);
                            starNameList.set_Con_Ra((float)((Integer.parseInt(arrayOfString[1]) * 15) + Integer.parseInt(arrayOfString[2]) * 0.25D));
                            starNameList.set_Con_Dc(Float.parseFloat(arrayOfString[3]));
                            consNameList.add(starNameList);
                            continue;
                        }
                        bufferedReader.close();
                        bufferedReader1.close();
                        bufferedReader2.close();
                        break;
                    }
                    break;
                }
                break;
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
    }
}