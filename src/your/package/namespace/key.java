package your.ackage.namespace;


import java.io.IOException;
import java.net.InetAddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class key extends Activity {
	 static String newip="",newkey="";
	  String portNum = "7";
	  String chk="";
	  MyStreamSocket socket;
	  String ss="";
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.key);
	        Button b1=(Button) findViewById(R.id.login);
	        
	       
	        do
	        {
	        b1.setOnClickListener(new OnClickListener() 
	        {
				public void onClick(View v) 
				{ 
					EditText ipaddr=(EditText)findViewById(R.id.ip);
				    EditText key=(EditText)findViewById(R.id.key);
				    newip=ipaddr.getText().toString();
				    newkey=key.getText().toString();
				    String hostName =newip;
				    try
			    	{
			    		socket = new MyStreamSocket(InetAddress.getByName(hostName), Integer.parseInt(portNum));
						socket.sendMessage(newkey);

						ss=socket.receiveMessage();
						if(ss.equals("correct"))
						{
							Intent i=new Intent(key.this,mak.class);
							startActivity(i);
						}
					}	
				    catch (IOException e)
				    {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   
			    }
	  
				
	
	        });
	        if(ss.equals("wrong"))
			Toast.makeText(key.this,"WRONG KEY!!", Toast.LENGTH_SHORT).show();
	}       while(ss.equals("wrong"));
}
}