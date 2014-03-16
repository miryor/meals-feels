package org.sampledsu.common.dao.mongo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import org.sampledsu.common.models.Pain;
import org.sampledsu.common.dao.mongo.PainRepository;

/**
 * Our mvc Service for handling Pain related actions.
 * Expectation for a PainRepository to a exist.
 * @author royrim
 *
 */
@Service
public class PainService {

	@Autowired
	private PainRepository painRepository;
	
	public void insertPain( Pain pain ) {
		pain.setId(UUID.randomUUID().toString());
		painRepository.save( pain );
	}
	
	public void deletePain( Pain pain ) {
		painRepository.delete( pain );
	}
	
	public void updatePain( Pain pain ) {
		painRepository.save( pain );
	}
	
	public List<Pain> userPainList( String user ) {
		Sort sort = new Sort(Direction.ASC, "entryDate");
		return painRepository.findByUser( user, sort );
	}
	
}
