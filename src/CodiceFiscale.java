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
        mese=data.charAt(5) + data.charAt(6);
        mese=calcolaMese(mese);
        return anno+mese+giorno;
    }
    private String calcolaMese(String mese){
        if(mese.charAt(0)=="0"){
            if (mese=="01") {
                mese = "A";
            }
            if (mese=="02") {
                mese = "B";
            }
            if (mese=="03") {
                mese = "C";
            }
            if (mese=="04") {
                mese = "D";
            }
            if (mese=="05") {
                mese = "E";
            }
            if (mese=="06") {
                mese = "H";
            }
            if (mese=="07") {
                mese = "L";
            }
            if (mese=="08") {
                mese = "M";
            }
            if (mese=="09") {
                mese = "P";
            }
        }
        else{
            if (mese=="10") {
                mese = "R";
            }
            if (mese=="11") {
                mese = "S";
            }
            if (mese=="12") {
                mese = "T";
            }
        }
        return mese;
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
