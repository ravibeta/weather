package net.ravi.spring3.controller;

import net.ravi.spring3.form.Contact;
import java.io.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes
public class WeatherController {

	@RequestMapping(value = "/addZipCode", method = RequestMethod.POST)
	public String addZipCode(@ModelAttribute("zipcode")
							ZipCode zipcode, BindingResult result) {
		

		try {
            
		int zip = Integer.parseInt(zipCode);
		if (zip < 501 || zip > 99950)
			throw new Exception("zip code not found");
		if (zipCode.length() != 5)
			throw new NumberFormatException();

            String weatherData = WeatherProxy.getWeatherForZip(zipcode);
            
            String cityStateTemp = Parser.GetCityStateTemperature(weatherData);
            System.out.println(cityStateTemp);
		} catch (NumberFormatException e) {
			System.out.println("invalid zip code format");
		} catch (Exception e) {
			System.out.println(e);
		}

		
		return "redirect:zipcodes.html";
	}
	
	@RequestMapping("/zipcodes")
	public ModelAndView showZipCodes() {
		
		return new ModelAndView("zipcode", "command", new ZipCode());
	}
	
}
