package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.enums.ShipType;
import cs3500.pa03.model.player.AiPlayer;
import cs3500.pa04.Json.FleetSpecJson;
import cs3500.pa04.Json.JoinJson;
import cs3500.pa04.Json.JsonUtils;
import cs3500.pa04.Json.MessageJson;
import cs3500.pa04.Json.SetUpJson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ProxyController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AiPlayer player;
  private final ObjectMapper mapper = new ObjectMapper();

  public ProxyController(Socket server, AiPlayer ai) throws IOException {
    this.server = server;
    this.player = ai;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
  }

  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);
      //keep listening to the server
      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
    }
    //handle the ending of the game

  }


  /**
   * Determines the type of request the server has sent ("guess" or "win") and delegates to the
   * corresponding helper method with the message arguments.
   *
   * @param message the MessageJSON used to determine what the server has sent
   */
  private void delegateMessage(MessageJson message) {
    String name = message.methodName();
    JsonNode arguments = message.arguments();

    if ("join".equals(name)) {
      handleJoin();
    } else if ("setup".equals(name)) {
      handleSetUp(arguments);
    } else if ("take-shots".equals(name)) {
      handleTakeShots(arguments);
    } else if ("report-damage".equals(name)) {
      handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      handleSuccHits(arguments);
    } else {
      throw new IllegalStateException("Invalid message name");
    }
  }

  /**
   *
   */
  private void handleJoin() {
    //information needed for a client response
    String gitUserName = "soniasheth";
    String gameType = "MULTI";

    //create a joinJSON message to send back to the server
    JoinJson joinMessage = new JoinJson(gitUserName, gameType);
    JsonNode jsonResponse = JsonUtils.serializeRecord(joinMessage);

    //create the response to send back to server
    MessageJson clientResponse = new MessageJson("join", jsonResponse);

    //sends back to the server??? i think
    this.out.println(clientResponse);
  }

  /**
   *
   * @param arguments
   */
  private void handleSetUp(JsonNode arguments) {
    //convert to the data representation of set-up (SetUpJson)
    SetUpJson setUpArgs = this.mapper.convertValue(arguments, SetUpJson.class);

    //get the height and width from ther server's message
    int height = setUpArgs.height();
    int width = setUpArgs.width();

    //get the fleet from the server's message
    FleetSpecJson fleetSpec = setUpArgs.fleetSpec();
    Map<ShipType, Integer> specification = new HashMap<>();
    specification.put(ShipType.CARRIER, fleetSpec.numCarrier());
    specification.put(ShipType.BATTLESHIP, fleetSpec.numBattleShip());
    specification.put(ShipType.DESTROYER, fleetSpec.numDestroyer());
    specification.put(ShipType.SUBMARINE, fleetSpec.numSubmarine());

    List<Ship> aiShips = player.setup(height, width,specification);
    //SOMEHOW CONVERT THE SHIP TO THE SHIPADAPTER !!!!!!!!!!

  }

  private void handleTakeShots(JsonNode arguments) {

  }

  private void handleReportDamage(JsonNode arguments) {

  }

  private void handleSuccHits(JsonNode arguments) {

  }








}
