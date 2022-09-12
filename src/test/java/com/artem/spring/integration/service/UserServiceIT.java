package com.artem.spring.integration.service;


import com.artem.spring.database.entity.Role;
import com.artem.spring.dto.UserCreateEditDto;
import com.artem.spring.dto.UserReadDto;
import com.artem.spring.integration.IntegrationTestBase;
import com.artem.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

;


@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;
    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() throws IOException {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "testPassword",
                LocalDate.now(),
                "Test",
                "Test",
                Role.USER,
                COMPANY_1 ,
                new MockMultipartFile("test.xlsx", new byte[0]));
        UserReadDto actualResult = userService.create(userDto);
        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertSame(userDto.getRole(), actualResult.getRole());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
    }

    @Test
    void update() throws IOException {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                "testPassword",
                LocalDate.now(),
                "Test",
                "Test",
                Role.USER,
                COMPANY_1,
                new MockMultipartFile("test.xlsx", new byte[0]));
        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertSame(userDto.getRole(), user.getRole());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-1234L));
        assertTrue(userService.delete(USER_1));
    }
}
