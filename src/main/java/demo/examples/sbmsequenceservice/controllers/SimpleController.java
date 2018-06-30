package demo.examples.sbmsequenceservice.controllers;

import demo.examples.sbmsequenceservice.model.ListPojo;
import demo.examples.sbmsequenceservice.service.SbmValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    private static final Logger LOGGER= LoggerFactory.getLogger(SimpleController.class);

    @RequestMapping(method = RequestMethod.POST,
    consumes = {"application/json"},
    value = "/server")
    public Boolean sbmAnalyse(@RequestBody ListPojo list) {
        return SbmValidator.validateOn2(list.getSeq());
    }
}
