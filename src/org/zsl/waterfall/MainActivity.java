package org.zsl.waterfall;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WaterfallLayout group = new WaterfallLayout(this);
        group.setFallCount(3);
        LayoutParams lp1 = new LayoutParams(50, 100);
        LayoutParams lp2 = new LayoutParams(100, 200);
        
        TextView c1 = new TextView(this);
        c1.setText("c1");
        c1.setBackgroundColor(Color.RED);
        
        TextView c2 = new TextView(this);
        c2.setBackgroundColor(Color.WHITE);
        c2.setText("c2");
        
        TextView c3 = new TextView(this);
        c3.setBackgroundColor(Color.GRAY);
        c3.setText("c3");
        
        TextView c4 = new TextView(this);
        c4.setBackgroundColor(Color.GREEN);
        c4.setText("c4");
        
        TextView c5 = new TextView(this);
        c5.setBackgroundColor(Color.YELLOW);
        c5.setText("c5");
        
        group.addView(c1, lp1);
        group.addView(c2, lp2);
        group.addView(c3, lp1);
        group.addView(c4, lp1);
        group.addView(c5, lp2);
        
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
