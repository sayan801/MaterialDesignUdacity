package org.honorato.materialdesignudacity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GridDetailActivity extends AppCompatActivity {
    public static final String KEY_COLORS = "colors";
    String[] mColors = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_detail);
        setupColors(this.getIntent().getExtras());
        setupElements();
    }

    protected void setupColors(Bundle extras) {
        if (mColors != null) {
            return;
        }
        if (extras == null || !extras.containsKey(KEY_COLORS)) {
            return;
        }
        mColors = extras.getStringArray(KEY_COLORS);
    }

    protected void setupElements() {
        setupToolbar();
        setupFab();
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor(mColors[GridActivity.DARK_PRIMARY]));
        }

        // Entire bar layout
        int barColor = Color.parseColor(mColors[GridActivity.PRIMARY]);
        AppBarLayout barLayout = (AppBarLayout) this.findViewById(R.id.app_bar);
        if (barLayout != null) {
            barLayout.setBackgroundColor(barColor);
        }
        toolbar.setBackgroundColor(barColor);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.collapsing_toolbar_layout);
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setBackgroundColor(barColor);
            collapsingToolbarLayout.setContentScrimColor(barColor);
        }
    }

    protected void setupFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(mColors[GridActivity.ACCENT])));
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        if (mColors != null) {
            saveInstanceState.putStringArray(KEY_COLORS, mColors);
        }
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        setupColors(saveInstanceState);
        super.onRestoreInstanceState(saveInstanceState);
    }
}
