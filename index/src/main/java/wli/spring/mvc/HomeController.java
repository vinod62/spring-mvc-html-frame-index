package wli.spring.mvc;

import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import wli.java.*;
import wli.java.db.*;
import wli.java.stigs.Stig;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update() {
		
		logger.info("Welcome update!");
		return "update";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		/*
		Stage stage = null;

		MyDatabaseUtils mdut = new MyDatabaseUtils();
		try {
			mdut.setStageByDb();
			ArrayList<Stage> alist = (ArrayList<Stage>) mdut.getStageList();
			Iterator<Stage> it = alist.iterator();
			while (it.hasNext()) {
				stage = it.next();
			}
			return "main";

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
*/
		
		logger.info("Welcome home!");

		return "main";
	}
}
