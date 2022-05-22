package uz.general.generaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.general.generaltask.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
