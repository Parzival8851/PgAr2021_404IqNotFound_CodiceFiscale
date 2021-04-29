/**
 * questa classe serve per mettere le variabile di Persona con i loro get
 */
public class Persona
{
    /**
     * stringa nome
     */
    private String nome;
    /**
     * stringa cognome
     */
    private String cognome;
    /**
     * stringa data di nascita
     */
    private String dataNascita;
    /**
     * stringa comune di nascita
     */
    private String comuneNascita;
    /**
     * stringa sesso
     */
    private String sesso;
    /**
     * stringa codice fiscale
     */
    private String codiceFiscale;

    /**
     *set del codice fiscale
     *  @param codiceFiscale
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * get del sesso
     * @return
     */
    public String getSesso() {
        return sesso;
    }

    /**
     * get del codice fiscale
     * @return
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * get cognome
     * @return
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * get del comune di nascita
     * @return
     */
    public String getComuneNascita() {
        return comuneNascita;
    }


    /**
     * get della data di nascita
     * @return
     */
    public String getDataNascita() {
        return dataNascita;
    }

    /**
     * get del nome
     * @return
     */
    public String getNome() {
        return nome;
    }


    /**
     *questo metodo riconosce le variebili di Persona
     * @param _nome
     * @param _cognome
     * @param _sesso
     * @param _comuneNascita
     * @param _dataNascita
     */
    public Persona(String _nome,String _cognome,String _sesso,String _comuneNascita,String _dataNascita)
    {
        this.nome=_nome;
        this.cognome=_cognome;
        this.sesso=_sesso;
        this.dataNascita=_dataNascita;
        this.comuneNascita=_comuneNascita;
    }
}
