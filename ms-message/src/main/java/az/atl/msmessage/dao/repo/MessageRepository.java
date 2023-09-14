package az.atl.msmessage.dao.repo;

import az.atl.msmessage.dao.entity.MessageEntity;
import az.atl.msmessage.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
//    List<MessageEntity> findByFromWhomIdOrToWhomId(Long id, Long id1);
//    Optional<UserEntity> findByUsername(String name);

}

