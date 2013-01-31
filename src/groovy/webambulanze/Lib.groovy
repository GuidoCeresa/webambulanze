package webambulanze

import java.sql.Timestamp

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 28-12-12
 * Time: 09:01
 */
class Lib {
    private static String aCapo = '\n'
    private static String tab1 = '  '
    private static String tab2 = tab1 + tab1
    private static String tab3 = tab2 + tab1

    private static String colBack = 'ff4444'
    private static String colText = '444444'

    static String getTitoloPagina(String testoIn) {
        String testoOut = ''
        int alt = 40
        testoOut += '<div style="padding:10px;font-size:30px;height:'
        testoOut += alt
        testoOut += 'px;background:#'
        testoOut += colBack
        testoOut += ';color:#'
        testoOut += colText
        testoOut += '">'
//        testoOut += '<img src="file:///Macintosh HD/Users/Gac/Documents/IdeaProjects/webambulanze/web-app/images/skin/database_add.png" width="40" height="40" alt="CRI"/>'
//        testoOut += '<img src="/Users/Gac/Desktop/Immagini/FacciaPositiva.png" width="40" height="40" alt="CRI"/>'

        testoOut += testoIn
        testoOut += '</div>'

        testoOut = '<div class="titolopagina">Pippo</div>'
        return testoOut
    }// fine del metodo

    static String test(String testoIn) {
        String testo = ''

        testo += '<table width="90%">'
        testo += '<thead>'
        testo += '<tr>'
//        testo += '<th align="left" valign="middle" color="#444444" bgcolor="#ff4444">'
        testo += '<th bgcolor=#ff4444>'
//        testo+='<span class="Stile1 Stile2">'
        testo += testoIn
//        testo+='</span>'
        testo += '</th>'
//        testo+='<th width="150; align="left" valign="middle" bordercolor="#00CCCC" bgcolor="#ff4444">'
//        testo+='Mario Draghi'
        testo += '</tr>'
        testo += '</thead>'
//        testo+='<th width="150; align="left" valign="middle" bordercolor="#00CCCC" bgcolor="#ff4444">'
//        testo+='is logged as...'
//        testo+='</th>'
//        testo+='</tr>'
        testo += '</table>'

        return testo
    }// fine del metodo

    static String tagTable(String testoIn) {
        return tagTable(testoIn, null)
    }// fine del metodo

    static String tagTable(String testoIn, Aspetto cella) {
        if (cella) {
            return tag('table', testoIn, cella.toString(), '', 0)
        } else {
            return tag('table', testoIn, '', '', 0)
        }// fine del blocco if-else
    }// fine del metodo

    static String getCaption(String testoIn) {
        String testoOut = tab1

        testoOut += '<caption>'
        testoOut += aCapo
        testoOut += tab2
        testoOut += '<div align="center">'
        testoOut += '<b>'
        testoOut += testoIn
        testoOut += '</b>'
        testoOut += '</div>'
        testoOut += aCapo
        testoOut += tab1
        testoOut += '</caption>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    static String tagCaption(String testoIn) {
        return tagCaption(testoIn, null)
    }// fine del metodo

    static String tagCaption(String testoIn, Aspetto cella) {
        if (cella) {
            return tag('caption', testoIn, cella.toString(), '', 0)
        } else {
            return tag('caption', testoIn, '', '', 0)
        }// fine del blocco if-else
    }// fine del metodo

    static String tagHead(String testoIn) {
        String testoOut = ''

        testoOut += '<thead>'
        testoOut += aCapo
        testoOut += testoIn.trim()
        testoOut += aCapo
        testoOut += '</thead>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    static String tagRiga(String testoIn) {
        String testoOut = ''

        testoOut += '<tr>'
        testoOut += aCapo
        testoOut += testoIn.trim()
        testoOut += aCapo
        testoOut += '</tr>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    static String getBody(String testoIn) {
        String testoOut = ''

        testoOut += '<tbody>'
        testoOut += aCapo
        testoOut += testoIn.trim()
        testoOut += aCapo
        testoOut += '</tbody>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    static String getBold(String testoIn) {
        String testoOut = ''

        testoOut += '<bold>'
        testoOut += testoIn
        testoOut += '</bold>'

        return testoOut
    }// fine del metodo

