import Exceptions.LePseudoExisteDéjà;
import Utilisateur.Jouer;
import Utilisateur.NvlPersonnage;

public class Main {
    public static void main(String[] argc) throws LePseudoExisteDéjà {

        NvlPersonnage maelia = new NvlPersonnage("Maelia ", "Christi", "PtitChat", "Chérie_12");
        NvlPersonnage maeli = new NvlPersonnage("Maeli ", "Christi", "PtitChat", "Chérie_12");
        NvlPersonnage mae = new NvlPersonnage("Maelia ", "Christi", "PtitChat", "Chérie_12");
        NvlPersonnage ma= new NvlPersonnage("Maeli ", "Christi", "PtitChat", "Chérie_12");

        Jouer e=new Jouer();
        e.don();
    }
}
