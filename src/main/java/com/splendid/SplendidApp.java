package com.splendid;

import com.splendid.cli.CommandInterpreter;

public class SplendidApp {
    public static void main(String[] args) {

        /*Path origin = Paths.get("/Users/pablo/Desktop/test");
        Path destiny = Paths.get("/Users/pablo/Desktop/copy");

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> nested = new HashMap<>();
        nested.put("attribute1", "Holaaa si cambie nested!");
        data.put("replace1", "Holaaa si cambie 1!");
        data.put("replace2", "Holaaa si cambie 2!");
        data.put("replace3", nested);

        try {
            TemplateEngine.run(origin, destiny, data);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        CommandInterpreter.init(args);
    }
}
