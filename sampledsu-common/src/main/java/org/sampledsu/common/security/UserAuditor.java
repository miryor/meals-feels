package org.sampledsu.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Really simplistic implementation to include User name
 * when our data objects get stored.
 * @author royrim
 *
 */
public class UserAuditor implements AuditorAware<String> {
	private static Logger logger = LoggerFactory.getLogger( UserAuditor.class );
	public String getCurrentAuditor() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName(); 
		if ( logger.isDebugEnabled() ) logger.debug( "getCurrentAuditor: " + name );
		return name;
	}

}
