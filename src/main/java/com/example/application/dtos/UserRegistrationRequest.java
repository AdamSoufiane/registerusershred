package com.example.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Email
    @NotNull
    @ApiModelProperty(notes = "The email address provided by the user during registration. It must be in a valid email format and cannot be null.")
    private String email;

    @NotNull
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\S+$).{8,20}$")
    @ApiModelProperty(notes = "The password chosen by the user during registration. It must adhere to a specific regex pattern for password complexity and have a specified size range.")
    private String password;

    @NotBlank
    @ApiModelProperty(notes = "The full name of the user provided during registration. It cannot be blank.")
    private String fullName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(notes = "The timestamp when the UserRegistrationRequest object was created, used for auditing and logging.")
    private Instant creationTimestamp;

    public UserRegistrationRequest(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.creationTimestamp = Instant.now();
    }

    @Override
    public String toString() {
        return "UserRegistrationRequest{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRegistrationRequest that = (UserRegistrationRequest) o;

        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(password, that.password)) return false;
        if (!Objects.equals(fullName, that.fullName)) return false;
        return Objects.equals(creationTimestamp, that.creationTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, fullName, creationTimestamp);
    }

    public UserRegistrationRequest copy() {
        return new UserRegistrationRequest(this.email, this.password, this.fullName);
    }
}