    static String getTitoloBase(String testoIn, String colore, int span) {
        String testoOut = tab3

        testoOut += '<th'
        if (span) {
            testoOut += ' colspan="'
            testoOut += span
            testoOut += '"'
        }// fine del blocco if
        if (colore) {
            testoOut += ' bgcolor=#'
            testoOut += colore
        }// fine del blocco if
        testoOut += '>'

        if (colore) {
            testoOut += '<FONT FACE="Geneva, Arial" SIZE=2>'
        }// fine del blocco if

        testoOut += testoIn
        testoOut += '</th>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    public static String getCellaBase(String testoIn, String colore, int span) {
        String testoOut = tab3

        testoOut += '<td class="tab"'
        if (span) {
            testoOut += ' rowspan="'
            testoOut += span
            testoOut += '"'
        }// fine del blocco if-else
        if (colore) {
            testoOut += ' bgcolor=#'
            testoOut += colore
        }// fine del blocco if-else
        testoOut += '>'

        testoOut += testoIn
        testoOut += '</td>'
        testoOut += aCapo

        return testoOut
    }// fine del metodo

    static String getTitolo(String testoIn, String colore) {
        return getTitoloBase(testoIn, colore, 0)
    }// fine del metodo

    static String getTitolo(String testoIn) {
        return getTitolo(testoIn, '')
    }// fine del metodo

    static String getTitoloTabellaSorted(String app, String cont, String sort, String order, String title) {
        return "<th class=\"sortable\"><a href=\"/${app}/${cont}/list?sort=${sort}&amp;order=${order}\">${title}</a></th>"
    }// fine del metodo

    static String getTitoloTabellaNotSorted(String app, String cont, String sort, String order, String title) {
        String testo

        if (order && order.equals('desc')) {
            testo = "<th class=\"sortable sorted desc\"><a href=\"/${app}/${cont}/list?sort=${sort}&amp;order=${order}\">${title}</a></th>"
        } else {
            testo = "<th class=\"sortable sorted asc\"><a href=\"/${app}/${cont}/list?sort=${sort}&amp;order=${order}\">${title}</a></th>"
        }// fine del blocco if-else

        return testo
//        return "<th class=\"sortable\"><a href=\"/${app}/${cont}/list?sort=${sort}&amp;order=${order}\">${title}</a></th>"
    }// fine del metodo

    static String getCampoTabellaLong(String app, String cont, long id, def value) {
        String testo = ''

        testo = "<a href=\"/${app}/${cont}/show/${id}\">${value}</a>"

        return Lib.tagCella(testo)
    }// fine del metodo

    static String getCampoTabellaBooleano(boolean value) {
        String testo = ''

        if (value) {
            testo = "<input type=\"checkbox\" checked>"
        } else {
            testo = "<input type=\"checkbox\" disabled>"
        }// fine del blocco if-else

        return Lib.tagCella(testo)
    }// fine del metodo

    static String getCampoTabellaStringa(String app, String cont, long id, def value) {
        String testo = ''

        testo = "<a href=\"/${app}/${cont}/show/${id}\">${value}</a>"

        return Lib.tagCella(testo)
    }// fine del metodo

    static String getCampoTabellaData(String app, String cont, long id, def value) {
        String testo
        String dataTxt = Lib.presentaDataMese(value)

        testo = "<a href=\"/${app}/${cont}/show/${id}\">${dataTxt}</a>"
        testo = Lib.tagCella(testo)

        return testo
    }// fine del metodo

    static String getCampoTabellaTime(String app, String cont, long id, def value) {
        String testo
        String timeTxt = value
        timeTxt = timeTxt.substring(0, 16)

        if (isData(value)) {
            testo = getCampoTabellaData(app, cont, id, value)
        } else {
            testo = "<a href=\"/${app}/${cont}/show/${id}\">${timeTxt}</a>"
            testo = Lib.tagCella(testo)
        }// fine del blocco if-else

        return testo
    }// fine del metodo

    static boolean isData(def value) {
        boolean isData = false
        String tag0 = '0'
        String tag00 = '00'
        String timeTxt = value
        String ore = timeTxt.substring(11, 13)
        String minuti = timeTxt.substring(14, 16)
        String secondi = timeTxt.substring(17, 19)
        String decimi = timeTxt.substring(20, 21)

        if (ore.equals(tag00) && minuti.equals(tag00) && secondi.equals(tag00) && decimi.equals(tag0)) {
            isData = true
        }// fine del blocco if

        return isData
    }// fine del metodo

