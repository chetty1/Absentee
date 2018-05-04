package Repositories;

import Model.OverView;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by chett_000 on 2018/03/09.
 */

public interface overviewRepository  extends MongoRepository<OverView,String>{

List<OverView> findByApprovedFalse();

    @Query("{'staff.name':{$regex : \'?0\'}}")
    List<OverView> findByNameLike(String name);
}
