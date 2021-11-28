package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //创建Button对象   也就是activity_main.xml里所设置的ID
    Button zero,one,two,three,four,five,six,seven,eight,nine,spot;
    Button mul,div,plus,min;
    Button cls,equal,percent;
    private Context context;
    private Toast toast;
    EditText result;
    boolean clr_flag;    //判断et编辑文本框中是否清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化对象
        setContentView(R.layout.activity_main);
        zero= (Button) findViewById(R.id.zero);
        one= (Button) findViewById(R.id.one);
        two= (Button) findViewById(R.id.two);
        three= (Button) findViewById(R.id.three);
        four= (Button) findViewById(R.id.four);
        five= (Button) findViewById(R.id.five);
        six= (Button) findViewById(R.id.six);
        seven= (Button) findViewById(R.id.seven);
        eight= (Button) findViewById(R.id.eight);
        nine= (Button) findViewById(R.id.nine);
        spot= (Button) findViewById(R.id.spot);
        plus= (Button) findViewById(R.id.plus);
        min= (Button) findViewById(R.id.min);
        mul= (Button) findViewById(R.id.mul);
        div= (Button) findViewById(R.id.div);
        cls= (Button) findViewById(R.id.cls);
        equal= (Button) findViewById(R.id.equal);
        percent= (Button) findViewById(R.id.percent);
        result= (EditText) findViewById(R.id.result);

        context=getApplicationContext();


        //给按钮设置点击事件
        //点击响应
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        spot.setOnClickListener(this);
        plus.setOnClickListener(this);
        min.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        cls.setOnClickListener(this);
        equal.setOnClickListener(this);
        percent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str=result.getText().toString();//获取文本框中的文本内容
        switch (v.getId()){
            case   R.id.zero:
            case   R.id.one:
            case   R.id.two:
            case   R.id.three:
            case   R.id.four:
            case   R.id.five:
            case   R.id.six:
            case   R.id.seven:
            case   R.id.eight:
            case   R.id.nine:
            case   R.id.spot:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    result.setText("");
                }
                result.setText(str+((Button)v).getText());
                break;
            case R.id.plus:
            case R.id.min:
            case R.id.mul:
            case R.id.div:
            case R.id.percent:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    result.setText("");
                }
                if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")||str.contains("%")) {
                    str=str.substring(0,str.indexOf(" "));
                }
                result.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.cls:
                if(clr_flag)
                    clr_flag=false;
                str="";
                result.setText("");
                break;
            case R.id.equal: //单独运算最后结果
                getResult();//调用下面的方法
                break;
        }
    }

    private void getResult() {

        double cnt=0;
        int g=0;
        int k=0;
        String exp=result.getText().toString();
        if(exp==null||exp.equals("")) return ;
        //因为没有运算符所以不用运算
        if(!exp.contains(" ")){
            return ;
        }
        if(clr_flag){
            clr_flag=false;
            return;
        }
        clr_flag=true;
        //截取运算符前面的字符串
        String s1=exp.substring(0,exp.indexOf(" "));//substring提取子串
        //截取的运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //截取运算符后面的字符串
        String s2=exp.substring(exp.indexOf(" ")+3);

        for(int i=0;i<s1.length();i++){
            char c = s1.charAt(i);
            if(c=='.')
                k++;
        }
        for(int i=0;i<s2.length();i++){
            char c = s2.charAt(i);
            if(c=='.')
                g++;
        }
        if(k>1||g>1){
            cnt=0;
            toast.makeText(getApplicationContext(),"数据输入错误",toast.LENGTH_LONG).show();
            result.setText(cnt+"");
        }
        else if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("×")){
                cnt=d1*d2;
            }
            if(op.equals("÷")){
                if(d2==0) {
                    cnt=0;
                    toast.makeText(getApplicationContext(),"除数不能为0",toast.LENGTH_LONG).show();
                }
                else cnt=d1/d2;
            }
            if(op.equals("%")){
                cnt=0;
                toast.makeText(getApplicationContext(),"%格式错误",toast.LENGTH_LONG).show();
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
                int res = (int) cnt;
                result.setText(res+"");
            }else {
                result.setText(cnt+"");}
        }
        //如果s1不是空    s2是空  就执行下一步
        else if(!s1.equals("")&&s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d3=100;
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
                toast.makeText(getApplicationContext(),"除数不能为0",toast.LENGTH_LONG).show();
            }
            if(op.equals("%")){
                cnt=d1/d3;
            }
            if(!s1.contains(".")&&!op.equals("%")) {
                int res = (int) cnt;
                result.setText(res+"");
            }else {
                result.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(op.equals("%")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                result.setText(res+"");
            }else {
                result.setText(cnt+"");}
        }
        else {
            result.setText("");
        }
    }
}