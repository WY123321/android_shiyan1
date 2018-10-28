package com.example.administrator.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;


public class MainActivity extends Activity implements View.OnClickListener {

    Button jinzhi2;
    Button jinzhi10;
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_point; //小数点
    Button btn_clear; //清除
    Button btn_del;   //删除
    Button btn_plus;
    Button btn_minus;
    Button btn_multiply;
    Button btn_divide;
    Button btn_equal;
    Button btn_left;
    Button btn_right;
    private TextView et_input;
    private StringBuilder pending = new StringBuilder();

    private void initView() {
        jinzhi2=(Button) findViewById(R.id.jinzhi2);
        jinzhi10=(Button) findViewById(R.id.jinzhi10);
        btn_0 = (Button) findViewById(R.id.button0);
        btn_1 = (Button) findViewById(R.id.button1);
        btn_2 = (Button) findViewById(R.id.button2);
        btn_3 = (Button) findViewById(R.id.button3);
        btn_4 = (Button) findViewById(R.id.button4);
        btn_5 = (Button) findViewById(R.id.button5);
        btn_6 = (Button) findViewById(R.id.button6);
        btn_7 = (Button) findViewById(R.id.button7);
        btn_8 = (Button) findViewById(R.id.button8);
        btn_9 = (Button) findViewById(R.id.button9);
        btn_point = (Button) findViewById(R.id.add);
        btn_clear = (Button) findViewById(R.id.clear);
        btn_del = (Button) findViewById(R.id.delete);
        btn_plus = (Button) findViewById(R.id.dian);
        btn_minus = (Button) findViewById(R.id.jan);
        btn_multiply = (Button) findViewById(R.id.chen);
        btn_divide = (Button) findViewById(R.id.chu);
        btn_equal = (Button) findViewById(R.id.equal);
        btn_left = (Button) findViewById(R.id.left);
        btn_right = (Button) findViewById(R.id.right);
        et_input = (TextView) findViewById(R.id.text);


        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        jinzhi2.setOnClickListener(this);
        jinzhi10.setOnClickListener(this);
    }


    void LogView(StringBuilder msg) {
        et_input.append(msg);
        int offset = et_input.getLineCount() * et_input.getLineHeight();
        if (offset > et_input.getHeight()) {
            et_input.scrollTo(0, offset - et_input.getHeight());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        et_input.setMovementMethod(ScrollingMovementMethod.getInstance());

    }


    public void onClick(View v) {
        int last = 0;
        if (pending.length() != 0) {
            last = pending.codePointAt(pending.length() - 1);

        }
        LogView(pending);
        switch (v.getId()) {
            case R.id.button0:
                pending = pending.append("0");
                et_input.setText(pending);
                break;
            case R.id.button1:
                pending = pending.append("1");
                et_input.setText(pending);
                break;
            case R.id.button2:
                pending = pending.append("2");
                et_input.setText(pending);
                break;
            case R.id.button3:
                pending = pending.append("3");
                et_input.setText(pending);
                break;
            case R.id.button4:
                pending = pending.append("4");
                et_input.setText(pending);
                break;
            case R.id.button5:
                pending = pending.append("5");
                et_input.setText(pending);
                break;
            case R.id.button6:
                pending = pending.append("6");
                et_input.setText(pending);
                break;
            case R.id.button7:
                pending = pending.append("7");
                et_input.setText(pending);
                break;
            case R.id.button8:
                pending = pending.append("8");
                et_input.setText(pending);
                break;
            case R.id.button9:
                pending = pending.append("9");
                et_input.setText(pending);
                break;
            case R.id.add:
                pending = pending.append("+");
                et_input.setText(pending);
                break;
            case R.id.jan:
                pending = pending.append("-");
                et_input.setText(pending);
                break;
            case R.id.chen:
                pending = pending.append("×");
                et_input.setText(pending);
                break;
            case R.id.chu:
                pending = pending.append("÷");
                et_input.setText(pending);
                break;
            case R.id.dian:
                if (judje1()) {
                    pending = pending.append(".");
                    et_input.setText(pending);
                }
                break;
            case R.id.right:// 右括号
                if ((last >= '0' && last <= '9' || last == ')') && judje2() == 1) {
                    pending = pending.append(")");
                    et_input.setText(pending);
                }
                break;
            case R.id.left:// 左括号
                if ((last != '(') || (last <= '0' && last >= '9')) {
                    pending = pending.append("(");
                    et_input.setText(pending);
                }
                break;

            case R.id.delete: //删除
                if (pending.length() != 0) {
                    pending = pending.delete(pending.length() - 1, pending.length());
                    et_input.setText(pending);
                }
                break;
            case R.id.clear: //清空
                pending = pending.delete(0, pending.length());
                et_input.setText(pending);
                break;
            case R.id.jinzhi2:
                if (judge3() == 0) {

                    et_input.setText(pending + "\n十进制  " + Integer.valueOf(String.valueOf(pending), 2).toString()
                    );
                } else {
                    et_input.setText("输入有误");
                }
                break;
            case R.id.jinzhi10:

                int b=Integer.parseInt(String.valueOf(pending));
                et_input.setText(pending + "\n二进制  " + Integer.toBinaryString(b)
                );

                break;

            case R.id.equal: // 等于
                if ((pending.length() > 1)) {
                    InfixInToDuffix inf = new InfixInToDuffix();
                    String jieguo;
                    try {
                        String a = inf.toSuffix(pending);
                        jieguo = inf.dealEquation(a);

                    } catch (Exception ex) {
                        jieguo = "出错";
                    }
                    et_input.setText(pending + "=" + jieguo);
                    pending = pending.delete(0, pending.length());
                    if (Character.isDigit(jieguo.charAt(0))) {
                        pending = pending.append(jieguo);
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean judje1() {
        String a = "+-×÷";
        int[] b = new int[a.length()];
        int max;
        for (int i = 0; i < a.length(); i++) {
            String c = "" + a.charAt(i);
            b[i] = pending.lastIndexOf(c);
        }
        Arrays.sort(b);
        if (b[a.length() - 1] == -1) {
            max = 0;
        } else {
            max = b[a.length() - 1];
        }
        if (pending.indexOf(".", max) == -1) {
            return true;
        } else {
            return false;
        }
    }

    private int judje2() {
        int a = 0, b = 0;
        for (int i = 0; i < pending.length(); i++) {
            if (pending.charAt(i) == '(') {
                a++;
            }
            if (pending.charAt(i) == ')') {
                b++;
            }
        }
        if (a == b)
            return 0;
        if (a > b)
            return 1;
        return 2;
    }

    private int judge3() {
        int a = 0;
        for (int i = 0; i < pending.length(); i++) {
            if (pending.charAt(i) != '0' && pending.charAt(i) != '1') {
                a = 1;
            }
        }
        return a;
    }


}
