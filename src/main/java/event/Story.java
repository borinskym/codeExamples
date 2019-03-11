package event;


import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Story {
    String scenario;
    String trigger;
}
