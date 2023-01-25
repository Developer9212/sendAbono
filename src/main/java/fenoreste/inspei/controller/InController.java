package fenoreste.inspei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fenoreste.inspei.modelos.request;
import fenoreste.inspei.service.InServiceGeneral;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({ "/api/sendAbono" })
public class InController {
	
	@Autowired
	private InServiceGeneral serviceGeneral;

	@PostMapping
	public ResponseEntity<?>sendAbono(@RequestBody request inData){
		System.out.println(inData.getMonto());
		return ResponseEntity.status(200).body(serviceGeneral.response(inData));
	}
	
}
