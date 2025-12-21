package es.exsample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ExSample extends Activity implements SensorEventListener, LocationListener {
    private Data_Star DS;

    double[] Loc = new double[2];

    private final float[] accelerometerReading = new float[3];

    private final float[] magnetometerReading = new float[3];

    private final float[] orientationAngles = new float[3];

    private Planetarium pla;

    private ReadCSV readCSV;

    private final float[] rotationMatrix = new float[9];

    private String[] selectStar;

    private SensorManager sensorManager;

    int[] time = new int[6];

    private Timer timer;

    public String[] DataBase_Star(String paramString) {
        String[] arrayOfString1 = new String[2];
        SQLiteDatabase sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase("data/data/" + getPackageName() + "/Sample.db", null);
        String[] arrayOfString2 = new String[88];
        arrayOfString2[0] = "INSERT INTO site(name, info) VALUES ('アンドロメダ座', 'ギリシャ神話の古代エチオピアの王女アンドロメダ姫の姿をした秋の星座。');";
        arrayOfString2[1] = "INSERT INTO site(name, info) VALUES ('いっかくじゅう座', '一角獣ユニコーンの姿をした星座。ユニコーンとは、額に角がある馬の姿で、捕まえると幸運が訪れると言われていた伝説上の生き物。');";
        arrayOfString2[2] = "INSERT INTO site(name, info) VALUES ('いて座', 'ギリシャ神話に登場する半人半馬のケンタウルス族ケイローンの姿をした星座。黄道12星座のひとつ。')";
        arrayOfString2[3] = "INSERT INTO site(name, info) VALUES ('いるか座', 'イルカの姿をした小さな星座。日本ではひし形が並んでいることから「ひし座」とも呼ばれていた。')";
        arrayOfString2[4] = "INSERT INTO site(name, info) VALUES ('インディアン座', 'インディアンが矢をもって立つ姿を現した星座。アメリカ先住民族インディアンの名にちなんでいる。')";
        arrayOfString2[5] = "INSERT INTO site(name, info) VALUES ('うお座', 'ひもで結ばれた2匹の魚の姿をした星座。黄道12星座のひとつ。')";
        arrayOfString2[6] = "INSERT INTO site(name, info) VALUES ('うさぎ座', 'うさぎ座はオリオンの足元を逃げ回るうさぎの姿をした小さな冬の星座。')";
        arrayOfString2[7] = "INSERT INTO site(name, info) VALUES ('牛飼い座', '牛飼い座は猟犬を従える巨人の姿をした星座。春の大三角を作るアークトゥルスが含まれている。')";
        arrayOfString2[8] = "INSERT INTO site(name, info) VALUES ('うみへび座', 'うみへび座は、夜空をつのった大きなうみへびを表した星座で、全天で一番大きな星座。')";
        arrayOfString2[9] = "INSERT INTO site(name, info) VALUES ('エリダヌス座', '川の名前が付けられた星座で、オリオン座の足元から流れ出している。')";
        arrayOfString2[10] = "INSERT INTO site(name, info) VALUES ('おうし座', 'おうし座は、狩人オリオンに襲い掛かる牡牛の姿をした星座。黄道12星座のひとつ。')";
        arrayOfString2[11] = "INSERT INTO site(name, info) VALUES ('おおいぬ座', 'おおいぬ座は、猟犬の姿を現した星座。おおいぬ座の口元にあるシリウスは、地球から見える恒星のうち最も明るい星。')";
        arrayOfString2[12] = "INSERT INTO site(name, info) VALUES ('おおかみ座', 'おおかみ座はケンタウルスにやりで突かれる狼の姿をした星座。')";
        arrayOfString2[13] = "INSERT INTO site(name, info) VALUES ('おおぐま座', '大きなクマの姿を表した春の星座。ひしゃくの姿をした北斗七星はおおぐま座のしっぽの部分。')";
        arrayOfString2[14] = "INSERT INTO site(name, info) VALUES ('おとめ座', 'おとめ座は、手に麦の穂を持つ女神の姿を表した星座。黄道12星座のひとつ。')";
        arrayOfString2[15] = "INSERT INTO site(name, info) VALUES ('おひつじ座', 'おひつじ座は、平原にたたずむ羊の姿をした秋の星座。黄道12星座のひとつ。')";
        arrayOfString2[16] = "INSERT INTO site(name, info) VALUES ('オリオン座', 'オリオン座は、ギリシャ神話の狩人オリーオーンの姿を表した星座。')";
        arrayOfString2[17] = "INSERT INTO site(name, info) VALUES ('がか座', 'がか座は、絵を立てかける画架の形をした星座。画架は絵を描くときにキャンバスを立てかける三脚のような道具のこと。')";
        arrayOfString2[18] = "INSERT INTO site(name, info) VALUES ('カシオペア座', 'カシオペア座は、古代エチオピア王妃カシオペアの姿をした秋の星座。地域によっては、形が錨に見えることから錨座とも呼ばれる。')";
        arrayOfString2[19] = "INSERT INTO site(name, info) VALUES ('かじき座', 'かじき座は、鋭く長い吻を持つ魚「かじき」の姿をした星座。もともとは「シイラ座」、または「金魚座」だったものが、いつの間にか「かじき座」と呼ばれるようになった。')";
        arrayOfString2[20] = "INSERT INTO site(name, info) VALUES ('かに座', 'かに座は、その名の通りかにの姿をした星座。黄道12星座のひとつ。')";
        arrayOfString2[21] = "INSERT INTO site(name, info) VALUES ('かみのけ座', 'かみのけ座は、星団をかみの毛の束に見立てた星座で、明るい星を結んで作らない珍しい星座。')";
        arrayOfString2[22] = "INSERT INTO site(name, info) VALUES ('カメレオン座', 'カメレオン座は、爬虫類のカメレオンの姿をした星座。カメレオン座の口元には、餌となるハエ座もいる。')";
        arrayOfString2[23] = "INSERT INTO site(name, info) VALUES ('からす座', 'からす座は、うみへび座の背中に乗ったカラスの姿をした星座。昔の日本では「帆かけ座」という名前で呼ばれていたそう。')";
        arrayOfString2[24] = "INSERT INTO site(name, info) VALUES ('かんむり座', 'かんむり座は、冠の形を表した星座。うしかい座とヘルクレス座に挟まれている。')";
        arrayOfString2[25] = "INSERT INTO site(name, info) VALUES ('きょしちょう座', 'きょしちょう座は、南アメリカに生息するくちばしの大きな鳥、巨嘴鳥の姿をした星座。')";
        arrayOfString2[26] = "INSERT INTO site(name, info) VALUES ('ぎょしゃ座', 'ぎょしゃ座は、馬車を操る王の姿を表した星座。将棋の駒のような五角形の姿が特徴。')";
        arrayOfString2[27] = "INSERT INTO site(name, info) VALUES ('きりん座', 'きりん座は、北の空に浮かぶきりんの姿をした星座。北極星近くにある大きな星座で、日本では一年を通して見ることができる。')";
        arrayOfString2[28] = "INSERT INTO site(name, info) VALUES ('くじゃく座', 'くじゃく座は、大きな尾羽をもつクジャクの姿をした星座。')";
        arrayOfString2[29] = "INSERT INTO site(name, info) VALUES ('くじら座', 'くじら座は、海の生物のくじらではなく、腕が生えた怪物クジラの姿をした星座。')";
        arrayOfString2[30] = "INSERT INTO site(name, info) VALUES ('ケフェウス座', 'ケフェウス座は、古代エチオピア王ケフェウスの姿を表した星座。')";
        arrayOfString2[31] = "INSERT INTO site(name, info) VALUES ('ケンタウルス座', 'ケンタウルス座は、上半身が人間で、下半身が馬というケンタウルスの姿をした星座。日本では上半身しか見ることができない。')";
        arrayOfString2[32] = "INSERT INTO site(name, info) VALUES ('けんびきょう座', 'けんびきょう座は、科学実験などに使われる顕微鏡の形をした星座で、フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[33] = "INSERT INTO site(name, info) VALUES ('こいぬ座', 'こいぬ座は、明るい2つの星だけでこいぬの姿を表す小さな星座。天の川を挟んでおおいぬ座と反対側にある。')";
        arrayOfString2[34] = "INSERT INTO site(name, info) VALUES ('こうま座', 'こうま座は、ペガスス座の鼻の先にある、小さな馬の頭の形をした星座。')";
        arrayOfString2[35] = "INSERT INTO site(name, info) VALUES ('こぎつね座', '子ぎつね座子ぎつね座は、ガチョウを加えた狐の姿をした星座。夏の大三角の真ん中にある。')";
        arrayOfString2[36] = "INSERT INTO site(name, info) VALUES ('こぐま座', 'こぐま座は、おおぐま座と親子に当たる小熊の姿をした春の星座。しっぽの先の星は北極星。')";
        arrayOfString2[37] = "INSERT INTO site(name, info) VALUES ('こじし座', 'こじし座は、小さなライオンの姿をした星座。しし座の頭の近くにある。')";
        arrayOfString2[38] = "INSERT INTO site(name, info) VALUES ('コップ座', 'コップ座は、古代ギリシャの盃の姿を表した星座。うみへび座の背中に乗るような位置にある。')";
        arrayOfString2[39] = "INSERT INTO site(name, info) VALUES ('こと座', 'こと座は、楽器の竪琴の形をした星座。七夕の「おりひめ星」として知られるベガはこと座の1等星。')";
        arrayOfString2[40] = "INSERT INTO site(name, info) VALUES ('コンパス座', 'コンパス座は、測量用コンパスの形をした星座。フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[41] = "INSERT INTO site(name, info) VALUES ('さいだん座', 'さいだん座は、古代ギリシャの祭壇の形を表した星座。日本からは星座がさかさまに見えるため、炎の部分が下になって見える。')";
        arrayOfString2[42] = "INSERT INTO site(name, info) VALUES ('さそり座', 'さそり座は、尾の先に毒針を持つサソリの姿をした星座。黄道12星座のひとつ。')";
        arrayOfString2[43] = "INSERT INTO site(name, info) VALUES ('さんかく座', 'さんかく座は、小さな三角形の形をした星座。その名の通り、3個の小さな星が三角形に並んでいる。')";
        arrayOfString2[44] = "INSERT INTO site(name, info) VALUES ('しし座', 'しし座は、ギリシャ神話の人食いライオンの姿をした星座。黄道12星座のひとつ。')";
        arrayOfString2[45] = "INSERT INTO site(name, info) VALUES ('じょうぎ座', 'じょうぎ座は、船乗りが使った直角定規と直定規を重ねた形をしており、フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[46] = "INSERT INTO site(name, info) VALUES ('たて座', 'たて座は、十字架の描かれたたての形をした星座。ポーランド国王ヤン3世ソビエスキーをたたえて作られた、実在の人物に由来する珍しい星座。')";
        arrayOfString2[47] = "INSERT INTO site(name, info) VALUES ('ちょうこくぐ座', 'ちょうこくぐ座は、芸術家が彫刻をする際に使用する道具の形をした星座。フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[48] = "INSERT INTO site(name, info) VALUES ('ちょうこくしつ座', 'ちょうこくしつ座は、彫刻家のアトリエをイメージして作られた星座。フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[49] = "INSERT INTO site(name, info) VALUES ('つる座', 'つる座は、翼を広げた鶴が、長い首を伸ばしている様子を表した星座。もともとはフラミンゴであるとも言われている。')";
        arrayOfString2[50] = "INSERT INTO site(name, info) VALUES ('テーブル山座', 'テーブル山座は、テーブルのように上部が平らになった岩山の形をした星座。南アフリカ・ケープタウンのテーブルマウンテンという実在の山を描いたもの。')";
        arrayOfString2[51] = "INSERT INTO site(name, info) VALUES ('てんびん座', 'てんびん座は、重さをはかる天秤の形を表した星座。黄道12星座のひとつ。')";
        arrayOfString2[52] = "INSERT INTO site(name, info) VALUES ('とかげ座', 'しっぽを丸めたトカゲの姿をした星座。ペガスス座の足元、ケフェウス座の頭上に位置している。')";
        arrayOfString2[53] = "INSERT INTO site(name, info) VALUES ('とけい座', 'オランダの科学者ホイヘンスが発明した振り子時計の姿を表した星座。フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[54] = "INSERT INTO site(name, info) VALUES ('とびうお座', 'アルゴ船の近くで羽の生えたトビウオが海面を飛ぶ姿を表した星座。昔、星を観測していた航海者たちがトビウオの大群を目にし、星座にしたと言われている。')";
        arrayOfString2[55] = "INSERT INTO site(name, info) VALUES ('とも座', '大きな帆船の船尾の形をした星座。フランスの天文学者ラカイユが設定した科学道具シリーズであり、ギリシャ神話に登場する大帆船アルゴー号に由来するアルゴー座が4つに分割されてできた星座のひとつ。')";
        arrayOfString2[56] = "INSERT INTO site(name, info) VALUES ('ハエ座', 'その名の通り昆虫のハエの姿をした星座。もともとミツバチだったが、何度かの変更の末ハエになった。変更の理由は、カメレオン座の餌にするためとも言われている。')";
        arrayOfString2[57] = "INSERT INTO site(name, info) VALUES ('はくちょう座', '天の川に沿って翼を広げた白鳥の姿をした星座。はくちょうの尾にあるデネブは、夏の大三角を作る一等星。')";
        arrayOfString2[58] = "INSERT INTO site(name, info) VALUES ('はちぶんぎ座', '天体観測に使用する八分儀の姿をした星座。はちぶんぎ座の中に天の南極があることで知られている。')";
        arrayOfString2[59] = "INSERT INTO site(name, info) VALUES ('はと座', '旧約聖書のノアの箱舟に登場する鳩の姿をした星座。')";
        arrayOfString2[60] = "INSERT INTO site(name, info) VALUES ('ふうちょう座', '南の島に住む極楽鳥の姿をした小さな星座。日本から見ることのできない星座のひとつ。')";
        arrayOfString2[61] = "INSERT INTO site(name, info) VALUES ('ふたご座', '二人の男の子が仲良く並んだ姿を表した星座。黄道12星座のひとつ。')";
        arrayOfString2[62] = "INSERT INTO site(name, info) VALUES ('ペガスス座', '翼が生えた天馬の姿をした星座。ペガススの胴体部分にある四角形は、秋の四角形と呼ばれている。')";
        arrayOfString2[63] = "INSERT INTO site(name, info) VALUES ('へび座', '医神アスクレピオス（へびつかい座）に絡みつく大蛇の姿を表した星座。')";
        arrayOfString2[64] = "INSERT INTO site(name, info) VALUES ('へびつかい座', '大蛇（へび座）に絡みつかれる医神アスクレピオスの姿を表した星座。')";
        arrayOfString2[65] = "INSERT INTO site(name, info) VALUES ('ヘルクレス座', '数々の冒険を成し遂げた勇者ヘルクレスの姿を表した星座。夏を代表する大きな星座だが、壮大なイメージと違い3等星以下の暗い星で作られる。')";
        arrayOfString2[66] = "INSERT INTO site(name, info) VALUES ('ペルセウス座', 'ギリシャ神話に登場する勇者ペルセウスの姿を表した星座。毎年お盆のころに活発になるペルセウス流星群が見れることでも知られている。')";
        arrayOfString2[67] = "INSERT INTO site(name, info) VALUES ('ほ座', '風を受けて膨らむ帆船の穂の形をした星座。ギリシャ神話に登場する大帆船アルゴー号に由来するアルゴー座が4つに分割されてできた星座のひとつ。')";
        arrayOfString2[68] = "INSERT INTO site(name, info) VALUES ('ぼうえんきょう座', '星の観測に使う天体望遠鏡の形をした星座。フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[69] = "INSERT INTO site(name, info) VALUES ('ほうおう座', '伝説の不死鳥フェニックスの姿を表した星座。日本名は鳳凰から取られているが、もともとはフェニックスの姿の星座。')";
        arrayOfString2[70] = "INSERT INTO site(name, info) VALUES ('ポンプ座', '科学実験に使う真空ポンプの形をした星座。フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[71] = "INSERT INTO site(name, info) VALUES ('みずがめ座', 'ギリシャ神話に登場する水瓶を持つ美少年ガニメデスの姿をした星座。黄道12星座のひとつ。')";
        arrayOfString2[72] = "INSERT INTO site(name, info) VALUES ('みずへび座', '水中を泳ぐ水へびの姿をした星座。メスの海蛇であろううみへび座に対して、オスのみずへび座が設定されたとも言われている。')";
        arrayOfString2[73] = "INSERT INTO site(name, info) VALUES ('みなみじゅうじ座', '十字架の形をした星座。88星座の中で最も小さい星座であり、「南十字星」や「サザン・クロス」という通称で親しまれている。')";
        arrayOfString2[74] = "INSERT INTO site(name, info) VALUES ('みなみのうお座', '水面から飛び跳ねる魚の姿をした星座。一等星のフォーマルハウトは魚の口元に当たり、すぐ上のみずがめ座からこぼれた酒のしずくが魚の口に注がれていることを示している。')";
        arrayOfString2[75] = "INSERT INTO site(name, info) VALUES ('みなみのかんむり座', 'かんむりの形をした星座。北のかんむり座に対して、南には南のかんむり座がある。')";
        arrayOfString2[76] = "INSERT INTO site(name, info) VALUES ('みなみのさんかく座', '三角定規の形をした星座。北のさんかく座に対し、南にはみなみのさんかく座がある。')";
        arrayOfString2[77] = "INSERT INTO site(name, info) VALUES ('や座', '弓矢の形をした星座。夏の大三角の中にあるが、暗い星が多く目立たない星座。')";
        arrayOfString2[78] = "INSERT INTO site(name, info) VALUES ('やぎ座', '頭はヤギで尾が魚という不思議な姿をした星座。黄道12星座のひとつ。')";
        arrayOfString2[79] = "INSERT INTO site(name, info) VALUES ('やまねこ座', 'やまねこの姿を表した星座。星座の設計者であるヘベリウス自らも「見つけるのは難しい」というほどわかりづらい星座。')";
        arrayOfString2[80] = "INSERT INTO site(name, info) VALUES ('らしんばん座', '航海に使う羅針盤の形をした星座。ギリシャ神話に登場する大帆船アルゴー号に由来するアルゴー座が4つに分割されてできた星座のひとつ。')";
        arrayOfString2[81] = "INSERT INTO site(name, info) VALUES ('りゅう座', '北の空に大きく横たわる伝説の動物、竜の姿を表した星座。りゅう座には、しぶんぎ座流星群と10月流星群の2つの流星群がある。')";
        arrayOfString2[82] = "INSERT INTO site(name, info) VALUES ('りゅうこつ座', '船の骨組みを表した星座。ギリシャ神話に登場する大帆船アルゴー号に由来するアルゴー座が4つに分割されてできた星座のひとつ。')";
        arrayOfString2[83] = "INSERT INTO site(name, info) VALUES ('りょうけん座', 'うしかい座の巨人が連れているとされる2匹の猟犬の姿をした星座。犬には名前がついており、北側の犬はアステリオン、南側の犬はカーラという。')";
        arrayOfString2[84] = "INSERT INTO site(name, info) VALUES ('レチクル座', '望遠鏡の方向を合わせるための小純金の形をした星座。フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[85] = "INSERT INTO site(name, info) VALUES ('ろ座', '科学実験用の炉の形をした星座。フランスの天文学者ラカイユが設定した科学道具シリーズのひとつ。')";
        arrayOfString2[86] = "INSERT INTO site(name, info) VALUES ('ろくぶんぎ座', '公開や天体観測に使う測量器具の六分儀の形を表した星座。災難に合わないよう、しし座とうみへび座の間において、勇敢な2つの星座に守ってもらうことにしたと言われている。')";
        arrayOfString2[87] = "INSERT INTO site(name, info) VALUES ('わし座', '鷲の姿をした星座。七夕の「ひこ星」として知られている1等星アルタイルがあり、「おりひめ星」のベガと対になるよう天の川の反対側に位置している。')";
        paramString = "SELECT * FROM site WHERE name == " + paramString;
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS site");
        sQLiteDatabase.execSQL("CREATE TABLE site(id INTEGER PRIMARY KEY, name STRING, info STRING)");
        int i;
        for (i = 0; i < arrayOfString2.length; i++)
            sQLiteDatabase.execSQL(arrayOfString2[i]);
        Cursor cursor = sQLiteDatabase.rawQuery(paramString, null);
        while (cursor.moveToNext()) {
            int j = cursor.getColumnIndex("name");
            i = cursor.getColumnIndex("info");
            paramString = cursor.getString(j);
            String str = cursor.getString(i);
            arrayOfString1[0] = paramString;
            arrayOfString1[1] = str;
        }
        sQLiteDatabase.close();
        return arrayOfString1;
    }

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
        double d1 = (k - m - (int)(i * 30.6001D)) + paramDouble + 0.5D - (int)(paramDouble + 0.5D);
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
        paramDouble = (int)d1;
        double d2 = (int)d1;
        double d3 = (d1 - paramDouble) * 24.0D + Math.round((float)(this.Loc[1] / 15.0D));
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
        System.out.println(this.timer.get_Dtime());
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
        d10 = koseiji(d3, d1);
        d4 = (d3 - 2415021.0D) / 36525.0D;
        this.timer.set_JD(d3);
        this.timer.set_ST(d10 * 15.0D);
        this.timer.set_Cent(d4);
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
        d4 = Math.toDegrees(Math.asin(d4 * d2 * d9 + d4 * d1 * d10 + d11 * d3));
        d8 = Math.toDegrees(Math.atan(-(d6 * d7 + d8 * d2) / d12));
        d5 = d8;
        if (d12 < 0.0D)
            d5 = d8 + 180.0D;
        return new double[] { d4, d5 };
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
        int b;
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
        Julian();
        star_culc();
        setContentView(R.layout.activity_main);
        //TextView textView = (TextView)findViewById(R.layout.activity_main);
        String[] arrayOfString = getTimer();
        //textView.setText("" + this.Loc[0] + " " + this.Loc[1] + "\n" + arrayOfString[0] + " " + arrayOfString[1]);
    }

    public void onLocationChanged(Location paramLocation) {}

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
        } else if (paramSensorEvent.sensor.getType() == 2) {
            arrayOfFloat = paramSensorEvent.values;
            float[] arrayOfFloat1 = this.magnetometerReading;
            System.arraycopy(arrayOfFloat, 0, arrayOfFloat1, 0, arrayOfFloat1.length);
        }
    }

    @SuppressLint("ResourceType")
    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        int b;
        double[] arrayOfDouble1 = this.pla.get_xn();
        double[] arrayOfDouble2 = this.pla.get_yn();
        String[] arrayOfString = this.pla.get_coname();
        switch (paramMotionEvent.getAction()) {
            case 1:
                for (b = 0; b < this.pla.get_con_counter(); b++) {
                    if (arrayOfDouble1[b] >= (paramMotionEvent.getX() + 400.0F) && arrayOfDouble1[b] <= (paramMotionEvent.getX() + 520.0F) && arrayOfDouble2[b] >= (paramMotionEvent.getY() - 50.0F) && arrayOfDouble2[b] <= (paramMotionEvent.getY() - 10.0F)) {
                        System.out.println(arrayOfString[b]);
                        String[] arrayOfString1 = DataBase_Star("'" + arrayOfString[b] + "'");
                        System.out.println(arrayOfString1[0] + "\n" + arrayOfString1[1]);
                        LinearLayout linearLayout = (LinearLayout)findViewById(R.layout.activity_main);
                        TranslateAnimation translateAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, -linearLayout.getHeight());
                        translateAnimation.setDuration(500L);
                        translateAnimation.setFillAfter(true);
                        linearLayout.startAnimation((Animation)translateAnimation);
                        TextView textView = (TextView)findViewById(R.layout.activity_main);
                        textView.setText(arrayOfString1[0]);
                        textView.startAnimation((Animation)new TranslateAnimation(0.0F, 0.0F, 0.0F, -textView.getHeight()));
                        textView = (TextView)findViewById(R.layout.activity_main);
                        textView.setText(arrayOfString1[1]);
                        textView.startAnimation((Animation)new TranslateAnimation(0.0F, 0.0F, 0.0F, -textView.getHeight()));
                        ((TextView)findViewById(R.layout.activity_main)).setText("" + this.Loc[0] + " " + this.Loc[1] + "\n" + this.timer.get_Ydate() + " " + this.timer.get_Dtime());
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
        double d5 = Math.cos(Math.toRadians(d2));
        d2 = Math.sin(Math.toRadians(d3));
        d3 = Math.cos(Math.toRadians(d3));
        double d6 = Math.sin(Math.toRadians(d4));
        d4 = Math.cos(Math.toRadians(d4));
        return new double[] { (-d2 * d1 + d3 * d4 * d5) * paramArrayOfdouble[0] + (-d2 * d5 - d3 * d4 * d1) * paramArrayOfdouble[1] - d3 * d6 * paramArrayOfdouble[2], (d3 * d1 + d2 * d4 * d5) * paramArrayOfdouble[0] + (d3 * d5 - d2 * d4 * d1) * paramArrayOfdouble[1] - d2 * d6 * paramArrayOfdouble[2], d6 * d5 * paramArrayOfdouble[0] - d6 * d1 * paramArrayOfdouble[1] + paramArrayOfdouble[2] * d4 };
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
        Iterator<LineDataList> iterator1 = ReadCSV.conLineList.iterator();
        int b1;
        for (b1 = 0; iterator1.hasNext(); b1++) {
            LineDataList lineDataList = iterator1.next();
            arrayOfDouble19 = saisa_hosei(yogen_AD(lineDataList.get_linRas(), lineDataList.get_linDcs()));
            double[] arrayOfDouble22 = horizon(arrayOfDouble19);
            if (arrayOfDouble22[0] < 0.0D)
                continue;
            double[] arrayOfDouble21 = horizon(saisa_hosei(yogen_AD(lineDataList.get_linRae(), lineDataList.get_linDce())));
            if (arrayOfDouble21[0] < 0.0D)
                continue;
            if (b1 >= 500){
                break;
            }

            arrayOfDouble7[b1] = arrayOfDouble22[0];
            arrayOfDouble9[b1] = arrayOfDouble22[1];
            arrayOfDouble8[b1] = arrayOfDouble21[0];
            arrayOfDouble10[b1] = arrayOfDouble21[1];
        }
        int b2;
        for (b2 = 0; b2 < b1 && b2 < 500; b2++) {
            arrayOfDouble19 = dispXY(arrayOfDouble7[b2], arrayOfDouble9[b2]);
            arrayOfDouble11[b2] = arrayOfDouble19[0];
            arrayOfDouble12[b2] = arrayOfDouble19[1];
            arrayOfDouble19 = dispXY(arrayOfDouble8[b2], arrayOfDouble10[b2]);
            arrayOfDouble13[b2] = arrayOfDouble19[0];
            arrayOfDouble14[b2] = arrayOfDouble19[1];
        }
        Iterator<StarDataList> iterator = ReadCSV.starList.iterator();
        arrayOfDouble7 = arrayOfDouble20;
        while (iterator.hasNext()) {
            StarDataList starDataList = iterator.next();
            arrayOfDouble10 = proper_move(starDataList.get_stRA(), starDataList.get_stDC(), starDataList.get_stV1(), starDataList.get_stV2());
            starDataList.set_stRA((float)arrayOfDouble10[0]);
            starDataList.set_stDC((float)arrayOfDouble10[1]);
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
        int b3;
        for (b3 = 0; b3 < b2; b3++) {
            arrayOfDouble7 = dispXY(arrayOfDouble3[b3], arrayOfDouble4[b3]);
            arrayOfDouble1[b3] = arrayOfDouble7[0];
            arrayOfDouble2[b3] = arrayOfDouble7[1];
        }
        b3 = 0;
        for (StarNameList starNameList : ReadCSV.consNameList) {
            arrayOfDouble7 = horizon(saisa_hosei(yogen_AD(starNameList.get_Con_Ra(), starNameList.get_Con_Dc())));
            if (arrayOfDouble7[0] < 0.0D)
                continue;
            arrayOfDouble15[b3] = arrayOfDouble7[0];
            arrayOfDouble16[b3] = arrayOfDouble7[1];
            arrayOfString[b3] = starNameList.get_Con_name();
            b3++;
        }
        for (int b4 = 0; b4 < b3; b4++) {
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
        if (ActivityCompat.checkSelfPermission((Context)getApplication(), "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission((Context)getApplication(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this, new String[] { "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION" }, 1);
            return;
        }
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        String str = null;
        if (locationManager.isProviderEnabled("gps"))
            str = "gps";
        locationManager.requestLocationUpdates(str, 500L, 1.0F, this);
        Location location = locationManager.getLastKnownLocation(str);
        this.Loc[0] = location.getLatitude();
        this.Loc[1] = location.getLongitude();
    }

    public double[] yogen_AD(float paramFloat1, float paramFloat2) {
        double d1 = Math.toRadians(paramFloat1);
        double d2 = Math.toRadians(paramFloat2);
        return new double[] { Math.cos(d1) * Math.cos(d2), Math.cos(d2) * Math.sin(d1), Math.sin(d2) };
    }
}
