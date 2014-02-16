package org.owasp.goatdroid.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apps")
public class AppsController {

	@RequestMapping(value = "")
	public String index() {
		return "/apps/home";
	}
}
