package insamon.insa.amerinsa;

import insamon.insa.amerinsa.pokemon.Bulbasaur;
import insamon.insa.amerinsa.pokemon.Charmander;
import insamon.insa.amerinsa.pokemon.Pikachu;
import insamon.insa.amerinsa.pokemon.Pokemon;
import insamon.insa.amerinsa.pokemon.Squirtle;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PokInitActivity extends Activity implements OnItemClickListener{

	private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<PokInitListView> itens;
    private static Personnage personnage=new Personnage("Ash", 64*9, 64*5); // il faudrait initialis� le perso non?
    private Pokemon pokemonDresseur;
    private Enigme enigme=new Enigme("","","","","");
    private int x=47;
    private int y=29;
    private ArrayList<Integer> coord =new ArrayList <Integer>(); // ce nombre donne le nombre d'�nigmes r�solues, et les valeurs qu'on ajoutera � cette liste seront les cooord des �nigmes r�solues
 //   private int compteur =0;// initialisation du compteur pour le donjon.
    
    
    /* il faut les instancier ici car ce seront les param�tres par d�faut de la construction de la carte
 	 * et sinon il y a un bug dans l'instanciation de Map Activity
    */
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pok_init);
		
		
		//on initialise la liste directement
		//coord.add(0);
		
		listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        createListView();
      
		
	}
	
	private void createListView() {
        //La liste qui va remplir le ListView
        itens = new ArrayList<PokInitListView>();
 
        PokInitListView item1 = new PokInitListView("Charmander", R.drawable.charmander_ico);
        PokInitListView item2 = new PokInitListView("Bulbasaur", R.drawable.bulbasaur_ico);
        PokInitListView item3 = new PokInitListView("Squirtle", R.drawable.squirtle_ico);
        PokInitListView item4 = new PokInitListView("Pikachu", R.drawable.pikachu_ico);
 
        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        itens.add(item4);
 
        //Crie l'adapter
        adapterListView = new AdapterListView(this, itens);
        listView.setOnItemClickListener(this);
        //Défine l'Adapter
        listView.setAdapter(adapterListView);
        
        listView.setCacheColorHint(Color.TRANSPARENT);
    }
	
	
	 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	        //Pren l'item sélectionné
	        PokInitListView item = adapterListView.getItem(arg2);
	      
	        //On crie un Toast qui affiche le pokemon selectionné
	        Toast.makeText(this, "Tu as selectionné: " + item.getText(), Toast.LENGTH_SHORT).show();
	        
	        //On stocke le pokemon choisi ds l'attribut correspondant.
	        if(item.getText()=="Charmander"){pokemonDresseur=new Charmander();}
	        else if(item.getText()=="Bulbasaur"){pokemonDresseur=new Bulbasaur();}
	        else if(item.getText()=="Pikachu"){pokemonDresseur=new Pikachu();}
	        else if(item.getText()=="Squirtle"){pokemonDresseur=new Squirtle();}
	        
	        // il faut stocker le pokemon choisi dans l'attribut pokemonDresseur.
	        //  pokemonDresseur=
	        //On va dans la map
	        //MapJeu jeu = new MapJeu(MapActivity.getContext(), personnage); //if faut verifier comment marche le context
	        Intent myintent2 = new Intent(PokInitActivity.this,MapActivity.class);
	       // myintent2.putExtra(getString(R.string.extraMessage), item.getText());
	        myintent2.putExtra("pokeDresseur",pokemonDresseur);
	        myintent2.putExtra("perso", personnage);
	        myintent2.putExtra("x",x);
        	myintent2.putExtra("y",y);
        	myintent2.putExtra("enigme", enigme);
        	myintent2.putExtra("coord", coord); 
        //	myintent2.putExtra("compteur",compteur);
            startActivity(myintent2);
            Toast.makeText(this, "Bon Jeu! (:", Toast.LENGTH_LONG).show();
	    }
	 
	 

	public static Personnage getPersonnage() {
		return personnage;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pok_init, menu);
		return true;
	}

}
