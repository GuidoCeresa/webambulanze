package webambulanze

import grails.test.GrailsUnitTestCase

class TempCleanerJobTests extends GrailsUnitTestCase {

    def refreshJob
    def grailsApplication

    protected void setUp() {
        super.setUp()
        refreshJob = grailsApplication.mainContext.getBean(RefreshJob)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCleanNothing() {
        refreshJob.execute()
    }

}
