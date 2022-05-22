package uz.general.generaltask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.general.generaltask.entity.Attachment;
import uz.general.generaltask.service.AttachmentService;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {
//    private final AttachmentService attachmentService;
//
//    @PostMapping
//    public ResponseEntity<Attachment> upload(@RequestParam("file") MultipartHttpServletRequest file){
//        return ResponseEntity.ok(attachmentService.uploadAttachment(file));
//    }
}
