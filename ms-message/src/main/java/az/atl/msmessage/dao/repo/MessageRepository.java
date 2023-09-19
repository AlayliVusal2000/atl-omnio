package az.atl.msmessage.dao.repo;

import az.atl.msmessage.dao.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findBySenderId(Long senderId);

    List<MessageEntity> findByRecipientId(Long recipientId);


}

