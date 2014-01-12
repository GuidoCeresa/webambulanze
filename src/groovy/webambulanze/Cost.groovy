package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 11-1-13
 * Time: 09:14
 */
public interface Cost {

    //--sigle dei livelli di authority per la security
    public static String ROLE_PROG = 'ROLE_prog'
    public static String ROLE_CUSTODE = 'ROLE_custode'
    public static String ROLE_ADMIN = 'ROLE_admin'
    public static String ROLE_MILITE = 'ROLE_milite'
    public static String ROLE_OSPITE = 'ROLE_ospite'

    //--anni di utilizzo del programma per le statistiche
    //--il 2012 è farlocco e serve per dare più credibilità al programma
    public static String[] ANNI = ['2012', '2013', '2014']

    //--alcune sigle di croci per operazioni selettive
    public static String CROCE_ALGOS = 'ALGOS'
    public static String CROCE_DEMO = 'DEMO'
    public static String CROCE_PUBBLICA_CASTELLO = 'PAVT'
    public static String CROCE_ROSSA_FIDENZA = 'CRF'
    public static String CROCE_ROSSA_PONTETARO = 'CRPT'
    public static String CROCE_PUBBLICA_PIANORO = 'PAP'
    public static String GAPS_CASTELLO = 'GAPS'

    //--codifica degli attributi dei cookies
    public static String COOKIE_SIGLA_CROCE = 'siglaCroce'
    //   public static String SESSIONE_LOGIN = 'startLogin'
    //   public static String SESSIONE_START_CONTROLLER = 'startController'
    //   public static String SESSIONE_TUTTI_CONTROLLI = 'allControllers'
    //   public static String SESSIONE_QUALI_CONTROLLI = 'controlli'

    //--codifica delle preferenze
    public static boolean PREF_startLogin = 'startLogin'
    public static String PREF_startController = 'startController'
    public static boolean PREF_allControllers = 'allControllers'
    public static String PREF_controlli = 'controlli'
    public static String PREF_mostraSoloMilitiFunzione = 'mostraSoloMilitiFunzione'
    public static String PREF_mostraMilitiFunzioneAndAltri = 'mostraMilitiFunzioneAndAltri'
    public static String PREF_militePuoInserireAltri = 'militePuoInserireAltri'
    public static String PREF_militePuoModificareAltri = 'militePuoModificareAltri'
    public static String PREF_militePuoCancellareAltri = 'militePuoCancellareAltri'
    public static String PREF_isOrarioTurnoModificabileForm = 'isOrarioTurnoModificabileForm'
    public static String PREF_calcoloNotturnoStatistiche = 'isCalcoloNotturnoStatistiche'
    public static String PREF_disabilitazioneAutomaticaLogin = 'isDisabilitazioneAutomaticaLogin'
    public static String PREF_fissaLimiteMassimoSingoloTurno = 'fissaLimiteMassimoSingoloTurno'
    public static String PREF_oreMassimeSingoloTurno = 'oreMassimeSingoloTurno'
    public static String PREF_maxMinutiTrascorsiModifica = 'maxMinutiTrascorsiModifica'
    public static String PREF_minGiorniMancantiModifica = 'minGiorniMancantiModifica'
    public static String PREF_usaModuloTurni = 'usaModuloTurni'
    public static String PREF_usaModuloViaggi = 'usaModuloViaggi'
    public static String PREF_isTabelloneSecured = 'tabelloneSecured'
    public static String PREF_isTurniSecured = 'turniSecured'
    public static String PREF_mostraTabellonePartenza = 'mostraTabellonePartenza'
    public static String PREF_bloccaSoloFunzioniObbligatorie = 'bloccaSoloFunzioniObbligatorie'
    public static String PREF_militePuoCreareTurnoStandard = 'militePuoCreareTurnoStandard'
    public static String PREF_militePuoCreareTurnoExtra = 'militePuoCreareTurnoExtra'
    public static String PREF_isTurnoSettimanale = 'isTurnoSettimanale'
    public static String PREF_usaNomeCognome = 'usaNomeCognome'
    public static String PREF_usaListaMilitiViaggi = 'usaListaMilitiViaggi'
    public static String PREF_numeroServiziEffettuati = 'numeroServiziEffettuati'

