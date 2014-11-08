package insamon.insa.amerinsa;

import insamon.insa.amerinsa.pokemon.Pokemon;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Felipe
 *
 */

public class MonPokemon extends Activity {

	/*
	 * attributs
	 */
	private Pokemon pokemonDresseur; //Le pokemon auquel on voit les caracteristiques
	private TextView nomPokemonDresseur;
	private TextView expPoke;
	private TextView viePokemon;
	private TextView pointsAttaquePokemon;
	private TextView levelPokemon;
	private TextView pointsDefensePokemon;
	private TextView enigmes;
	private Button goMap;
	private ImageView imgView;
	private Bitmap p;
	private int x;
	private int y;
	private Enigme enigme;
	private ArrayList<Integer> coord;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);   
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, //pour faire l'ecran fullscreen
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//pour laisser l'écran toujours horizontal
	    setContentView(R.layout.activity_mon_pokemon);
	    
	    nomPokemonDresseur= (TextView)findViewById(R.id.monPokemonNom);
	    expPoke= (TextView)findViewById(R.id.expPoke);
	    viePokemon= (TextView)findViewById(R.id.viePokemon);
	    pointsAttaquePokemon= (TextView)findViewById(R.id.pointsAttaquePokemon);
	    levelPokemon= (TextView)findViewById(R.id.levelPokemon);
	    pointsDefensePokemon=(TextView)findViewById(R.id.pointsDefensePokemon);
	    enigmes=(TextView)findViewById(R.id.enigmes);
	    imgView= (ImageView)findViewById(R.id.imagePokemon);

	    recupererIntent();
	    setNoms();
	    setImage();
	    
	    
	    //Verifie si le donjon est débloqué pour afficher un pettit toast
	    if((coord.size()/2)>=20){
	    	Toast.makeText(getApplicationContext(), "Le donjon est débloqué!!", Toast.LENGTH_SHORT).show();
	    }
	    
	    //Bouton pour retourner dans la map
	    goMap=(Button)findViewById(R.id.goMap);
	    goMap.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {        	
            	Intent myintent2 = new Intent(MonPokemon.this,MapActivity.class);
            	remplirIntent(myintent2);
            	Toast.makeText(getApplicationContext(), "Tu retournes dans la map!", Toast.LENGTH_SHORT).show();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mon_pokemon, menu);
		return true;
	}
	

	
	/**
	 * Cette méthode sert à mettre les caractéristiques du pokemon dans les TextView, et charger les Strings personalisés
	 */

	public void setNoms(){
		Resources res = getResources();
		String setViePoke = res.getString(R.string.setViePoke,""+ pokemonDresseur.getPdv(),""+pokemonDresseur.getPointsDeVieMax());
		String setLvlPoke = res.getString(R.string.setLvlPoke, ""+pokemonDresseur.getLvl());
		String attaquePokemon = res.getString(R.string.attaquePokemon,""+ pokemonDresseur.getPAttaque());
		String setExpPoke = res.getString(R.string.setExpPoke,""+ pokemonDresseur.getpExperience());
		String setDefPoke = res.getString(R.string.defPokemon,""+ pokemonDresseur.getPDefense());
		String setNbEnig = res.getString(R.string.enig,""+ (coord.size()/2));
		
		viePokemon.setText(setViePoke);
		expPoke.setText(setExpPoke);
		pointsAttaquePokemon.setText(attaquePokemon);
		levelPokemon.setText(setLvlPoke);
		nomPokemonDresseur.setText(pokemonDresseur.getNomPokemon());
		pointsDefensePokemon.setText(setDefPoke);
		enigmes.setText(setNbEnig);
	}
	
	/**
	 * Cette méthode sert a charger l'image du pokemon dresseur et afficher dans l'écran
	 */
	public void setImage(){
		 p=pokemonDresseur.chargerImage(getApplicationContext().getResources());
		 Drawable d = new BitmapDrawable(getApplicationContext().getResources(),p);
		 imgView.setImageDrawable(d);
	}
	
	
	/**
	 * récupère les attributs présents dans l'intent et initialise les attributs avec.
	 */
	public void recupererIntent(){
		
		Intent i = getIntent();
		pokemonDresseur= (Pokemon)i.getSerializableExtra("pokeDresseur");
		System.out.println("intent recupere");
		x=(Integer) i.getSerializableExtra("x");
		y=(Integer) i.getSerializableExtra("y");
		System.out.println(""+x+"    "+y);	
		System.out.println(pokemonDresseur.getNomPokemon());
		enigme=(Enigme)i.getSerializableExtra("enigme");
		coord=(ArrayList<Integer>)i.getSerializableExtra("coord");
	}
	
	
	/**
	 * remplit l'intent avec les attributs afin de les transmettre à la prochaine intent
	 * @param myintent2 l'intent dans laquelle on charge les attributs
	 */
	public void remplirIntent(Intent myintent2){
    	//rebalance les coordonnées.
    	myintent2.putExtra("x",x);
    	myintent2.putExtra("y",y);
    	System.out.println("MonPokActivity: x  y"+x+"    "+y);
    	myintent2.putExtra("pokeDresseur", pokemonDresseur);
    	enigme.setSolved(false);
    	myintent2.putExtra("enigme",enigme); 
    	myintent2.putExtra("coord", coord);
    	startActivity(myintent2);
		
	}
}
