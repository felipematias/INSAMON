/**
 * 
 */
package insamon.insa.amerinsa;

/**
 * @author Felipe
 *Crie une liste pour les pokemons initiales
 */
public class PokInitListView {

    private String text;
    private int iconeRid;

    public PokInitListView () {
    }

    public PokInitListView (String text, int iconeRid) {
        this.text = text;
        this.iconeRid = iconeRid;
    }

    public int getIconeRid() {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid) {
        this.iconeRid = iconeRid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}