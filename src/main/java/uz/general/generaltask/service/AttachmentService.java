package uz.general.generaltask.service;

import io.github.classgraph.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.general.generaltask.entity.Attachment;
import uz.general.generaltask.repository.AttachmentRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final String directory = "files";

    public Attachment uploadAttachment(
            MultipartFile file
    ){
        if (file != null){
            String originalName = file.getOriginalFilename();

            String[] split = originalName.split("\\.");
            String fileExtension = split[split.length - 1];

            if (!fileExtension.equals("jpg")){
                throw new RuntimeException("Sorry file extension isn't available. File extension must be .jpg or .JPG");
            }

            String name = UUID.randomUUID().toString() + "." + fileExtension;

            Attachment attachment = new Attachment();
            attachment.setOriginalName(originalName);
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            attachment.setName(name);

            attachmentRepository.save(attachment);

            Path path = Paths.get(directory + "/" + name);
            try {
                Files.copy(file.getInputStream(),path);
            } catch (IOException e) {
                throw new RuntimeException("File isn't upload");
            }

            return attachment;

        }
        return null;
    }

    public Attachment getAttachment(Long id){
        return attachmentRepository.findById(id).get();
    }

}
