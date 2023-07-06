/**
 * 
 */
package com.seersol.leatrac.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seersol.leatrac.dto.ContactDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @author Ravi.sangubotla
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class DataImportController {

	private static final Logger LOG = LoggerFactory.getLogger(DataImportController.class);

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/import")
	@ApiOperation("Import Data")
	public ResponseEntity<ContactDTO> importData(@RequestBody ContactDTO contact) {
		LOG.info("Importing Data :: {}", contact);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		ContactDTO dto = new ContactDTO();
		dto.setCity("Hyderabad");
		dto.setCompany("seersolutions");
		dto.setEmail("a@a.com");
		dto.setFirstName("FirstName");
		dto.setLastName("LastName");
		dto.setState("TS");
		dto.setStatus("Active");
		
		
		LOG.info("contact json :: {}",gson.toJson(dto));
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