    //--spazio vuoto
    public static String SPAZIO_10 = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
    public static String SPAZIO_20 = SPAZIO_10 + SPAZIO_10
    public static String SPAZIO_30 = SPAZIO_20 + SPAZIO_10

    //--identificatore di turni
    public static String EXTRA = 'extra'

    //--sigla e password del programmatore
    public static String PROG_NICK_CRF = '---'
    public static String PROG_NICK_CRPT = '--' //--i nick sono unici
    public static String PROG_NICK_DEMO = '----' //--i nick sono unici
    public static String PROG_USERNAME = 'gac'
    public static String PROG_NICK = '---'
    public static String PROG_PASS = 'fulvia'

    //--sigla e password per accesso libero alla croce demo
    public static String DEMO_OSPITE = 'ospite'
    public static String DEMO_PASSWORD_BOOT = '1' //non accetta valori vuoti nella creazione del record
    public static String DEMO_PASSWORD = ''

    //--sigla e password per accesso libero alla croce rossa ponte taro
    public static String CRPT_OSPITE = '.ospite.'
    public static String CRPT_PASSWORD_BOOT = '2' //non accetta valori vuoti nella creazione del record
    public static String CRPT_PASSWORD = ''

    //--identificatore delle colonne per le statistiche
    public static String CAMPO_TURNI = 'Turni'
    public static String CAMPO_ORE = 'Ore'

    //--identificatore delle numero di turni valido o meno per le statistiche
    public static String STATUS_VERDE = 'ok'
    public static String STATUS_ROSSO = 'rosso'

    //--identificatori di alcuni campi tra TagLib e Controller
    public static String CAMPO_RIPETIZIONE_TURNO = 'ripetizioneTurno'
    public static String CAMPO_FREQUENZA_RIPETIZIONE = 'frequenzaRipetizione'
    public static String CAMPO_NUMERO_RIPETIZIONI = 'numeroRipetizioni'
    public static ArrayList VALORI_FREQUENZA_TXT = ['ogni 7gg', 'ogni 14gg', 'ogni 21 gg', 'ogni 28gg']
    public static ArrayList VALORI_FREQUENZA_NUM = [7, 14, 21, 28]

    //--sigle dei tipi di turno in croce rossa fidenza
    public static String CRF_TIPO_TURNO_AUTOMEDICA_MATTINO = 'msa-mat'
    public static String CRF_TIPO_TURNO_AUTOMEDICA_POMERIGGIO = 'msa-pom'
    public static String CRF_TIPO_TURNO_AUTOMEDICA_NOTTE = 'msa-notte'
    public static String CRF_TIPO_TURNO_AMBULANZA_MATTINO = 'amb-mat'
    public static String CRF_TIPO_TURNO_AMBULANZA_POMERIGGIO = 'amb-pom'
    public static String CRF_TIPO_TURNO_AMBULANZA_NOTTE = 'amb-notte'
    public static String CRF_TIPO_TURNO_EXTRA = 'extra'

    //--funzioni in croce rossa pontetaro
    public static String CRPT_FUNZIONE_AUT_118 = 'aut-118'
    public static String CRPT_FUNZIONE_AUT_ORD = 'aut-ord'
    public static String CRPT_FUNZIONE_DAE = 'dae'
    public static String CRPT_FUNZIONE_SOC = 'soc'
    public static String CRPT_FUNZIONE_BAR = 'bar'
    public static String CRPT_FUNZIONE_CENTRALINO = 'cent'
    public static String CRPT_FUNZIONE_PULIZIE = 'pul'
    public static String CRPT_FUNZIONE_UFFICIO = 'uff'

    //--sigle dei tipi di turno in croce rossa pontetaro
    public static String CRPT_TIPO_TURNO_AMBULANZA_MATTINO = '118-mat'
    public static String CRPT_TIPO_TURNO_AMBULANZA_POMERIGGIO = '118-pom'
    public static String CRPT_TIPO_TURNO_AMBULANZA_NOTTE = '118-notte'
    public static String CRPT_TIPO_TURNO_DIALISI_UNO_ANDATA = 'dia-1a'
    public static String CRPT_TIPO_TURNO_DIALISI_UNO_RITORNO = 'dia-1r'
    public static String CRPT_TIPO_TURNO_DIALISI_DUE_ANDATA = 'dia-2a'
    public static String CRPT_TIPO_TURNO_DIALISI_DUE_RITORNO = 'dia-2r'
    public static String CRPT_TIPO_TURNO_ORDINARIO_OLD = 'ord'
    public static String CRPT_TIPO_TURNO_ORDINARIO_SINGOLO = 'ord-uno'
    public static String CRPT_TIPO_TURNO_ORDINARIO_DOPPIO = 'ord-due'
    public static String CRPT_TIPO_TURNO_EXTRA = 'extra'
    public static String CRPT_TIPO_TURNO_SERVIZI = 'servizi'

