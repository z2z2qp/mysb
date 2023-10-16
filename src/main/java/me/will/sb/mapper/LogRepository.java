package me.will.sb.mapper;

import me.will.sb.entity.Log;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 庄佳彬
 * @since 2023/10/14 16:23
 */
@Repository
public interface LogRepository extends MongoRepository<Log, ObjectId> {
}
