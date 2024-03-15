package com.wolfcode.content.service;

import com.wolfcode.content.dto.ContentRequest;
import com.wolfcode.content.dto.ContentResponse;
import com.wolfcode.content.entity.Content;
import com.wolfcode.content.repository.ContentRepository;
import com.wolfcode.content.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    public void addCourseContent(MultipartFile file, ContentRequest contentRequest) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Please select a file to upload");
        }

        byte[] contentBytes = ImageUtils.compressImage(file.getBytes());

        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        contentRequest.setFileName(fileName); // Set filename (optional for logging)

        Content content = Content.builder()
                .contentCode(UUID.randomUUID().toString().toUpperCase().substring(0, 8))
                .unitCode(contentRequest.getUnitCode())
                .instructor(contentRequest.getInstructor())
                .instructions(contentRequest.getInstructions())
                .content(contentBytes) // Set content bytes
                .build();

        contentRepository.save(content);
        log.info("new course content: {} : {} has been added", content.getContentCode(), content.getUnitCode());
    }


    private ContentResponse mapToContentResponse(Content content) {
        return ContentResponse.builder()
                .contentCode(content.getContentCode())
                .unitCode(content.getUnitCode())
                .instructor(content.getInstructor())
                .instructions(content.getInstructions())
                .content(ImageUtils.decompressImage(content.getContent()))
                .build();
    }

    public List<ContentResponse> getContentByUnitCode(String unitCode) {
        List<Content> contents = contentRepository.findByUnitCode(unitCode);
        return contents.stream().map(content -> mapToContentResponse(content)).toList();
    }

    public List<ContentResponse> getContentByInstructor(String instructor) {
        List<Content> contents = contentRepository.findByInstructor(instructor);
        return contents.stream().map(this::mapToContentResponse).toList();
    }

    public void deleteContentByContentCode(String contentCode) {
        contentRepository.deleteByContentCode(contentCode);
    }
}
