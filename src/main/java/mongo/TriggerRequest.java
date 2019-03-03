package mongo;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "triggerRequests")

public class TriggerRequest  {
    @Indexed(unique = true)
    private TriggerDTO originalTrigger;
    @Indexed
    private String eventId;
    @Indexed
    private String gameId;
    private String triggerCategory;
    private String sportType;

    @Builder
    public TriggerRequest(TriggerDTO originalTrigger, String eventId, String gameId, String triggerCategory, String sportType,
                          String id, String traceId, String triggerType, String personName, String teamName,
                          String iotaNotificationId, String iotaNotificationTimestamp, String playerName,
                          String createdAt, String startedAt, LocalDateTime dbCreatedDate) {
        this.originalTrigger = originalTrigger;
        this.eventId = eventId;
        this.gameId = gameId;
        this.triggerCategory = triggerCategory;
        this.sportType = sportType;
    }

}
