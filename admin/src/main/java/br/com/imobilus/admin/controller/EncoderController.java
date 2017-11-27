package br.com.imobilus.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobilus.admin.util.Encoder;
import br.com.imobilus.admin.util.request.TextRequest;
import br.com.imobilus.admin.util.response.TextResponse;

@RestController
@RequestMapping("coder")
public class EncoderController {
	
	@PostMapping(value = "encode", consumes="application/json")
	public ResponseEntity<TextResponse> encode(@RequestBody TextRequest request) {
		TextResponse textResponse = new TextResponse();
		textResponse.setResponse(Encoder.encode(request.getRequest()));
		ResponseEntity<TextResponse> response = new ResponseEntity<TextResponse>(textResponse, HttpStatus.OK);
		return response;
	}
	
}
