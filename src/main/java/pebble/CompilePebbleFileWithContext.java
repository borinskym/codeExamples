package pebble;

import com.google.common.collect.ImmutableMap;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class CompilePebbleFileWithContext {

    private PebbleEngine pebbleEngine;
    private String templateFileName;
    public String compile() {
        try (Writer writer = new StringWriter()) {
            PebbleTemplate pebbleTemplate = pebbleEngine.getTemplate(templateFileName);
            pebbleTemplate.evaluate(writer, pebbleContext());
            return writer.toString();
        }catch (IOException e){
         return null;
        }
    }


    private ImmutableMap<String, Object> pebbleContext() {
        return ImmutableMap.of(
                "data", ImmutableMap.of(
                        "eventId", "EVENT_ID",
                        "triggerType", "TRIGGER_TYPE")
        );
    }
}
