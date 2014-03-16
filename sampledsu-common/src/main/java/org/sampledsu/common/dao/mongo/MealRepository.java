package org.sampledsu.common.dao.mongo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.sampledsu.common.models.*;

/**
 * Spring data mongo repository will add some magic here with
 * base functionality like save and find.  It will also parse
 * method names like findByUser and the passed in parameters
 * to auto-generate the mongo code that matches your intent.
 * @author royrim
 *
 */
@Repository
public interface MealRepository extends MongoRepository<Meal,String> {

	List<Meal> findByUser( String user/*, Pageable pageable*/ );
}
