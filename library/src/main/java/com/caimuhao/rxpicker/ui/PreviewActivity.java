package com.caimuhao.rxpicker.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.caimuhao.rxpicker.R;
import com.caimuhao.rxpicker.bean.MediaItem;
import com.caimuhao.rxpicker.ui.adapter.VpPreViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Smile
 * @time 2017/4/20  上午11:37
 * @desc ${TODD}
 */
public class PreviewActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private TextView tvTitle;
  private ViewPager vpPreview;
  private VpPreViewAdapter vpAdapter;

  private List<MediaItem> data;

  public static void start(Context context, ArrayList<MediaItem> data) {
    Intent intent = new Intent(context, PreviewActivity.class);
    intent.putExtra("data", data);
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preview);
    handleData();
    setupToolbar();
    vpPreview = (ViewPager) findViewById(R.id.vp_preview);
    vpAdapter = new VpPreViewAdapter(data);
    vpPreview.setAdapter(vpAdapter);
    vpPreview.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override public void onPageSelected(int position) {
        toolbar.setTitle(position + 1 + "/" + data.size());
      }
    });
  }

  private void handleData() {
    data = (List<MediaItem>) getIntent().getSerializableExtra("data");
  }

  private void setupToolbar() {
    toolbar = (Toolbar) findViewById(R.id.nav_top_bar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("1/" + data.size());
  }
}
