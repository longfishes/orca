package com.longfish.orca.service;

import com.longfish.orca.pojo.dto.ContentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AIService {

    String createSession();

    List<String> listSession();

    String smartTitle(ContentDTO contentDTO);

    String smartSummary(ContentDTO contentDTO);

    String ocrPredict(MultipartFile file);
}
