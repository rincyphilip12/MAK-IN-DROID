package your.ackage.namespace;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

public class screenshot extends Activity{
	
	
	 public void onCreate(Bundle savedInstanceState) 
	    {	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.screen);  
	        String imageInSD=Environment.getExternalStorageDirectory().getAbsolutePath()+"/ss.png/";
	        Bitmap bitmap=BitmapFactory.decodeFile(imageInSD);
	        ImageView myImageView=(ImageView)findViewById(R.id.imageView1);
	        myImageView.setImageBitmap(bitmap);

	    }

}