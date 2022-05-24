package uz.general.generaltask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.general.generaltask.service.AttachmentService;


@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseEntity<Long> upload(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(attachmentService.uploadAttachment(file).getId());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> getFile(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(attachmentService.getAttachment(id));
    }
}
