package io.cloudtraining.sbdg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.time.LocalDate;

@Data
/**
 * @see Getter
 * @see Setter
 * @see RequiredArgsConstructor
 * @see ToString
 * @see EqualsAndHashCode
 * @see lombok.Value
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Region("person")
public class Person {

    @Id @NonNull
    private String id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String nickName;

    @JsonFormat(pattern="yyyy-MM-dd", timezone="US/Central")
    LocalDate dateOfBirth;
}
