package com.ibeer.realm;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.util.SessionUtil;

public class CustomRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		//加载用户权限
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		Map<String, Object> returnResult = responseMessage.getReturnResult();
		List permissions = (List)returnResult.get("result");
		simpleAuthorInfo.addStringPermissions(permissions);
		//将权限信息放入session
		Session session = SessionUtil.getSession();
		session.setAttribute(SessionUtil.SESSION_PERMISSION, permissions);
		
		return simpleAuthorInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
