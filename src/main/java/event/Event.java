package event;


import lombok.*;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    private String id;
    private String iotaEventId;
    private String name;
    private List<Story> stories;
    private String sport;

}
