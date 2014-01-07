package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 9-7-13
 * Time: 10:44
 */
class LibHtml {

    private static String CAPO = '\n'
    private static String TAB = '\t'
    private static int POS_TAB_DEFAULT = 3

    private static boolean USA_RIGHE_MULTIPLE = false
    private static boolean ASTERISCO_SINISTRA = true

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo
     *
     * @param nome del campo della Domain class
     * @return stringa html
     */
    public static String field(String nomeCampo) {
        return field(Field.testoEdit, nomeCampo)
    }// fine del metodo

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo
     *
     * @param campo dalla Enumeration
     * @param nome del campo della Domain class
     * @return stringa html
     */
    public static String field(Field campo, String nomeCampo) {
        return field(campo, nomeCampo, '', nomeCampo, POS_TAB_DEFAULT)
    }// fine del metodo

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo
     *
     * Il campo può essere di varie tipologie
     * Il campo è solo mostrato
     * Il campo può essere un link oppure solo il valore
     *
     * @param etichetta della label
     * @param value da mostrare
     *
     * @return stringa html
     */
    public static String field(String etichetta, def value) {
        return field(Field.testo, etichetta, value, POS_TAB_DEFAULT)
    }// fine del metodo

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo
     *
     * Il campo può essere di varie tipologie
     * Il campo è solo mostrato
     * Il campo può essere un link oppure solo il valore
     *
     * @param etichetta della label
     * @param value da mostrare
     * @param posizione del tab iniziale della riga
     *
     * @return stringa html
     */
    public static String field(String etichetta, def value, int pos) {
        return field(Field.testo, etichetta, value, pos)
    }// fine del metodo

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo
     *
     * Il campo può essere di varie tipologie
     * Il campo è solo mostrato
     * Il campo può essere un link oppure solo il valore
     *
     * @param campo dalla Enumeration
     * @param etichetta della label
     * @param value da mostrare
     *
     * @return stringa html
     */
    public static String field(Field campo, String etichetta, def value) {
        return field(campo, etichetta, value, POS_TAB_DEFAULT)
    }// fine del metodo

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo
     *
     * Il campo può essere di varie tipologie
     * Il campo è solo mostrato
     * Il campo può essere un link oppure solo il valore
     *
     * @param campo dalla Enumeration
     * @param etichetta della label
     * @param value da mostrare
     * @param posizione del tab iniziale della riga
     *
     * @return stringa html
     */
    public static String field(Field campo, String etichetta, def value, int pos) {
        return field(campo, etichetta, value, '', pos)
    }// fine del metodo

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo
     *
     * Il campo può essere di varie tipologie
     * Il campo è solo mostrato
     * Il campo può essere un link oppure solo il valore
     *
     * @param campo dalla Enumeration
     * @param etichetta della label
     * @param value da mostrare
     * @param linkNome (eventuale) oppure nome del campo della Domain class
     *
     * @return stringa html
     */
    public static String field(Field campo, String etichetta, def value, String linkNome) {
        return field(campo, etichetta, value, linkNome, POS_TAB_DEFAULT)
    }// fine del metodo

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo
     *
     * Il campo può essere di varie tipologie
     * Il campo è solo mostrato
     * Il campo può essere un link oppure solo il valore
     *
     * @param campo dalla Enumeration
     * @param etichetta della label
     * @param value da mostrare
     * @param linkNome (eventuale) oppure nome del campo della Domain class
     * @param posizione del tab iniziale della riga
     *
     * @return stringa html
     */
    public static String field(Field campo, String etichetta, def value, String linkNome, int pos) {
        String testoHtml = ''
        boolean richiesto = false
        boolean modificabile = false
        boolean linkato

        if (campo) {
            richiesto = campo.richiesto
            modificabile = campo.modificabile
            linkato = campo.linkato
        }// fine del blocco if

        testoHtml += nCapo(1)
        testoHtml += nTab(pos)
        testoHtml += '<div class="fieldcontain">'
        testoHtml += nCapo(1)
        testoHtml += label(etichetta, richiesto, pos + 1)

        testoHtml += nCapo(1)
        if (linkato) {
            testoHtml += link(linkNome, value, pos + 1)
        } else {
            if (modificabile) {
                if (campo == Field.oraMin) {
                    testoHtml += setOreMinuti(linkNome, value, pos + 1)
                } else {
                    testoHtml += input(value, linkNome, richiesto, pos + 1)
                }// fine del blocco if-else
            } else {
                testoHtml += nTab(pos + 1)
                testoHtml += value
            }// fine del blocco if-else
        }// fine del blocco if-else

        testoHtml += nCapo(1)
        testoHtml += nTab(pos)
        testoHtml += '</div>'
        testoHtml += nCapo(1)

        return testoHtml
    }// fine del metodo

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo selettore di valori
     *
     * @param etichetta della label
     * @param nome del campo della Domain class
     * @param listaValori da mostrare
     * @param valoreSelezionato
     * @param richiesto
     *
     * @return stringa html
     */
    public static String fieldLista(String etichetta, String nomeCampo, ArrayList listaValori, def valoreSelezionato, boolean richiesto) {
        String testoHtml = ''

        testoHtml += nCapo(1)
        testoHtml += nTab(POS_TAB_DEFAULT)
        testoHtml += '<div class="fieldcontain">'
        testoHtml += nCapo(1)
        testoHtml += label(etichetta, richiesto, POS_TAB_DEFAULT + 1)

        testoHtml += nCapo(1)
        testoHtml += lista(nomeCampo, listaValori, valoreSelezionato, richiesto, POS_TAB_DEFAULT + 1)

        testoHtml += nCapo(1)
        testoHtml += nTab(POS_TAB_DEFAULT)
        testoHtml += '</div>'
        testoHtml += nCapo(1)

        return testoHtml
    }// fine del metodo

