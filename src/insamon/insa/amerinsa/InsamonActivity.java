package insamon.insa.amerinsa;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InsamonActivity extends Activity {
    /** Called when the activity is first created. */
	
    
    	MediaPlayer mp;
    
        @Override
		public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.main);
          Button start=(Button)findViewById(R.id.start);
          
          
          start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
        
                Intent myintent2 = new Intent(InsamonActivity.this,InitActivity.class);
                startActivity(myintent2);

            }
        });
     }     
		
}