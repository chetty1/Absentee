package Repositories;

import Model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface staffRepository extends MongoRepository<Staff, String> {

    Staff findByUsername(String username);

    Staff findByName(String name);
    List<Staff> findByNameLike(String name);

}
