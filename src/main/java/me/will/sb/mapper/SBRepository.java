package me.will.sb.mapper;


import me.will.sb.model.resp.App;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SBRepository extends MongoRepository<App, Integer> {

}