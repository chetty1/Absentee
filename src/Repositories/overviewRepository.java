package Repositories;

import Model.OverView;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by chett_000 on 2018/03/09.
 */

public interface overviewRepository  extends MongoRepository<OverView,String>{

List<OverView> findByApprovedFalse();
}
