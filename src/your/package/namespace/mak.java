package your.ackage.namespace;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.*;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;



public class mak extends Activity implements TextWatcher{
   
	
    String before="";
    int len=0;
    int curlen=0;
	MyStreamSocket socket;
	 String portNum = "10";
      	String result="";
	    int windowwidth;
	    int windowheight;
	    int x_cord=0,y_cord=0;
	    int x_cord_temp=0,y_cord_temp=0;
	    ImageButton bleft,brite,bsave,bshut,bsnap;
	    EditText e;
	    Button b1,b2;
	    String clickvalue="";
    @Override
    
    public void onCreate(Bundle savedInstanceState) 
    {	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);  
        bleft=(ImageButton) findViewById(R.id.left);     //DECLARATIONS
        brite=(ImageButton)findViewById(R.id.right);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button) findViewById(R.id.button2);
        bsnap=(ImageButton) findViewById(R.id.imageButton4);
        e=(EditText) findViewById(R.id.editText1);
        bsave=(ImageButton)findViewById(R.id.save);
        bshut=(ImageButton)findViewById(R.id.ImageButton01);
        e.addTextChangedListener(this);
        // ESTABLISHING SOCKET
        String hostName =key.newip;
        try {
			socket = new MyStreamSocket(InetAddress.getByName(hostName), Integer.parseInt(portNum));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	//LEFT BUTTON
        bleft.setOnClickListener(new OnClickListener() 
        {
			public void onClick(View v) 
			{
				try
		    	{
					clickvalue="leftb*click";
					socket.sendMessage(clickvalue);
		    	}
				catch(Exception e)
				{
					System.out.println("left button click"+e);
				}
		    }
		});
        bsnap.setOnClickListener(new OnClickListener() 
        {
			public void onClick(View v) 
			{
				try
		    	{
					clickvalue="snapshot*click";
					socket.sendMessage(clickvalue);
					
					//new Thread(new Runnable() {
						
					//	public void run() {
				//while(true)
				//{
					String ss=socket.receiveMessage();
					System.out.println("------------------->"+ss);
					if(ss.equals("halooo"))
					{
					String path=Environment.getExternalStorageDirectory().getAbsolutePath();
					System.out.println(path+"/ss.png");
							File f=new File(path+"/ss.png");
							try 
							{
								socket.receiveFile(f);
							} catch (IOException e) {
								e.printStackTrace();
								System.out.println("screenshot" + e);
							}
				//}
						Intent i=new Intent(mak.this,screenshot.class);
						startActivity(i);
					}
							
							
						//}
					//}).start();
					
					
		    	}
				catch(Exception e)
				{
					System.out.println("snapshot "+e);
				}
		    }
		});
        brite.setOnClickListener(new OnClickListener() 
        {
			
			public void onClick(View v) 
			{
				try
		    	{
					clickvalue="rightb*click";
					socket.sendMessage(clickvalue);
		    	}
			
				catch(Exception e)
				{
					System.out.println("right button click"+e);
				}
			}
		});
        bsave.setOnClickListener(new OnClickListener() 
        {
			
			public void onClick(View v) 
			{
				try
		    	{
					clickvalue="save*click";
					socket.sendMessage(clickvalue);
		    	}
			
				catch(Exception e)
				{
					System.out.println("save"+e);
				}
			}
		});
        bshut.setOnClickListener(new OnClickListener() 
        {
			
			public void onClick(View v) 
			{
				try
				{
					socket.sendMessage("shutdown*click");
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
				
			});
        b1.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		try
        		{
        			clickvalue="scrollup*click";
        			socket.sendMessage(clickvalue);
        		}
        		catch(Exception e)
        		{
        			System.out.println("scroll up click"+e);
        		}
        		
        	}
        });
        b2.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		try
        		{
        			clickvalue="scrolldown*click";
        			socket.sendMessage(clickvalue);
        		}
        		catch(Exception e)
        		{
        			System.out.println("scroll  down click"+e);
        		}
        		
        	}
        });
        
        try
        {      
        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        windowheight = getWindowManager().getDefaultDisplay().getHeight();
        
        LinearLayout obj_linearlayout=(LinearLayout)findViewById(R.id.sendco);
        obj_linearlayout.setOnTouchListener(new View.OnTouchListener() 
        {

                        public boolean onTouch(View v, MotionEvent event) 
                        {
                          
                            switch(event.getAction())
                            {
                            
                            case MotionEvent.ACTION_MOVE:	 x_cord_temp=x_cord;
                            								 y_cord_temp=y_cord;
                            								 
                                                            x_cord=(int)event.getRawX();
                                                            y_cord=(int)event.getRawY();
                                                            
                                                           
                                                            

                                                            if(x_cord>windowwidth){x_cord=windowwidth;}
                                                            if(y_cord>windowheight){y_cord=windowheight;}

                                    	        						    try
                                    	        						    {
                                    	        						    	if(x_cord>x_cord_temp&&y_cord>y_cord_temp)
                                    	        						    	{
                                    	        						    		result="dr";
                                    	        						    	}
                                    	        						    	else if(x_cord>x_cord_temp&&y_cord<y_cord_temp)
                                    	        						    	{
                                    	        						    		result="ur";
                                    	        						    	}
                                    	        						    	else if(x_cord<x_cord_temp&&y_cord>y_cord_temp)
                                    	        						    	{
                                    	        						    		result="ld";
                                    	        						    	}
                                    	        						    	else if(x_cord<x_cord_temp&&y_cord<y_cord_temp)
                                    	        						    	{
                                    	        						    		result="lu";
                                    	        						    	}
                                    	        						    	else if(x_cord<x_cord_temp&&y_cord==y_cord_temp)
                                    	        						    	{
                                    	        						    		result="l";
                                    	        						    	}
                                    	        						    	else if(x_cord>x_cord_temp&&y_cord==y_cord_temp)
                                    	        						    	{
                                    	        						    		result="r";
                                    	        						    	}
                                    	        						    	else if(x_cord==x_cord_temp&&y_cord<y_cord_temp)
                                    	        						    	{
                                    	        						    		result="u";
                                    	        						    	}
                                    	        						    	else if(x_cord==x_cord_temp&&y_cord>y_cord_temp)
                                    	        						    	{
                                    	        						    		result="d";
                                    	        						    	}
                                    	        						    	else
                                    	        						    	{
                                    	        						    		
                                    	        						    	}
                                    	        						    	socket.sendMessage(result+"*mouse");
                                    	        						    }
                                    	        						    catch(Exception e)
                                    	        						    {
                                    	        						    	System.out.println("send socket-->"+e);
                                    	        						    }
                                    	        		                           
                                                       break;
                            default:
                                                       break;
                            }
                                return true;
                        }
                });
        }
        catch (Exception ex) 
        {
            System.out.println(ex);
        } 
   }
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	before=s.toString();
		
	len=before.length();
	}
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		//Toast.makeText(Mak1Activity.this,s, Toast.LENGTH_SHORT).show();
		
		try 
		{
			
			String current=s.toString();
			curlen=current.length();
			if(curlen>=len)
			{
			String c_text="";
			c_text=current.substring(len,len+1);
			
			if(c_text.equals(" "))
			{
				c_text="space";
				socket.sendMessage(c_text+"*keyboard");
			}
			else
			{
				
				socket.sendMessage(c_text+"*keyboard");
			}
			
			
			Toast.makeText(mak.this,c_text, Toast.LENGTH_SHORT).show();
		     }
			else
			{
				socket.sendMessage(s+"*backspace");
				
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	} 
    
  
}

    

    
