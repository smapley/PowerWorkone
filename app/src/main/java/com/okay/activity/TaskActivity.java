package com.okay.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.okay.R;
import com.okay.widget.KCalendar;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends Activity {
    private String date = null;// ����Ĭ��ѡ�е����� ��ʽΪ ��2014-04-05�� ��׼DATE��ʽ
    private ProgressDialog dialog;
    private String[] updata = new String[2];
    private String now_month = null;
    private String now_year = null;
    // ��ͷ�·�
    private String[] title_month = {"Jan.", "Feb.", "Mar.", "Apr", "May.",
            "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.", "Dec."};
    private KCalendar calendar;
    private RelativeLayout layout;
    private TextView popupwindow_calendar_month, rili_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rili);

        dialog = new ProgressDialog(this);
        dialog.setTitle("��ʾ��");
        dialog.setMessage("���������С�����");

        layout = (RelativeLayout) this.findViewById(R.id.activity_rili_layout);

        popupwindow_calendar_month = (TextView) this
                .findViewById(R.id.popupwindow_calendar_month);
        rili_year = (TextView) this.findViewById(R.id.rili_year);
        calendar = (KCalendar) this.findViewById(R.id.popupwindow_calendar);
        popupwindow_calendar_month.setText(title_month[calendar
                .getCalendarMonth() - 1]);
        rili_year.setText(calendar.getCalendarYear() + "");

        now_year = calendar.getCalendarYear() + "";
        if (calendar.getCalendarMonth() < 10) {
            now_month = "0" + calendar.getCalendarMonth();
        } else {
            now_month = calendar.getCalendarMonth() + "";
        }

        updata[0] = "16";
        updata[1] = calendar.getCalendarYear() + "��" + now_month + "��";
        if (null != date) {

            int years = Integer.parseInt(date.substring(0, date.indexOf("-")));
            int month = Integer.parseInt(date.substring(date.indexOf("-") + 1,
                    date.lastIndexOf("-")));
            popupwindow_calendar_month.setText(years + "��" + month + "��");

            calendar.showCalendar(years, month);
            calendar.setCalendarDayBgColor(date,
                    R.drawable.calendar_date_focused);
        }

        List<String> list = new ArrayList<String>(); // ���ñ���б�
        list.add("2014-04-01");
        list.add("2014-04-02");
        calendar.addMarks(list, 0);
        // ������ѡ�е�����
        calendar.setOnCalendarClickListener(new KCalendar.OnCalendarClickListener() {

            public void onCalendarClick(int row, int col, String dateFormat) {
                int month = Integer.parseInt(dateFormat.substring(
                        dateFormat.indexOf("-") + 1,
                        dateFormat.lastIndexOf("-")));

                if (calendar.getCalendarMonth() - month == 1// ������ת
                        || calendar.getCalendarMonth() - month == -11) {
                    calendar.lastMonth();

                } else if (month - calendar.getCalendarMonth() == 1 // ������ת
                        || month - calendar.getCalendarMonth() == -11) {
                    calendar.nextMonth();

                } else {
                    Intent intent = new Intent();
                    intent.setClass(TaskActivity.this, TeamActivity.class);
                    intent.putExtra("date", dateFormat);
                    startActivity(intent);
                }
            }
        });

        // ������ǰ�·�
        calendar.setOnCalendarDateChangedListener(new KCalendar.OnCalendarDateChangedListener() {
            public void onCalendarDateChanged(int year, int month) {
                popupwindow_calendar_month.setText(title_month[month - 1]);
                now_year = year + "";
                if (month < 10) {
                    now_month = "0" + month;
                } else {
                    now_month = month + "";
                }
                // ---------------------------------------------------------------------
                calendar.getCalendarMonth();
                updata[1] = year + "��" + now_month + "��";
                // ------------------------------------------------------------------------
            }
        });

        // ���¼�����ť
        RelativeLayout popupwindow_calendar_last_month = (RelativeLayout) this
                .findViewById(R.id.popupwindow_calendar_last_month);
        popupwindow_calendar_last_month
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        calendar.lastMonth();
                    }

                });

        // ���¼�����ť
        RelativeLayout popupwindow_calendar_next_month = (RelativeLayout) this
                .findViewById(R.id.popupwindow_calendar_next_month);
        popupwindow_calendar_next_month
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        calendar.nextMonth();
                    }
                });

    }


}
