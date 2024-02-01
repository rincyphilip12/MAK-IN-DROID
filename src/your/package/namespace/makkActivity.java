package your.ackage.namespace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class makkActivity extends Activity {
	
	Handler aa=new Handler();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        aa.postDelayed(task,7000);
    }

  	 
    final Runnable task=new Runnable()
    {
  		public void run(){
  			Intent i = new Intent(makkActivity.this,key.class);
  			startActivity(i);
  		}
  	};
}
  	
  	/*try {
		Thread.sleep(50);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  	Intent i = new Intent(makkActivity.this,key.class);
	startActivity(i);
    }
}*/