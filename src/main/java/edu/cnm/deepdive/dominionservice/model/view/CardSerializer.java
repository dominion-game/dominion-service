package edu.cnm.deepdive.dominionservice.model.view;
/**
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.cnm.deepdive.dominionservice.model.entity.Card;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CardSerializer {

  public static class Serializer extends JsonSerializer<Card> {


    @Override
    public void serialize(Card card, JsonGenerator jsonGenerator,
        SerializerProvider serializerProvider) throws IOException {
      jsonGenerator.writeString(card.getCardType().toString());
    }
  }

  public static class Deserializer extends JsonDeserializer<Card> {

    @Override
    public Card deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
      Card card = new Card();
      card.setCardType(jsonParser.readValueAs(String.class));
      return word;
    }

  }

}
*/