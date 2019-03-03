package mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeaderDTO {
    private String id;
    private String eventTime;
    private String trigger;
    private String originalTrigger;
    private String type;
    private String lastModified;
    private String simulationId;
    private String origin;
}
