package com.LiveConnect.controller;

import ch.qos.logback.core.model.Model;
import com.LiveConnect.Service.videoService;
import com.LiveConnect.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos")
public class videoController {

    @Autowired
    private videoService videoSvc;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("uploaderId") String uploaderId) {

        try {
            // Save video file and metadata
            String videoPath = videoSvc.saveVideo(file, id, title, description, uploaderId);
            videoSvc.transcodeVideo(videoPath);

            // Return success response as JSON
            Map<String, String> response = new HashMap<>();
            response.put("message", "Video uploaded and transcoded successfully");
            response.put("path", videoPath);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Return error response as JSON
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Video upload failed");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoSvc.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    // Endpoint to get video by ID
    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable String id) {
        Optional<Video> video = videoSvc.getVideoById(id);
        if (video.isPresent()) {
            return new ResponseEntity<>(video.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
