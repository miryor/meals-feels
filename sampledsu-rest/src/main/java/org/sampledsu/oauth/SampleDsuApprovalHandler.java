package org.sampledsu.oauth;

import java.util.Collection;

import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class SampleDsuApprovalHandler extends TokenServicesUserApprovalHandler {
	private static Logger logger = LoggerFactory.getLogger( SampleDsuApprovalHandler.class );

	private Collection<String> autoApproveClients = new HashSet<String>();
	
	private boolean useTokenServices = true;

	/**
	 * @param useTokenServices the useTokenServices to set
	 */
	public void setUseTokenServices(boolean useTokenServices) {
		this.useTokenServices = useTokenServices;
	}

	/**
	 * @param autoApproveClients the auto approve clients to set
	 */
	public void setAutoApproveClients(Collection<String> autoApproveClients) {
		this.autoApproveClients = autoApproveClients;
	}
	
	@Override
	public AuthorizationRequest updateBeforeApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		return super.updateBeforeApproval(authorizationRequest, userAuthentication);
	}

	/**
	 * Allows automatic approval for a white list of clients in the implicit grant case.
	 * 
	 * @param authorizationRequest The authorization request.
	 * @param userAuthentication the current user authentication
	 * 
	 * @return Whether the specified request has been approved by the current user.
	 */
	@Override
	public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		if ( logger.isDebugEnabled() ) logger.debug( "isApproved( " + authorizationRequest.getClientId() + ", " + userAuthentication.getName() );
		
		// If we are allowed to check existing approvals this will short circuit the decision
		if (useTokenServices && super.isApproved(authorizationRequest, userAuthentication)) {
			return true;
		}

		if (!userAuthentication.isAuthenticated()) {
			if ( logger.isDebugEnabled() ) logger.debug( "User not authenticated" );
			return false;
		}

		String flag = authorizationRequest.getApprovalParameters().get(AuthorizationRequest.USER_OAUTH_APPROVAL);
		if ( logger.isDebugEnabled() ) logger.debug( "Flag: " + flag );
		boolean approved = flag != null && flag.toLowerCase().equals("true");

		return approved
				|| (authorizationRequest.getResponseTypes().contains("token") && autoApproveClients
						.contains(authorizationRequest.getClientId()));

	}
	
}
