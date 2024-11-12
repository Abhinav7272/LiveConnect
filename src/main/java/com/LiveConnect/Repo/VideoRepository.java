package com.LiveConnect.Repo;
import com.LiveConnect.entity.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface VideoRepository extends MongoRepository<Video, String> {


}