    static String getCampoTabella(String app, String cont, long id, def value) {
        String testo = ''

        if (value instanceof Long || value instanceof Boolean || value instanceof String || value instanceof Date) {
            if (value instanceof Long) {
                testo = getCampoTabellaLong(app, cont, id, value)
            }// fine del blocco if

            if (value instanceof Boolean) {
                testo = getCampoTabellaBooleano(value)
            }// fine del blocco if

            if (value instanceof String) {
                testo = getCampoTabellaStringa(app, cont, id, value)
            }// fine del blocco if

            if (value instanceof Date) {
                if (value instanceof Timestamp) {
                    testo = getCampoTabellaTime(app, cont, id, value)
                } else {
                    testo = getCampoTabellaData(app, cont, id, value)
                }// fine del blocco if-else
            }// fine del blocco if
        } else {
            if (value) {
                testo = getCampoTabellaStringa(app, cont, id, value)
            } else {
                testo = getCampoTabellaStringa(app, cont, id, '')
            }// fine del blocco if-else
        }// fine del blocco if-else

        return testo
    }// fine del metodo

    static String getCampoSchedaBooleano(String nome, String testoLabel, boolean flag) {
        String testoOut = ''

        //--prima la label
        testoOut += Lib.tagLabel(nome, testoLabel)

//        //--poi il campo col checkbox
//        testoOut += "<input type=\"hidden\" name=\"_${nome}\" /><input type=\"checkbox\""
//        if (value) {
//            testoOut += ' checked '
//        } else {
//            testoOut += ' disabled '
//        }// fine del blocco if-else
//        testoOut += "name=\"\${nome}\" id=\"\${nome}\"/>"

        //--poi il campo col valore=vero/falso
        testoOut += Lib.tagBool(nome, flag)

        //--il tutto inserito nel tag tagLiContain
        testoOut = Lib.tagLiContain(testoOut)

        //--il tutto inserito nel tag tagOlClass
        testoOut = Lib.tagOlClass(testoOut)

        return testoOut
    }// fine del metodo


    static String getCampoSchedaFormBooleano(String nome, String testoLabel, boolean flag) {
        String testoOut = ''

        //--prima la label
        testoOut += Lib.tagLabelEdit(nome, testoLabel)

        //--poi il blocco hidden
        testoOut += Lib.tagHidden(nome)

        //--poi il blocco checkbox
        testoOut += Lib.tagCheckbox(nome, flag)

        //--il tutto inserito nel tag tagLiContain
        testoOut = Lib.tagDivContain(testoOut)

        return testoOut
    }// fine del metodo

    /**
     * Forza il primo carattere della stringa a maiuscolo
     * Restituisce una stringa
     * Un numero ritorna un numero
     * Un nullo ritorna un nullo
     * Un oggetto non stringa ritorna uguale
     *
     * @param entrata stringa in ingresso
     * @return uscita string in uscita
     */
    public static primaMaiuscola(entrata) {
        // variabili e costanti locali di lavoro
        def uscita = entrata
        String primo
        String rimanente

        if (entrata && entrata in String) {
            primo = entrata.substring(0, 1)
            primo = primo.toUpperCase()
            rimanente = entrata.substring(1)
            uscita = primo + rimanente
        }// fine del blocco if

        // valore di ritorno
        return uscita
    } // fine del metodo statico

    //--Inserisce il testo nel tag della cella (titolo)
    public static tagCellaTitolo(String testoIn) {
        return tagCellaTitolo(testoIn, null)
    } // fine del metodo statico

    //--Inserisce il testo nel tag della cella (titolo)
    public static tagCellaTitolo(String testoIn, Aspetto classe) {
        return tagCellaTitolo(testoIn, classe, 1)
    } // fine del metodo statico

    //--Inserisce il testo nel tag della cella (titolo)
    public static tagCellaTitolo(String testoIn, Aspetto classe, int span) {
        return tag('th', testoIn, classe.toString(), 'col', span)
    } // fine del metodo statico

    //--Inserisce il testo nel tag della cella (normale)
    public static tagCella(String testoIn) {
        return tagCella(testoIn, null)
    } // fine del metodo statico

    //--Inserisce il testo nel tag della cella (normale)
    public static tagCella(String testoIn, Aspetto classe) {
        return tagCella(testoIn, classe, 1)
    } // fine del metodo statico

    //--Inserisce il testo nel tag della cella (normale)
    public static tagCella(String testoIn, Aspetto classe, int span) {
        return tag('td', testoIn, classe.toString(), 'row', span)
    } // fine del metodo statico

