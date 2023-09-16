package az.atl.msuser;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.model.consts.Role;
import az.atl.msuser.model.dto.AdminDto;
import az.atl.msuser.model.validator.PasswordConstraintValidator;
import az.atl.msuser.service.AdminService;
import az.atl.msuser.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MsUserApplicationTests {


    @Test
    void contextLoads() {

    }

}
