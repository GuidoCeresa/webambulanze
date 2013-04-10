package webambulanze



import org.junit.*
import grails.test.mixin.*

@TestFor(ViaggioController)
@Mock(Viaggio)
class ViaggioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/viaggio/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.viaggioInstanceList.size() == 0
        assert model.viaggioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.viaggioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.viaggioInstance != null
        assert view == '/viaggio/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/viaggio/show/1'
        assert controller.flash.message != null
        assert Viaggio.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/viaggio/list'

        populateValidParams(params)
        def viaggio = new Viaggio(params)

        assert viaggio.save() != null

        params.id = viaggio.id

        def model = controller.show()

        assert model.viaggioInstance == viaggio
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/viaggio/list'

        populateValidParams(params)
        def viaggio = new Viaggio(params)

        assert viaggio.save() != null

        params.id = viaggio.id

        def model = controller.edit()

        assert model.viaggioInstance == viaggio
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/viaggio/list'

        response.reset()

        populateValidParams(params)
        def viaggio = new Viaggio(params)

        assert viaggio.save() != null

        // test invalid parameters in update
        params.id = viaggio.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/viaggio/edit"
        assert model.viaggioInstance != null

        viaggio.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/viaggio/show/$viaggio.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        viaggio.clearErrors()

        populateValidParams(params)
        params.id = viaggio.id
        params.version = -1
        controller.update()

        assert view == "/viaggio/edit"
        assert model.viaggioInstance != null
        assert model.viaggioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/viaggio/list'

        response.reset()

        populateValidParams(params)
        def viaggio = new Viaggio(params)

        assert viaggio.save() != null
        assert Viaggio.count() == 1

        params.id = viaggio.id

        controller.delete()

        assert Viaggio.count() == 0
        assert Viaggio.get(viaggio.id) == null
        assert response.redirectedUrl == '/viaggio/list'
    }
}
