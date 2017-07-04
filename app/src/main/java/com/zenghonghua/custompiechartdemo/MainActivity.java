package com.zenghonghua.custompiechartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyPieChart pie_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pie_chart = (MyPieChart) findViewById(R.id.pie_chart);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       pie_chart.setUsePercentValues(true);
       pie_chart.getDescription().setEnabled(false);
       pie_chart.setExtraOffsets(0.f, 10.f, 0.f, 10.f);

       pie_chart.setDragDecelerationFrictionCoef(0.95f);
       pie_chart.setCenterText("图表");

       pie_chart.setDrawHoleEnabled(true);
       pie_chart.setHoleColor(Color.WHITE); //中间圆颜色
       pie_chart.setTransparentCircleColor(Color.WHITE);
       pie_chart.setTransparentCircleAlpha(110);

       pie_chart.setHoleRadius(40f);  //圆半径
       pie_chart.setTransparentCircleRadius(44f);
       pie_chart.setDrawCenterText(true);

       pie_chart.setRotationAngle(-90);
        //the chart by touch
       pie_chart.setRotationEnabled(true);
       pie_chart.setHighlightPerTapEnabled(true);

        //((PieHolder) holder).pie_chart.setEntryLabelColor(mContext.getResources().getColor(R.color.red_main)); //设置饼图标签颜色

        setPieData(pie_chart, 6, 100);

        pie_chart.animateY(1200, Easing.EasingOption.EaseInOutQuad); //设置饼图动画

        Legend l = pie_chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTextColor(Color.BLUE);
        l.setEnabled(false);
    }

    /**
     * 设置饼图数据
     */
    private void setPieData(MyPieChart chart, int count, float range) {
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
//        for (int i = 0; i < mPieList.size(); i++) {
//            PieInfo pieInfo = mPieList.get(i);
//
//            //如果百分比小于1,则不显示设置为空字符串
//            if ((int) pieInfo.getRatio() < 5) {
//                PieEntry entry = new PieEntry(pieInfo.getRatio(), "");
//                entries.add(entry);
//            } else {
//                PieEntry entry = new PieEntry(pieInfo.getRatio(), pieInfo.getTitil());
//                entries.add(entry);
//            }
//        }
        PieEntry sz = new PieEntry(30f, "深圳0深圳0");
        PieEntry sz1 = new PieEntry(12f, "深圳1");
        PieEntry sz2 = new PieEntry(10f, "深圳2");
        PieEntry sz3 = new PieEntry(29f, "深圳3");
        PieEntry sz4 = new PieEntry(11f, "深圳4");
        PieEntry sz5 = new PieEntry(8f, "其他");
        entries.add(sz);
        entries.add(sz1);
        entries.add(sz2);
        entries.add(sz3);
        entries.add(sz4);
        entries.add(sz5);

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);  //不同块之间的间距
        dataSet.setSelectionShift(7f);//选中时候突出的间距

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.pie_1));
        colors.add(getResources().getColor(R.color.pie_2));
        colors.add(getResources().getColor(R.color.pie_3));
        colors.add(getResources().getColor(R.color.pie_4));
        colors.add(getResources().getColor(R.color.pie_5));
        colors.add(getResources().getColor(R.color.pie_6));
        dataSet.setColors(colors);

        dataSet.setValueLinePart1OffsetPercentage(100f);
        dataSet.setValueLinePart1Length(0.6f);
        dataSet.setValueLinePart2Length(0.2f);
        dataSet.setHighlightEnabled(true);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//标签显示在外面，关闭显示在饼图里面

        dataSet.setValueLineColor(0xff000000);  //设置指示线条颜色,必须设置成这样，才能和饼图区域颜色一致

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setHighlightEnabled(true);

        chart.setData(data);
        chart.highlightValues(null);
        chart.invalidate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
