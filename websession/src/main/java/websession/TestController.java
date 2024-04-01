package websession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping({"/api"})
public class TestController {
	
	@Autowired
	LinkRelationProvider linkRelationProvider;

	@GetMapping("/websession")
	public Mono getSession(WebSession session) {

	    session.getAttributes().putIfAbsent("id", 0);
	    session.getAttributes().putIfAbsent("note", "Hellow World!");

	    //CustomResponse r = new CustomResponse();
	    String s = session.getAttributes().get("id").toString();
	    s = s + session.getAttributes().get("note");

	    return Mono.just(s);
	}
}
