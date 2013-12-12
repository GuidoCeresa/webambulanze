/* Created by Algos s.r.l. */
/* Date: mag 2012 */
/* Questo file è stato installato dal plugin AlgosBase */
/* Tipicamente NON verrà più sovrascritto dalle successive release del plugin */
/* in quanto POTREBBE essere personalizzato in questa applicazione */
/* Se vuoi che le prossime release del plugin sovrascrivano questo file, */
/* perdendo tutte le modifiche effettuate qui, */
/* regola a true il flag di controllo flagOverwrite© */
/* flagOverwrite = false */

package webambulanze

import org.springframework.dao.DataIntegrityViolationException

class UtenteRuoloController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def utenteService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    static arrayUtenti = new ArrayList()
    static arrayRuoli = new ArrayList()

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroce(request)
        def campiLista = [
                'utente',
                'ruolo']

        if (!params.sort) {
            params.sort = 'utente'
        }// fine del blocco if-else

        if (croce) {
            params.siglaCroce = croce.sigla
            if (params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
                lista = UtenteRuolo.findAll("from UtenteRuolo order by ruolo,utente")
            } else {
                if (!params.sort) {
                    params.sort = 'utente'
                }// fine del blocco if-else
                lista = utenteService.tuttiUtentiRuoloDellaCroceSenzaProgrammatore(croce)
            }// fine del blocco if-else
        } else {
            lista = utenteService.tuttiSenzaProgrammatore(params)
        }// fine del blocco if-else

        long k = 0
        arrayUtenti = new ArrayList()
        arrayRuoli = new ArrayList()
        lista?.each {
            k++
            arrayUtenti.add(it.utente)
            arrayRuoli.add(it.ruolo)
            it.id = k
        } // fine del ciclo each

        render(view: 'list', model: [utenteRuoloInstanceList: lista, utenteRuoloInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    def create() {
        Croce croce = croceService.getCroce(request)
        def listaUtenti = null
        def listaRuoli

        if (croce) {
            listaUtenti = Utente.findAllByCroce(croce)
        }// fine del blocco if
        listaRuoli = Ruolo.findAllByAuthorityNotEqual('ROLE_prog')

        [utenteRuoloInstance: new UtenteRuolo(params), listaRuoli: listaRuoli, listaUtenti: listaUtenti]
    } // fine del metodo

    def save() {
        def utenteRuoloInstance = new UtenteRuolo(params)
        if (!utenteRuoloInstance.save(flush: true)) {
            render(view: "create", model: [utenteRuoloInstance: utenteRuoloInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), utenteRuoloInstance.id])
        redirect(action: "list")
    } // fine del metodo

    def show(Long id) {
        def utenteRuoloInstance = recuperaIstanza(id)
        if (!utenteRuoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
            return
        }
        [utenteRuoloInstance: utenteRuoloInstance]
    } // fine del metodo

    def edit(Long id) {
        def listaUtenti = null
        def listaRuoli
        Croce croce = croceService.getCroce(request)

        def utenteRuoloInstance = recuperaIstanza(id)
        if (!utenteRuoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
            return
        }

        if (croce) {
            listaUtenti = Utente.findAllByCroce(croce)
        }// fine del blocco if
        listaRuoli = Ruolo.findAllByAuthorityNotEqual('ROLE_prog')

        [utenteRuoloInstance: utenteRuoloInstance, listaRuoli: listaRuoli, listaUtenti: listaUtenti]
    } // fine del metodo

    def update(Long id) {
        def utenteRuoloInstance = recuperaIstanza(id)
        if (!utenteRuoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
            return
        }
        utenteRuoloInstance.delete(flush: true)
        utenteRuoloInstance = new UtenteRuolo(params)

        if (!utenteRuoloInstance.save(flush: true)) {
            render(view: "edit", model: [utenteRuoloInstance: utenteRuoloInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), utenteRuoloInstance.id])
        redirect(action: "list")
    } // fine del metodo

    def delete(Long id) {
        def utenteRuoloInstance = recuperaIstanza(id)
        if (!utenteRuoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
            return
        }

        try {
            utenteRuoloInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

    private static UtenteRuolo recuperaIstanza(long id) {
        UtenteRuolo istanza
        long idUtente = 0
        long idRuolo = 0
        String nomeUtente
        String nomeRuolo
        Utente utente
        Ruolo ruolo
        int pos = id.intValue() - 1

        nomeUtente = arrayUtenti[pos]
        nomeRuolo = arrayRuoli[pos]
        if (nomeUtente) {
            utente = Utente.findByNickname(nomeUtente)
            if (utente) {
                idUtente = utente.id
            }// fine del blocco if
        }// fine del blocco if
        if (nomeRuolo) {
            nomeRuolo = 'ROLE_' + nomeRuolo
            ruolo = Ruolo.findByAuthority(nomeRuolo)
            if (ruolo) {
                idRuolo = ruolo.id
            }// fine del blocco if
        }// fine del blocco if
        istanza = UtenteRuolo.get(idUtente, idRuolo)
        istanza.id = id
        return istanza
    } // fine del metodo

} // fine della controller classe
