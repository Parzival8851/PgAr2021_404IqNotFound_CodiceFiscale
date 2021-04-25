import javax.management.BadAttributeValueExpException;

public class CodiceFiscale
{


    public String calcolaData (String data) {
        int anno, giorno;
        String mese;
        anno = data.charAt(2) + data.charAt(3);
        giorno = data.charAt(8) + data.charAt(9);
        if (*/è una donna */){
            giorno = giorno + 40;
        }
        if (data.charAt(5) == '0' && data.charAt(6) == '1') {
            mese = "A";
        }
        if (data.charAt(5) == '0' && data.charAt(6) == '2') {
            mese = "B";
        }
        if (data.charAt(5) == '0' && data.charAt(6) == '3') {
            mese = "C";
        }
        if (data.charAt(5) == '0' && data.charAt(6) == '4') {
            mese = "D";
        }
        if (data.charAt(5) == '0' && data.charAt(6) == '5') {
            mese = "E";
        }
        if (data.charAt(5) == '0' && data.charAt(6) == '6') {
            mese = "H";
        }
        if (data.charAt(5) == '0' && data.charAt(6) == '7') {
            mese = "L";
        }
        if (data.charAt(5) == '0' && data.charAt(6) == '8') {
            mese = "M";
        }
        if (data.charAt(5) == '0' && data.charAt(6) == '9') {
            mese = "P";
        }
        if (data.charAt(5) == '1' && data.charAt(6) == '0') {
            mese = "R";
        }
        if (data.charAt(5) == '1' && data.charAt(6) == '1') {
            mese = "S";
        }
        if (data.charAt(5) == '1' && data.charAt(6) == '2') {
            mese = "T";
        }

        return anno+mese+giorno;
    }









   public String calcolaCognome (String cognome){
    char cognome, carattere();
    for(int i=0;i < cognome.lenght( );i++){
        if(! carattere='A'and carattere='E' and carattere='I' and carattere='O' and carattere='U'){
            carattere(i) = cognome.charAt(i);
            int conta++;
        }
        if (conta==3)
            break;
    }
    if ()



   }
}
