package az.atl.msuser.dao.repo;

import az.atl.msuser.dao.entity.MessageEntity;
import az.atl.msuser.dao.entity.UserEntity;
import org.mapstruct.control.MappingControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findByFromWhomIdOrToWhomId(Authentication id, UserEntity id1);

}

