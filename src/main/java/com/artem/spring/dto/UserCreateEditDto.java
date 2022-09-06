package com.artem.spring.dto;

import com.artem.spring.database.entity.Role;
import com.artem.spring.validation.UserInfo;
import com.artem.spring.validation.group.CreateAction;
import com.artem.spring.validation.group.UpdateAction;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = UpdateAction.class)
public class UserCreateEditDto {

    @Email(message = "Please provide correct email format")
    String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

//    @NotBlank(message = "Please enter a valid first name")
    @Size(min = 3, max = 64, message = "Size must be between 3 and 64 characters")
    String firstname;

//    @NotBlank(message = "Please enter a valid last name")
    String lastname;

    Role role;

    Integer companyId;

}
