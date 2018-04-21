package donal

import grails.rest.RestfulController

class DemoController extends RestfulController {

    static responseFormats = [
            index: ['xml', 'json'],  // Support both XML, JSON
            save: ['json']           // Only support JSON
    ]

    DemoController() {
        super(User)
    }
}
