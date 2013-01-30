/* Created by Algos s.r.l. */
/* Date: mag 2012 */
/* Questo file è stato installato dal plugin AlgosBase */
/* Tipicamente NON verrà più sovrascritto dalle successive release del plugin */
/* in quanto POTREBBE essere personalizzato in questa applicazione */
/* Se vuoi che le prossime release del plugin sovrascrivano questo file, */
/* perdendo tutte le modifiche effettuate qui, */
/* regola a true il flag di controllo flagOverwrite© */
/* flagOverwrite = false */

// Devi creare il database esternamente
// Devi mettere il jar mysql-connector-java-5.1.5-bin.jar da qualche parte (nella directory lib di grails-app)
// Devi modificare qui il nome del database
def dataBase = 'amb'

dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = ""
} // end of dataSource

hibernate {
    cache.use_second_level_cache = false
    cache.use_query_cache = false
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
} // end of hibernate

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/${dataBase}?useUnicode=yes&characterEncoding=UTF-8"
        } // end of dataSource
    } // end of development

    test {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/${dataBase}?useUnicode=yes&characterEncoding=UTF-8"
        } // end of dataSource
    } // end of production

    production {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/${dataBase}?useUnicode=yes&characterEncoding=UTF-8"
        } // end of dataSource
    } // end of test
} // end of environments
