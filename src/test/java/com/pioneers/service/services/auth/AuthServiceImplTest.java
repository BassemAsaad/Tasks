package com.pioneers.service.services.auth;

import com.pioneers.service.error.exceptions.LoginException;
import com.pioneers.service.error.exceptions.RegisterException;
import com.pioneers.service.model.dtos.requests.StudentLogin;
import com.pioneers.service.model.dtos.requests.StudentSignup;
import com.pioneers.service.model.entities.Student;
import com.pioneers.service.repositories.students.StudentRepository;
import com.pioneers.service.utils.CredentialsHelper;
import com.pioneers.service.utils.mappers.StudentMapper;
import com.pioneers.service.utils.time.TimeHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    private Student student;
    private StudentSignup studentSignup;
    private StudentLogin studentLogin;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .id("1")
                .fullName("Bassem Asaad")
                .email("ba@gmail.com")
                .phone("01014148059")
                .password("123456")
                .isLogin(false)
                .build();

        studentSignup = StudentSignup.builder()
                .firstName("Bassem")
                .lastName("Asaad")
                .email("ba@gmail.com")
                .age(19)
                .phoneNumber("01014148059")
                .password("123456")
                .build();

        studentLogin = StudentLogin.builder()
                .email("ba@gmail.com")
                .password("123456")
                .build();
    }

    @Test
    void shouldRegisterStudentSuccessfullyWhenEmailAndPhoneNotRegistered(){

        try (MockedStatic<StudentMapper> studentMapperMock = mockStatic(StudentMapper.class)) {
            // Arrange
            when(studentRepository.findByEmail(any()))
                    .thenReturn(Optional.empty());
            when(studentRepository.findByPhone(any()))
                    .thenReturn(Optional.empty());

            studentMapperMock
                    .when(() -> StudentMapper.toStudent(any()))
                    .thenReturn(student);

            // Act
            String result = authService.registerStudent(studentSignup);

            // Assert
            assertEquals("Student registered successfully", result);

            verify(studentRepository, times(1)).save(any(Student.class));
            verify(studentRepository, times(1)).findByEmail(studentSignup.email());
            verify(studentRepository, times(1)).findByPhone(studentSignup.phoneNumber());
        }

    }

    @Test
    void shouldThrowExceptionWhenEmailRegistered(){
        when(studentRepository.findByEmail(any()))
                .thenReturn(Optional.of(student));

        assertThrows(RegisterException.class, () -> authService.registerStudent(studentSignup));
        verify(studentRepository, times(1)).findByEmail(studentSignup.email());
        verify(studentRepository, never()).findByPhone(studentSignup.phoneNumber());
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void shouldThrowExceptionWhenPhoneRegistered(){
        //mook exception
        //                  when(studentRepository.findByPhone(any()))
        //                          .thenThrow(new RegisterException("Phone is already used in system"));*
        //                  RegisterException exception = assertThrows(
        //                          RegisterException.class,
        //                          () -> authService.registerStudent(studentSignup));
        //                 assertEquals("Phone is already used in system", exception.getMessage());
        when(studentRepository.findByPhone(any()))
                .thenReturn(Optional.of(student));

        assertThrows(RegisterException.class, () -> authService.registerStudent(studentSignup));
        verify(studentRepository, times(1)).findByPhone(studentSignup.phoneNumber());
        verify(studentRepository, times(1)).findByEmail(studentSignup.email());
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void shouldLoginStudentSuccessfullyWhenEmailAndPasswordMatched(){
        try(MockedStatic<CredentialsHelper> credentialsHelperMock = mockStatic(CredentialsHelper.class);
            MockedStatic<TimeHelper> timeHelperMock = mockStatic(TimeHelper.class)
        ){
            // arrange
            Timestamp expectedTime = new Timestamp(System.currentTimeMillis());
            when(studentRepository.findByEmail(any()))
                    .thenReturn(Optional.of(student));

            credentialsHelperMock
                    .when(()-> CredentialsHelper.verifyPassword(studentLogin.password(), student.getPassword()))
                    .thenReturn(true);
            timeHelperMock
                    .when(TimeHelper::currentTimestamp)
                    .thenReturn(expectedTime);

            // act
            String result = authService.loginStudent(studentLogin);

            // assert
            assertEquals("Login successful", result);
            assertTrue(student.isLogin());
            assertEquals(expectedTime, student.getLastLoginAt());
            verify(studentRepository, times(1)).findByEmail(studentLogin.email());
        }
    }

    @Test
    void shouldThrowExceptionWhenStudentNotFound(){
        // arrange
        when(studentRepository.findByEmail(any()))
                .thenReturn(Optional.empty());

        // act


        // assert
        assertThrows(LoginException.class, () -> authService.loginStudent(studentLogin));
        verify(studentRepository, times(1)).findByEmail(studentLogin.email());

    }

    @Test
    void shouldThrowExceptionWhenStudentAlreadyLoggedIn(){
        // arrange
        student.setLogin(true);
        when(studentRepository.findByEmail(any()))
                .thenReturn(Optional.of(student));

        // act


        // assert
        assertThrows(LoginException.class, () -> authService.loginStudent(studentLogin));
        verify(studentRepository, times(1)).findByEmail(studentLogin.email());
    }

    @Test
    void shouldThrowExceptionWhenStudentPasswordNotMatched(){
        try(MockedStatic<CredentialsHelper> credentialsHelperMock = mockStatic(CredentialsHelper.class)){
            // arrange
            when(studentRepository.findByEmail(any()))
                    .thenReturn(Optional.of(student));
            credentialsHelperMock.when(() ->
                    CredentialsHelper
                            .verifyPassword(studentLogin.password(), student.getPassword()))
                    .thenReturn(false);

            // act
            // assert
            assertThrows(LoginException.class, ()-> authService.loginStudent(studentLogin));
            verify(studentRepository, times(1)).findByEmail(studentLogin.email());

        }
    }

}
