package com.LiveConnect.Service;

import com.LiveConnect.Repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.LiveConnect.entity.Video;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@Service
public class videoService {

    @Autowired
    private VideoRepository videoRepository;

    public String saveVideo(MultipartFile file, String id, String title, String description, String uploaderId) throws IOException {
        String filePath = "/Users/abhinavkumargaur/Documents/Coding_workspace/Resume_Projects/Proj2_videoStreaming/LiveConnect 2/src/main/resources/static" + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        // Create Video entity with all metadata
        Video video = new Video();
        video.setId(id);
        video.setTitle(title);
        video.setDescription(description);
        video.setUploaderId(uploaderId);
        video.setUploadDate(new Date());
        video.setFilePath(filePath);
        video.setViews(0);
        video.setLikes(0);

        // Save video metadata in repository
        videoRepository.save(video);
        return filePath;
    }

    public void transcodeVideo(String videoPath) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i", videoPath, "-vcodec", "libx264", videoPath + "_transcoded.mp4");
        processBuilder.start();
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Optional<Video> getVideoById(String id) {
        return videoRepository.findById(id);
    }

}
