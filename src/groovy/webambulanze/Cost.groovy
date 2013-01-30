package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 11-1-13
 * Time: 09:14
 */
public interface Cost {

    //--sigle dei livelli di authority per la security
    public static String ROLE_PROG = 'ROLE_programmatore'
    public static String ROLE_CUSTODE = 'ROLE_custode'
    public static String ROLE_ADMIN = 'ROLE_admin'
    public static String ROLE_MILITE = 'ROLE_milite'
    public static String ROLE_OSPITE = 'ROLE_ospite'

    //--alcune sigle di croci per operazioni selettive
    public static String CROCE_ALGOS = 'ALGOS'
    public static String CROCE_PUBBLICA = 'PAVT'
    public static String CROCE_ROSSA = 'CRF'

    //--codifica delle preferenze
    public static String PREF_mostraSoloMilitiFunzione = 'mostraSoloMilitiFunzione'
    public static String PREF_mostraMilitiFunzioneAndAltri = 'mostraMilitiFunzioneAndAltri'
    public static String PREF_militePuoInserireAltri = 'militePuoInserireAltri'
    public static String PREF_militePuoModificareAltri = 'militePuoModificareAltri'
    public static String PREF_militePuoCancellareAltri = 'militePuoCancellareAltri'

    //--spazio vuoto
    public static String SPAZIO_10 = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
    public static String SPAZIO_20 = SPAZIO_10 + SPAZIO_10
    public static String SPAZIO_30 = SPAZIO_20 + SPAZIO_10

    //--identificatore di turni
    public static String EXTRA = 'extra'
} // fine della interfaccia
