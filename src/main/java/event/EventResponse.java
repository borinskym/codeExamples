package event;

import lombok.*;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponse {
    private List<Event> heedEvents;
}
