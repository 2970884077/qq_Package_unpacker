package com.mycompany.myapp2;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;

public class MainActivity extends Activity 
{
	private Button button1 = null;
	private EditText edittextsessionkey = null;
	private EditText edittextqq = null;
	private EditText edittextdata= null;
	private EditText edittextoutput= null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		this.edittextsessionkey= (EditText) findViewById(R.id.mainEditTextkey);
		this.edittextdata=(EditText) findViewById(R.id.mainEditTextin);
		this.edittextqq=(EditText) findViewById(R.id.mainEditTextqq);
		this.edittextoutput=(EditText) findViewById(R.id.mainEditText1out);
		this.button1=(Button) findViewById(R.id.mainButton1);
		this.edittextdata.setText(Data.data);
		button1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v)
				{
					if (String.valueOf(edittextsessionkey.getText()).equals("") != true && String.valueOf(edittextqq.getText()).equals("") != true && String.valueOf(edittextdata.getText()).equals("") != true ){
					    Toast.makeText(MainActivity.this, "Started", Toast.LENGTH_SHORT).show();
					    boolean result = start_analyze(String.valueOf(edittextdata.getText()), String.valueOf(edittextsessionkey.getText()),String.valueOf(edittextqq.getText()));
						Toast.makeText(MainActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();

					}
					else
					{
						Toast.makeText(MainActivity.this, "Must not be empty", Toast.LENGTH_SHORT).show();
					}
				}




			});
    }
	
	
	public Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			switch (msg.what)
			{
				case 0:
					//完成主界面更新,拿到数据
					String data = (String)msg.obj;
					edittextoutput.setText(data);
					break;
				default:
					break;
			}
		}

	};


	private boolean start_analyze(String data, String p1, String p2)
	{
		ParseFactory factory = new ParseFactory(Util.str_to_byte(p1),p2);
	    ByteFactory bytefactory = new ByteFactory(Util.str_to_byte(data.replaceAll("\\n","")));
		ByteBuilder builder = new ByteBuilder();
		while(bytefactory.position<bytefactory.data.length){
			int length = (int) bytefactory.readlong();
			System.out.println(length);
			if (length%16==0){
			    builder.writebytes(factory.parse(bytefactory.readBytes(length-4)));
			}else{
				bytefactory.readBytes(length-4);
			}
		}
		
		mHandler.sendEmptyMessage(0);

		//需要数据传递，用下面方法；
		Message msg =new Message();
		//可以是基本类型，可以是对象，可以是List、map等；
		
		msg.obj=Util.byte2HexString(builder.getdata());
		mHandler.sendMessage(msg);
		return true;
	}
	
	
}
