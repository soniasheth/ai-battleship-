package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.player.AiPlayer;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;



public class ProxyDealer {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AiPlayer player;
  private final ObjectMapper mapper = new ObjectMapper();

  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");
  public ProxyDealer(Socket server, AiPlayer ai) throws IOException {
    this.server = server;
    this.player = ai;

    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
  }

  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        //MessageJson message = parser.readValueAs(MessageJson.class);
        //delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
    }


  }
}
