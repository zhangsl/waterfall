package org.zsl.waterfall;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AbsoluteLayout absolute = new AbsoluteLayout(this);
        WaterfallLayout group = new WaterfallLayout(this);
        group.setFallCount(3);
        LayoutParams lp1 = new LayoutParams(50, 100);
        LayoutParams lp2 = new LayoutParams(100, 100);
        
        TextView c1 = new TextView(this);
        c1.setText("c1");
        c1.setBackgroundColor(Color.RED);
        
        TextView c2 = new TextView(this);
        c2.setBackgroundColor(Color.WHITE);
        c2.setText("c2");
        
        TextView c3 = new TextView(this);
        c3.setBackgroundColor(Color.GRAY);
        c3.setText("c3");
        
        group.addView(c1, lp1);
//        absolute.addView(c1, lp1);
        group.addView(c2, lp1);
        group.addView(c3, lp1);
        
        group.setBackgroundColor(Color.BLUE);
        
        setContentView(group, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
