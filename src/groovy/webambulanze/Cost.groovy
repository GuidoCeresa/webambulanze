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
    public static String CROCE_DEMO = 'DEMO'
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

    //--sigla e password del programmatore
    public static String PROG_NICK = '---'
    public static String PROG_PASS = 'fulvia'

    //--sigla e password per accesso libero alla croce demo
    public static String DEMO_OSPITE = 'ospite'
    public static String DEMO_PASSWORD = ''

    //--identificatore delle colonne per le statistiche
    public static String CAMPO_TURNI = 'Turni'
    public static String CAMPO_ORE = 'Ore'
} // fine della interfaccia