    /**
     * Costruisce un blocco (di solito una riga) composto da label e campo
     *
     * Il campo può essere di varie tipologie
     * Il campo può essere editabile, oppure solo mostrato
     * Se mostrato, il campo può essere un link oppure solo il valore
     *
     *
     * @param etichetta della label
     * @param richiesto per visualizzare l'asterisco
     * @return stringa html
     */
    public static String field(String etichetta, boolean richiesto, int pos) {
        String testoHtml = ''

        testoHtml += '<div class="fieldcontain">'
        testoHtml += label(etichetta, richiesto, pos)
        testoHtml += '</div>'

        return testoHtml
    }// fine del metodo

    /**
     * Costruisce una label associata ad un campo
     *
     * Flag per mostrare l'asterisco del campo obbligatorio
     *
     * @param etichetta della label
     * @param richiesto per visualizzare l'asterisco
     * @return stringa html
     */
    private static String label(String etichetta, boolean richiesto, int pos) {
        String testoHtml = ''
        boolean usaRigheMultiple = USA_RIGHE_MULTIPLE

        testoHtml += nTab(pos)
        testoHtml += "<label for=\"${etichetta}\">"
        if (usaRigheMultiple) {
            testoHtml += nCapo(1)
            testoHtml += nTab(pos + 1)
        }// fine del blocco if

        if (richiesto && ASTERISCO_SINISTRA) {
            if (usaRigheMultiple) {
                testoHtml += nCapo(1)
            }// fine del blocco if
            testoHtml += '<span class="required-indicator">*&nbsp;</span>'
        }// fine del blocco if
        testoHtml += etichetta
        if (richiesto && !ASTERISCO_SINISTRA) {
            if (usaRigheMultiple) {
                testoHtml += nCapo(1)
            }// fine del blocco if
            testoHtml += '<span class="required-indicator">&nbsp;*</span>'
        }// fine del blocco if
        if (usaRigheMultiple) {
            testoHtml += nCapo(1)
            testoHtml += nTab(pos)
        }// fine del blocco if
        testoHtml += '</label>'

        return testoHtml
    }// fine del metodo

    private static String input(def value, String nomeCampo, boolean richiesto, int pos) {
        String testoHtml = ''

        testoHtml += nTab(pos)
        if (!richiesto) {
            testoHtml += "<input type=\"number\" name=\"${nomeCampo}\" value=\"${value}\" id=\"${nomeCampo}\" />"
        } else {
            testoHtml += "<input type=\"number\" name=\"${nomeCampo}\" value=\"${value}\" required=\"\" id=\"${nomeCampo}\" />"
        }// fine del blocco if-else

//        testoHtml +="<g:field name=\"${nomeCampo}\" type=\"number\" value=\"${value}\" required=\"\"/>"
        return testoHtml
    }// fine del metodo


    private static String link(String testoLink, def value, int pos) {
        String testoHtml = ''

        testoHtml += nTab(pos)
        testoHtml += "<a href=\"/webambulanze/${testoLink}\">${value}</a>"

        return testoHtml
    }// fine del metodo

    //--va a capo n volte
    private static String nCapo(int pos) {
        String testoHtml = ''

        for (int k = 0; k < pos; k++) {
            testoHtml += CAPO
        } // fine del ciclo for

        return testoHtml
    }// fine del metodo

    //--tabulatore per n volte
    private static String nTab(int pos) {
        String testoHtml = ''

        for (int k = 0; k < pos; k++) {
            testoHtml += TAB
        } // fine del ciclo for

        return testoHtml
    }// fine del metodo