    //--funzioni nella croce demo
    public static String DEMO_FUNZIONE_AUT = 'aut'
    public static String DEMO_FUNZIONE_SEC = 'sec'
    public static String DEMO_FUNZIONE_TER = 'ter'
    public static String DEMO_FUNZIONE_BAR = 'bar'

    //--sigle dei tipi di turno nella demo
    public static String DEMO_TIPO_TURNO_AUTOMEDICA_MATTINO_OLD = 'mat'
    public static String DEMO_TIPO_TURNO_AUTOMEDICA_MATTINO = 'msa-mat'
    public static String DEMO_TIPO_TURNO_AUTOMEDICA_POMERIGGIO_OLD = 'pom'
    public static String DEMO_TIPO_TURNO_AUTOMEDICA_POMERIGGIO = 'msa-pom'
    public static String DEMO_TIPO_TURNO_AUTOMEDICA_NOTTE_OLD = 'notte'
    public static String DEMO_TIPO_TURNO_AUTOMEDICA_NOTTE = 'msa-notte'
    public static String DEMO_TIPO_TURNO_AMBULANZA_MATTINO = 'amb-mat'
    public static String DEMO_TIPO_TURNO_AMBULANZA_POMERIGGIO = 'amb-pom'
    public static String DEMO_TIPO_TURNO_AMBULANZA_NOTTE = 'amb-notte'
    public static String DEMO_TIPO_TURNO_EXTRA = 'extra'

    //--sigla per accesso dipendenti pubblica assistenza pianoro
    public static String PAP_NICK_DIPENDENTI = 'dipendenti'
    public static String PAP_NICK_CUSTODE = 'Piana Silvano'

    //--funzioni in pubblica assistenza pianoro
    public static String PAP_FUNZIONE_AUT = 'aut'
    public static String PAP_FUNZIONE_SOC = 'soc'
    public static String PAP_FUNZIONE_BAR = 'bar'
    public static String PAP_FUNZIONE_BAR_2 = 'bar2'

    //--sigle dei tipi di turno in pubblica assistenza pianoro
    public static String PAP_TIPO_TURNO_LUNVEN_NOTTE = 'msa-notte'
    public static String PAP_TIPO_TURNO_LUNVEN_MATTINA = 'msa-mat'
    public static String PAP_TIPO_TURNO_LUNVEN_POMERIGGIO = 'msa-pom'
    public static String PAP_TIPO_TURNO_LUNVEN_POMERIGGIOSERA = 'msa-pomsera'
    public static String PAP_TIPO_TURNO_LUNVEN_SERA = 'msa-sera'
    public static String PAP_TIPO_TURNO_SABDOM_NOTTE = 'msa2-notte'
    public static String PAP_TIPO_TURNO_SABDOM_MATTINA = 'msa2-mat'
    public static String PAP_TIPO_TURNO_SABDOM_POMERIGGIO = 'msa2-pom'
    public static String PAP_TIPO_TURNO_SABDOM_SERA = 'msa2-sera'

    //--funzioni in gaps castello
    public static String GAPS_FUNZIONE_VOLONTARIO = 'vol'
    public static String GAPS_FUNZIONE_VOLONTARIO_2 = 'vol2'
    public static String GAPS_FUNZIONE_TIROCINANTE = 'tir'
    public static String GAPS_FUNZIONE_TUTOR = 'tut'

    //--funzioni in gaps castello
    public static String GAPS_TIPO_TURNO_MATTINA = 'mat'
    public static String GAPS_TIPO_TURNO_PRANZO = 'pra'
    public static String GAPS_TIPO_TURNO_POMERIGGIO = 'pom'
} // fine della interfaccia
