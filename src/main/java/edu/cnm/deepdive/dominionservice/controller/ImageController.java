package edu.cnm.deepdive.dominionservice.controller;

import edu.cnm.deepdive.dominionservice.model.entity.Card.CardType;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pics")
public class ImageController {

  @RequestMapping(value = "/{cardType}", method = RequestMethod.GET,
      produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<InputStreamResource> getImage(@PathVariable ("cardId") CardType cardType, Authentication authentication) throws IOException {

    String cardClassPath = cardType.toString();

    ClassPathResource imgFile = new ClassPathResource("res/"+cardClassPath+".jpg");

    return ResponseEntity
        .ok()
        .contentType(MediaType.IMAGE_JPEG)
        .body(new InputStreamResource(( imgFile).getInputStream()));
  }
}
