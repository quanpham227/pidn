package com.pivinadanang.services;

import com.pivinadanang.dto.CloudinaryDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public interface ICloudinaryService {

    CloudinaryDTO upload(MultipartFile file, String folderName);

    CloudinaryDTO update(String publicId , MultipartFile file);

    void delete(String publicId);

    ByteArrayResource download(String publicId, int width, int height,
                               boolean isAvatar);
}
