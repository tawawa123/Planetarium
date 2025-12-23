package es.exsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawStar extends View {
    double[] MG = new double[1000];

    ExSample main = new ExSample();

    Paint paint = new Paint();

    Planetarium pla = new Planetarium();

    int[] rd = new int[1000];

    public static float centerX = 0;
    public static float centerY = 0;

    public DrawStar(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        ExSample.centerX = w / 2f;
        ExSample.centerY = h / 2f;
    }

    protected void onDraw(Canvas canvas) {
        if (Planetarium.star_counter <= 0) {
            return; // データ未準備
        }
        if (Planetarium.XX == null || Planetarium.YY == null || Planetarium.MG == null) {
            return;
        }

        canvas.drawColor(Color.BLACK);

        this.paint.setAntiAlias(false);
        this.paint.setColor(Color.argb(255, 0, 32, 255));
        canvas.drawCircle(550.0F, 1000.0F, 510.0F, this.paint);
        int b;
        for (b = 0; b < Planetarium.star_counter; b++) {
            this.paint.setColor(Color.argb(255, 255, 255, 255));
            this.rd[b] = this.main.magnitude(Planetarium.MG[b]);
            this.paint.setStrokeWidth(3.0F);
            canvas.drawOval((float)Planetarium.XX[b] - (this.rd[b] / 2), (float)Planetarium.YY[b] - (this.rd[1] / 2), (float)Planetarium.XX[b] + (this.rd[b] / 2), (float)Planetarium.YY[b] + (this.rd[b] / 2), this.paint);
        }
        for (b = 0; b < this.pla.get_line_counter(); b++) {
            this.paint.setStrokeWidth(1.0F);
            this.paint.setColor(Color.argb(255, 255, 255, 255));
            //System.out.println(this.pla.get_x1());
            canvas.drawLine((float)this.pla.x1[b], (float)this.pla.y1[b], (float)this.pla.x2[b], (float)this.pla.y2[b], this.paint);
        }
        for (b = 0; b < Planetarium.con_counter; b++) {
            this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.paint.setStrokeWidth(1.0F);
            this.paint.setTextSize(15.0F);
            this.paint.setColor(Color.argb(255, 255, 0, 0));
            canvas.drawText(Planetarium.coname[b], (float)Planetarium.xn[b], (float)Planetarium.yn[b], this.paint);
        }
    }
}
