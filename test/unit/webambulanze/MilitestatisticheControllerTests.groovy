package webambulanze



import org.junit.*
import grails.test.mixin.*

@TestFor(MilitestatisticheController)
@Mock(Militestatistiche)
class MilitestatisticheControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/militestatistiche/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.militestatisticheInstanceList.size() == 0
        assert model.militestatisticheInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.militestatisticheInstance != null
    }

    void testSave() {
        controller.save()

        assert model.militestatisticheInstance != null
        assert view == '/militestatistiche/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/militestatistiche/show/1'
        assert controller.flash.message != null
        assert Militestatistiche.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/militestatistiche/list'

        populateValidParams(params)
        def militestatistiche = new Militestatistiche(params)

        assert militestatistiche.save() != null

        params.id = militestatistiche.id

        def model = controller.show()

        assert model.militestatisticheInstance == militestatistiche
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/militestatistiche/list'

        populateValidParams(params)
        def militestatistiche = new Militestatistiche(params)

        assert militestatistiche.save() != null

        params.id = militestatistiche.id

        def model = controller.edit()

        assert model.militestatisticheInstance == militestatistiche
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/militestatistiche/list'

        response.reset()

        populateValidParams(params)
        def militestatistiche = new Militestatistiche(params)

        assert militestatistiche.save() != null

        // test invalid parameters in update
        params.id = militestatistiche.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/militestatistiche/edit"
        assert model.militestatisticheInstance != null

        militestatistiche.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/militestatistiche/show/$militestatistiche.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        militestatistiche.clearErrors()

        populateValidParams(params)
        params.id = militestatistiche.id
        params.version = -1
        controller.update()

        assert view == "/militestatistiche/edit"
        assert model.militestatisticheInstance != null
        assert model.militestatisticheInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/militestatistiche/list'

        response.reset()

        populateValidParams(params)
        def militestatistiche = new Militestatistiche(params)

        assert militestatistiche.save() != null
        assert Militestatistiche.count() == 1

        params.id = militestatistiche.id

        controller.delete()

        assert Militestatistiche.count() == 0
        assert Militestatistiche.get(militestatistiche.id) == null
        assert response.redirectedUrl == '/militestatistiche/list'
    }
}
