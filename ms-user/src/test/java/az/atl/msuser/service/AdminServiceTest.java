package az.atl.msuser.service;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.model.consts.Role;
import az.atl.msuser.model.dto.AdminDto;
import az.atl.msuser.model.validator.PasswordConstraintValidator;
import az.atl.msuser.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdminServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @InjectMocks
    PasswordConstraintValidator password;

    @Test
    void testGetByIdAdmin() {
        UserEntity userEntity = UserEntity.builder().id(1L).name("David").surname("Alex").username("alex123").email("david2@mail.ru").jobTitle("Java Developer").role(Role.ADMIN).build();
        AdminDto adminDto = AdminDto.builder().id(1L).name("David").surname("Alex").username("alex123").email("david2@mail.ru").jobTitle("Java Developer").role(Role.ADMIN).build();
        when(userRepo.findById(1L)).thenReturn(Optional.of(userEntity));

        AdminDto result = adminServiceImpl.getUserById(1L);


        assertEquals(adminDto.getId(), result.getId());
    }

//    @Test
//    void deleteByIdAdmin(){
//        Long id= 1L;
//        UserEntity user=Mockito.mock(UserEntity.class);
//
//        Mockito.when(userRepo.save(ArgumentMatchers.any(UserEntity.class))).thenReturn(user);
//
//        adminServiceImpl.deleteUserById(id);
//
//        verify(userRepo).deleteById(id);
//
//
//    }


}
