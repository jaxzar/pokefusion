package pokefusion;

import java.util.Base64;
import java.util.Random;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class PokeFusion {
    private final String ACCESS_ORIGIN = "Access-Control-Allow-Origin";
    private final String ACCESS_METHODS = "Access-Control-Allow-Methods";
    private final String ACCESS_HEADERS = "Access-Control-Allow-Headers";

    public static String clientID = "2152601087.678095044756";
    public static String slackArgs = "6668c8df82ecb490cc2bab5abea95fd0";

    // #pokefusion channel
    public static String SLACK_API = "https://hooks.slack.com/services/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";

    // #pokemon channel
    //public static String SLACK_API2 = "https://hooks.slack.com/services/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";
    public static String CHAT_API = "https://slack.com/api/chat.postMessage";
    public static String OAUTH_URL = "https://slack.com/api/oauth.access";
    public static String URL_FIRST = "https://images.alexonsager.net/pokemon/fused/";
    public static String URL_LAST = ".png";

    public PokeFusion() {
    }

    @GET
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generatePost() {
        SlackIntegration.main(new String[] {""});
        //Response r = SlackIntegration.getResponse();
        return Response.status(200)
                       .header(ACCESS_ORIGIN, "*")
                       .header(ACCESS_METHODS, "GET")
                       .header(ACCESS_HEADERS, "Content-Type")
                       .entity("Message posted!").build();
    }
    
    static class SlackIntegration {
        public static Response res;
        public static Response getResponse() {
            return res;
        }
        public static void main(String[] args) {
            String[] nameStart = new String[] {
                "", "Bulba", "Ivy", "Venu",
                "Charm", "Charm", "Char",
                "Squirt", "Wart", "Blast",
                "Cater", "Meta", "Butt",
                "Weed", "Kak", "Bee",
                "Pi", "Pid", "Pidg",
                "Ratt", "Rat", "Spear", "Fear",
                "Ek", "Arb", "Pika", "Rai",
                "San", "Sand",
                "Ni", "Nid", "Nido",
                "Ni", "Nid", "Nido",
                "Cle", "Clef", "Vul", "Nine",
                "Jiggly", "Wiggly", "Zu", "Gol",
                "Odd", "Gloo", "Vile",
                "Par", "Para", "Ven", "Veno",
                "Dig", "Dug", "Meow", "Per",
                "Psy", "Gold", "Mank", "Prime",
                "Growl", "Arcan",
                "Po", "Pol", "Poli",
                "Ab", "Kada", "Alaka",
                "Ma", "Mach", "Macha",
                "Bell", "Weepin", "Victree",
                "Ten", "Tenta",
                "Geo", "Grav", "Gole",
                "Pony", "Rapid", "Slo", "Slow",
                "Mag", "Magne",
                "Farfe",
                "Do", "Dod", "Se", "Dew",
                "Gri", "Mu", "Shell", "Cloy",
                "Gast", "Haun", "Gen",
                "On",
                "Drow", "Hyp", "Krab", "King",
                "Vol", "Elec", "Exe", "Exeg",
                "Cu", "Maro", "Hitmon", "Hit",
                "Lick",
                "Koff", "Weez", "Rhy", "Rhyd",
                "Chan", "Tang", "Kangas",
                "Hor", "Sea", "Go", "Seak",
                "Sta", "Star",
                "Mr. ", "Scy", "Jyn", "Elect", "Magm", "Pin", "Tau",
                "Magi", "Gyra",
                "Lap", "Dit",
                "Ee", "Vapor", "Jolt", "Flar",
                "Pory",
                "Oman", "Oma", "Kab", "Kabu",
                "Aero", "Snor",
                "Artic", "Zap", "Mol",
                "Dra", "Drag", "Dragon",
                "Mew", "Me"
            };
            String[] nameEnd = new String[] {
                "", "ur", "aur", "saur",
                "mander", "meleon", "izard",
                "irtle", "ortle", "toise",
                "pie", "pod", "free",
                "dle", "una", "drill",
                "dgey", "otto", "geot",
                "tata", "ticate", "ow", "row",
                "ans", "bok", "chu", "aichu",
                "shrew", "slash",
                "ran", "rina", "queen",
                "oran", "rino", "king",
                "fairy", "able", "pix", "tails",
                "puff", "tuff", "at", "bat",
                "ish", "oom", "plume",
                "as", "asect", "nat", "moth",
                "lett", "trio", "owth", "sian",
                "uck", "duck", "key", "ape",
                "lithe", "nine",
                "wag", "whirl", "wrath",
                "bra", "dabra", "kazam",
                "chop", "choke", "champ",
                "sprout", "bell", "reebel",
                "cool", "cruel",
                "dude", "eler", "lem",
                "yta", "dash", "poke", "bro",
                "mite", "ton",
                "etch'd",
                "duo", "drio", "eel", "gong",
                "mer", "uk", "der", "ster",
                "stly", "ter", "gar",
                "nix",
                "zee", "pno", "by", "ler",
                "torb", "trode", "cute", "utor",
                "bone", "wak", "lee", "chan",
                "tung",
                "ing", "zing", "horn", "don",
                "sey", "ela", "khan",
                "sea", "dra", "een", "king",
                "ryu", "mie",
                "mime", "ther", "ynx", "buzz", "mar", "sir", "ros",
                "karp", "ados",
                "ras", "tto",
                "vee", "oreon", "teon", "areon",
                "gon",
                "nyte", "star", "buto", "tops",
                "dactyl", "lax",
                "cuno", "dos", "tres",
                "tini", "onair", "nite",
                "two", "ew"
            };
            Random r = new Random();
            Client c = ClientBuilder.newClient();
            Client c2 = ClientBuilder.newClient();
            WebTarget slack = c.target(CHAT_API);
            WebTarget oauth = c2.target(OAUTH_URL);
            JsonParser parser = new JsonParser();
            int x = r.nextInt(151)+1;
            int y = r.nextInt(151)+1;
            while (y == x) {
                y = r.nextInt(151)+1;
            }
            String temp = clientID + ":" + slackArgs;
            String credentialBlob = Base64.getEncoder().encodeToString(temp.getBytes());

            // Post to Slack

            // block 1 (name)
            String request = "[{ \"type\": \"section\"," +
                " \"text\": { \"type\": \"mrkdwn\", \"text\": \"" + nameStart[x] + nameEnd[y] + "\" } " +
                " }," +
                // block 2 (image)
                " \"type\": \"section\", " +
                " \"text\": { \"type\": \"mrkdwn\", \"text\": \"" + URL_FIRST + x + "/" + x + "." + y + URL_LAST + "\" }" +
                "}]";
            res = slack.request()
                .header("Authorization", "Basic " + credentialBlob)
                .header("Content-Type", "application/json")
                .post(Entity.json(request));
            System.out.println("Slack response: " + res.toString());
        }
    }
}
