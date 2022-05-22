package uz.general.generaltask.service;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AttachmentServiceTest {
//    @Test
//    public void successfullySetAvatar() throws Exception {
//        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.png");
//        final MockMultipartFile avatar = new MockMultipartFile("file", "test.png", "image/png", inputStream);
//
//        doNothing().when(service).setAvatar(1L, avatar);
//
//        final MvcResult result = mockMvc.perform(fileUpload(urlPath).file(avatar))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        verify(service).setAvatar(1L, avatar);
//    }
}