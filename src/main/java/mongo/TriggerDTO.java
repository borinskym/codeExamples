package mongo;

import jdk.nashorn.internal.ir.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TriggerDTO {

    public final static String STARTED_AT = "startedAt";
    public final static String MATCH_ID = "matchId";
    public final static String FIGHT_ID = "fightId";
    public final static String EVENT_ID = "eventId";
    public final static String PLAYER_NAME = "playerName";
    public final static String CONTESTANT_ID = "contestantId";
    public final static String TYPE_ID = "typeId";

    private HeaderDTO header;
    private ObjectNode body;
}
