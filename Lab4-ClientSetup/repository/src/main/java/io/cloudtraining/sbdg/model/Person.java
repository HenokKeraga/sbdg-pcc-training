package io.cloudtraining.sbdg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.time.LocalDate;


@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
//@Builder(toBuilder = true)
@Region("person")
public class Person {

    @Id @NonNull
    private String id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String nickName;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd", timezone="US/Central")
    LocalDate dateOfBirth;

    private Person(Builder builder) {
        setId(builder.id);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setNickName(builder.nickName);
        setDateOfBirth(builder.dateOfBirth);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public static final class Builder {
        private @NonNull String id;
        private @NonNull String firstName;
        private @NonNull String lastName;
        private String nickName;
        private LocalDate dateOfBirth;

        private Builder() {
        }

        public Builder id(@NonNull String val) {
            id = val;
            return this;
        }

        public Builder firstName(@NonNull String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(@NonNull String val) {
            lastName = val;
            return this;
        }

        public Builder nickName(String val) {
            nickName = val;
            return this;
        }

        public Builder dateOfBirth(LocalDate val) {
            dateOfBirth = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
