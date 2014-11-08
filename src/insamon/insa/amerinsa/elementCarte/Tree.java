package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class Tree extends Elements  {
		
	
	public Tree() {
		super("Tree","Tree");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.bigtree);
		return b;
	}


}