    /**
     * Costruisce blocco con campo selettore delle ore e campo selettore dei minuti
     *
     * @param nomeCampo
     * @return stringa html
     */
    public static String setOreMinuti(String nomeCampo) {
        return setOreMinuti(nomeCampo, null)
    }// fine del metodo

    /**
     * Costruisce blocco con campo selettore delle ore e campo selettore dei minuti
     *
     * @param nomeCampo
     * @param value da mostrare per le ore
     * @param value da mostrare per i minuti
     * @return stringa html
     */
    public static String setOreMinuti(String nomeCampo, def value, int pos) {
        String testoHtml = ''
        def valueOre = ''
        def valueMinuti = ''

        if (value) {
            valueOre = Lib.getNumOra(value)
            valueMinuti = Lib.getNumMinuti(value)
        }// fine del blocco if

        testoHtml += setOre(nomeCampo, valueOre, pos)
        testoHtml += nCapo(1)
        testoHtml += nTab(pos)
        testoHtml += ' : '
        testoHtml += setMinuti(nomeCampo, valueMinuti, pos)

        return testoHtml
    }// fine del metodo

    /**
     * Costruisce un campo selettore delle ore
     *
     * @param nomeCampo
     * @return stringa html
     */
    public static String setOre(String nomeCampo) {
        return setOre(nomeCampo, '')
    }// fine del metodo

    /**
     * Costruisce un campo selettore dei minuti
     *
     * @param nomeCampo
     * @return stringa html
     */
    public static String setMinuti(String nomeCampo) {
        return setMinuti(nomeCampo, '')
    }// fine del metodo

    /**
     * Costruisce un campo selettore delle ore
     *
     * @param nomeCampo
     * @param value da mostrare
     * @return stringa html
     */
    public static String setOre(String nomeCampo, def value, int pos) {
        return setOreMinutiBase(nomeCampo, value, pos, 'Ore', 24)
    }// fine del metodo

    /**
     * Costruisce un campo selettore dei minuti
     *
     * @param nomeCampo
     * @param value da mostrare
     * @return stringa html
     */
    public static String setMinuti(String nomeCampo, def value, int pos) {
        return setOreMinutiBase(nomeCampo, value, pos, 'Minuti', 60)
    }// fine del metodo

    /**
     * Costruisce un campo selettore
     *
     * @param nomeCampo
     * @param value da mostrare
     * @param posizione del tab iniziale della riga
     * @param tag (ore/minuti)
     * @param max numero della selezione (24/60)
     * @return stringa html
     */
    private static String setOreMinutiBase(String nomeCampo, def value, int pos, String tag, int max) {
        String testoHtml = ''
        String tagSelected = 'selected="selected"'
        boolean isSelected

        testoHtml += CAPO
        testoHtml += nTab(pos)
        testoHtml += "<select name=\"${nomeCampo}${tag}\" id=\"\${nomeCampo}${tag}\">"
        testoHtml += CAPO

        for (int k = 0; k < max; k++) {
            isSelected = (k == value)
            testoHtml += nTab(pos + 1)
            if (isSelected) {
                testoHtml += "<option value=\"${k}\" ${tagSelected}>${k}</option>"
            } else {
                testoHtml += "<option value=\"${k}\">${k}</option>"
            }// fine del blocco if-else
            testoHtml += CAPO
        } // fine del ciclo for
        testoHtml += nTab(pos)
        testoHtml += "</select>"

        return testoHtml
    }// fine del metodo

    private static String lista(String nomeCampo, ArrayList lista, def valoreSelezionato, boolean richiesto, int pos) {
        String testoHtml = ''
        String tagSelected = 'selected="selected"'
        boolean isSelected
        def key  =''

        testoHtml += CAPO
        testoHtml += nTab(pos)
        testoHtml += "<select name=\"${nomeCampo}\" id=\"\${nomeCampo}\">"
        testoHtml += CAPO

        if (!richiesto) {
            testoHtml += "<option value=\"\"></option>"
        }// fine del blocco if

        lista?.each {
            isSelected = (it.toString().equals(valoreSelezionato.toString()))
            testoHtml += nTab(pos + 1)
            try { // prova ad eseguire il codice
                key = it.id
            } catch (Exception unErrore) { // intercetta l'errore
                key = it
            }// fine del blocco try-catch
            if (isSelected) {
                testoHtml += "<option value=\"${key}\" ${tagSelected}>${it}</option>"
            } else {
                testoHtml += "<option value=\"${key}\">${it}</option>"
            }// fine del blocco if-else
            testoHtml += CAPO
        } // fine del ciclo each

        testoHtml += nTab(pos)
        testoHtml += "</select>"

        return testoHtml
    }// fine del metodo

}// fine della classe statica
