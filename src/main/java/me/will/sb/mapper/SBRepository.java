package me.will.sb.mapper;


import me.will.sb.entity.App;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SBRepository extends MongoRepository<App, Integer> {

}