    //--Inserisce il testo nel tag di un elemento HTML
    public static tag(String tag, String testoIn, String classe, String prefixSpan, int span) {
        String testoOut = ''

        testoOut += "<${tag}"
        if (classe && !classe.equals('null')) {
            testoOut += ' class="'
            testoOut += classe
            testoOut += '"'
        }// fine del blocco if
        if (span && span > 1) {
            testoOut += " ${prefixSpan}span=\""
            testoOut += span
            testoOut += '"'
        }// fine del blocco if
        testoOut += '>'

        testoOut += testoIn
        testoOut += "</${tag}>"
        testoOut += aCapo

        return testoOut
    } // fine del metodo statico

    //--Inserisce il testo nel tag div, formattato a destra
    public static tagDivDex(String testoIn) {
        String testoOut = ''

        if (testoIn) {
            testoOut += '<div style="text-align: right;">'
            testoOut += testoIn
            testoOut += '</div>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    //--Inserisce il testo nel tag div contain
    public static tagLiContain(String testoIn) {
        String testoOut = ''

        if (testoIn) {
            testoOut += '<li class="fieldcontain">'
            testoOut += testoIn
            testoOut += '</li>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    //--Inserisce il testo nel tag div contain
    public static tagDivContain(String testoIn) {
        String testoOut = ''

        if (testoIn) {
            testoOut += '<div class="fieldcontain">'
            testoOut += testoIn
            testoOut += '</div>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    //--Costruisce il tag label per il testo indicato
    public static tagLabel(String nome, String testoLabel) {
        String testoOut = ''

        if (nome && testoLabel) {
            testoLabel = Lib.primaMaiuscola(testoLabel)
            testoOut += "<span id=\"${nome}-label\" class=\"property-label\">"
            testoOut += testoLabel
            testoOut += '</span>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    //--Costruisce il tag label per il testo indicato
    public static tagLabelEdit(String nome, String testoLabel) {
        String testoOut = ''

        if (nome) {
            testoLabel = Lib.primaMaiuscola(testoLabel)
            testoOut += "<label for=\"${nome}\">"
            testoOut += testoLabel
            testoOut += '</label>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    //--Costruisce il tag booleano mostrando vero o falso
    public static tagBool(String testoIn, boolean flag) {
        String testoOut = ''

        testoOut += "<span class=\"property-value\" aria-labelledby=\"${testoIn}-label\">"
        if (flag) {
            testoOut += 'Vero'
        } else {
            testoOut += 'Falso'
        }// fine del blocco if-else
        testoOut += "</span>"

        return testoOut
    } // fine del metodo statico

    //--Costruisce il tag ol per il testo indicato
    public static tagOlClass(String testoIn) {
        String testoOut = ''

        if (testoIn) {
            testoOut += '<ol class="property-list milite">'
            testoOut += testoIn
            testoOut += '</ol>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    //--Costruisce il tag hidden per il testo indicato
    public static tagHidden(String nome) {
        String testoOut = ''

        if (nome) {
            testoOut += '<input type="hidden" name="_'
            testoOut += nome
            testoOut += '"/>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    //--Costruisce il tag checkbox per il testo indicato
    public static tagCheckbox(String nome, boolean flag) {
        String testoOut = ''

        if (nome) {
            testoOut += '<input type="checkbox" name="'
            testoOut += nome
            testoOut += '"'
            if (flag) {
                testoOut += ' checked="checked" '
            }// fine del blocco if
            testoOut += '" id="'
            testoOut += nome
            testoOut += '"/>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    public static Turno creaTurno(Croce croce, TipoTurno tipoTurno, Date giorno) {
        Turno turno = null
        Date primoGennaio2013 = Lib.creaData1Gennaio()
        Date inizio
        Date fine
        int offSet = Lib.getNumGiorno(giorno) - 1
        inizio = primoGennaio2013 + offSet
        fine = primoGennaio2013 + offSet
        String funz

        inizio = Lib.setOra(inizio, tipoTurno.oraInizio)
        fine = Lib.setOra(fine, tipoTurno.oraFine)
        if (tipoTurno.fineGiornoSuccessivo) {
            fine = fine + 1
        }// fine del blocco if

        //turno = Turno.findOrCreateByCroceAndTipoTurnoAndGiornoAndInizioAndFine(croce, tipoTurno, giorno, inizio, fine)
        if (tipoTurno.sigla.equals(Cost.EXTRA)) {
            turno = new Turno(croce: croce, tipoTurno: tipoTurno, giorno: giorno)
        } else {
            turno = Turno.findOrCreateByCroceAndTipoTurnoAndGiorno(croce, tipoTurno, giorno)
        }// fine del blocco if-else
        turno.inizio = inizio
        turno.fine = fine
        for (int k = 1; k <= tipoTurno.getListaFunzioni().size(); k++) {
            funz = 'funzione' + k
            turno."${funz}" = tipoTurno.getListaFunzioni().get(k - 1)
        } // fine del ciclo for
        turno.note = ''
        turno.localitàExtra = ''
        turno.save(failOnError: true)

        return turno
    }// fine del metodo

    /**
     * Presentazione della data.
     */
    def static presentaDataNum(Date data) {
        /* variabili e costanti locali di lavoro */
        String dataFormattata = ''
        GregorianCalendar cal = new GregorianCalendar()
        def giorno
        def mese
        def anno
        String sep = '-'

        try { // prova ad eseguire il codice
            if (data) {
                cal.setTime(data)
                giorno = cal.get(Calendar.DAY_OF_MONTH)
                mese = cal.get(Calendar.MONTH)
                mese++
                anno = cal.get(Calendar.YEAR)
                anno = anno + ''
                anno = anno.substring(2)

                dataFormattata += giorno
                dataFormattata += sep
                dataFormattata += mese
                dataFormattata += sep
                dataFormattata += anno
            }// fine del blocco if

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return dataFormattata
    }// fine del metodo

    /**
     * Presentazione della data.
     */
    def static presentaDataMese(Date data) {
        /* variabili e costanti locali di lavoro */
        String dataFormattata = ''
        GregorianCalendar cal = new GregorianCalendar()
        def giorno
        def mese
        def anno
        String sep = '-'

        try { // prova ad eseguire il codice
            if (data) {
                cal.setTime(data)
                giorno = cal.get(Calendar.DAY_OF_MONTH)
                mese = cal.get(Calendar.MONTH)
                mese++
                mese = Mese.getShort(mese)  //scrive il nome del mese, ma allarga la colonna
                anno = cal.get(Calendar.YEAR)
                anno = anno + ''
                anno = anno.substring(2)

                dataFormattata += giorno
                dataFormattata += sep
                dataFormattata += mese
                dataFormattata += sep
                dataFormattata += anno
            }// fine del blocco if

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return dataFormattata
    }// fine del metodo

    /**
     * Restituisce il giorno della settimana.
     * <p/>
     * Giorno scritto per intero <br>
     *
     * @param giorno da elaborare
     *
     * @return giorno della settimana
     */
    public static String getGiorno(Date giorno) {
        /* variabili e costanti locali di lavoro */
        String settimana = ""
        int pos
        GregorianCalendar cal = new GregorianCalendar()

        try { // prova ad eseguire il codice
            if (giorno) {
                cal.setTime(giorno);
                pos = cal.get(Calendar.DAY_OF_WEEK);

                // la settimana inglese comincia da domenica
                // quella italiana da lunedi

                pos--;

                // shift della domenica
                if (pos == 0) {
                    pos = 7;
                }// fine del blocco if

                // nel calendario i giorni della settimana cominciano da 1
                // la Enumeration comincia da zero
                pos--;

                if ((pos >= 0) && (pos <= 7)) {
                    settimana = Giorno.values()[pos].toString();
                }// fine del blocco if
            }// fine del blocco if

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return settimana;
    }

    /**
     * Presentazione della data.
     */
    def static presentaDataCompleta(Date data) {
        /* variabili e costanti locali di lavoro */
        String dataFormattata = ''
        GregorianCalendar cal = new GregorianCalendar()
        def giorno
        def mese
        def anno
        String sep = '-'
        String settimana = getGiorno(data)

        try { // prova ad eseguire il codice
            if (data) {
                cal.setTime(data)
                giorno = cal.get(Calendar.DAY_OF_MONTH)
                mese = cal.get(Calendar.MONTH)
                mese++
                mese = Mese.getShort(mese)  //scrive il nome del mese, ma allarga la colonna
                anno = cal.get(Calendar.YEAR)

                dataFormattata += settimana
                dataFormattata += ', '
                dataFormattata += giorno
                dataFormattata += sep
                dataFormattata += mese
                dataFormattata += sep
                dataFormattata += anno
            }// fine del blocco if

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return dataFormattata
    }// fine del metodo

    /**
     * Crea la data del primo gennaio corrente anno.
     * <p/>
     *
     * @param giorno il giorno del mese (1 per il primo)
     * @param mese il mese dell'anno (1 per gennaio)
     * @param anno l'anno
     *
     * @return la data creata
     */
    public static Date creaData1Gennaio() {
        /* variabili e costanti locali di lavoro */
        Date giorno = new Date()
        Calendar cal

        try { // prova ad eseguire il codice
            cal = Calendar.getInstance()
            cal.setTime(giorno)
            cal.set(Calendar.MONTH, 0)
            cal.set(Calendar.DAY_OF_MONTH, 1)
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)

            giorno = new java.util.Date(cal.getTime().getTime());

        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return giorno
    }// fine del metodo

    /**
     * Crea la data del giorno.
     *
     * @return la data creata
     */
    public static Date creaData(int num) {
        /* variabili e costanti locali di lavoro */
        Date giorno = null

        try { // prova ad eseguire il codice
            giorno = creaData1Gennaio()
            giorno = giorno + num - 1
        } catch (Exception unErrore) { // intercetta l'errore
        }// fine del blocco try-catch

        /* valore di ritorno */
        return giorno
    }// fine del metodo

    public static Date setOra(Date giornoIn, int ora) {
        Date giornoOut
        Calendar cal = Calendar.getInstance()

        cal.setTime(giornoIn)
        cal.set(Calendar.HOUR_OF_DAY, ora)

        giornoOut = new java.util.Date(cal.getTime().getTime());

        return giornoOut
    }// fine del metodo

    public static String getAnno(Date giorno) {
        String anno
        Calendar cal = Calendar.getInstance()

        anno = cal.get(Calendar.YEAR)

        return anno
    }// fine del metodo

    public static int getNumGiorno(Date giornoIn) {
        int giorno
        Calendar cal = Calendar.getInstance()

        cal.setTime(giornoIn)
        giorno = cal.get(Calendar.DAY_OF_YEAR)

        return giorno
    }// fine del metodo

    public static int getNumGiornoMese(Date giornoIn) {
        int giorno
        Calendar cal = Calendar.getInstance()

        cal.setTime(giornoIn)
        giorno = cal.get(Calendar.DAY_OF_MONTH)

        return giorno
    }// fine del metodo

    public static int getNumGiorniNelMese(Date giornoIn) {
        int giorni
        int mese
        Calendar cal = Calendar.getInstance()

        cal.setTime(giornoIn)
        mese = cal.get(Calendar.MONTH) + 1
        giorni = Mese.getGiorni(mese)

        return giorni
    }// fine del metodo

    public static int getNumMese(Date giornoIn) {
        int mese
        Calendar cal = Calendar.getInstance()

        cal.setTime(giornoIn)
        mese = cal.get(Calendar.MONTH) + 1

        return mese
    }// fine del metodo

    public static int getNumOra(Date giornoIn) {
        int ora
        Calendar cal = Calendar.getInstance()

        cal.setTime(giornoIn)
        ora = cal.get(Calendar.HOUR_OF_DAY)

        return ora
    }// fine del metodo

    public static int getNumMinuti(Date giornoIn) {
        int minuti
        Calendar cal = Calendar.getInstance()

        cal.setTime(giornoIn)
        minuti = cal.get(Calendar.MINUTE)

        return minuti
    }// fine del metodo

    public static int getNumSettimana(Date giornoIn) {
        int settimana
        Calendar cal = Calendar.getInstance()

        cal.setTime(giornoIn)
        settimana = cal.get(Calendar.DAY_OF_WEEK)

        return settimana
    }// fine del metodo

    //--Costruisce il tag controller per il testo indicato
    public static String tagController(String controller, String titolo) {
        String testoOut = ''

        if (controller && titolo) {
            testoOut += '<li class="controller">'
            testoOut += '<a href="/webambulanze/'
            testoOut += controller
            testoOut += '/index">'
            testoOut += titolo
            testoOut += '</a>'
            testoOut += '</li>'
        }// fine del blocco if

        return testoOut
    } // fine del metodo statico

    //--Costruisce il tag controller per il testo indicato
    public static String tagController(String controller) {
        return Lib.tagController(controller, controller)
    } // fine del metodo statico


} // fine della classe
