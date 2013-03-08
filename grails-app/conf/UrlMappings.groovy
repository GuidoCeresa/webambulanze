/* Created by Algos s.r.l. */
/* Date: mag 2012 */
/* Questo file è stato installato dal plugin AlgosBase */
/* Tipicamente NON verrà più sovrascritto dalle successive release del plugin */
/* in quanto POTREBBE essere personalizzato in questa applicazione */
/* Se vuoi che le prossime release del plugin sovrascrivano questo file, */
/* perdendo tutte le modifiche effettuate qui, */
/* regola a true il flag di controllo flagOverwrite© */
/* flagOverwrite = false */

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }
        //      "/"(controller: 'Gen', action: 'selezionaCroce')
        "/"(controller: 'Gen', action: 'selezionaCroceRossaPonteTaroFree')
        "/algos"(controller: 'Gen', action: 'selezionaCroceAlgos')
        "/ALGOS"(controller: 'Gen', action: 'selezionaCroceAlgos')
        "/demo"(controller: 'Gen', action: 'selezionaCroceDemo')
        "/DEMO"(controller: 'Gen', action: 'selezionaCroceDemo')
        //       "/pavt"(controller: 'Gen', action: 'selezionaCrocePAVT')
//        "/PAVT"(controller: 'Gen', action: 'selezionaCrocePAVT')
        "/crf"(controller: 'Gen', action: 'selezionaCroceRossaFidenza')
        "/CRF"(controller: 'Gen', action: 'selezionaCroceRossaFidenza')
        "/crpt"(controller: 'Gen', action: 'selezionaCroceRossaPonteTaroFree')
        "/CRPT"(controller: 'Gen', action: 'selezionaCroceRossaPonteTaroFree')
        "/logoutSelection"(controller: 'Gen', action: 'logoutSelection')
        "/logoutalgos"(controller: 'Gen', action: 'logoutalgos')
        "/logoutdemo"(controller: 'Gen', action: 'logoutdemo')
        "/demogac"(controller: 'Gen', action: 'logoutdemo')
        "500"(view: '/error')
    }
}
