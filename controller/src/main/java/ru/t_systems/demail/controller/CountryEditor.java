package ru.t_systems.demail.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.t_systems.demail.service.CountryService;

@Component
public class CountryEditor extends PropertyEditorSupport {

	@Autowired
	private CountryService countryService;

	@Override
	public void setAsText(final String text) {

		if (!text.equals("NONE") && !text.isEmpty()) {
		
			setValue(countryService.getCountry(Integer.valueOf(text)));
		}
	}

}
