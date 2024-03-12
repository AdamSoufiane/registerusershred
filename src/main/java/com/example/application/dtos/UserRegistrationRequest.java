package com.example.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
}
