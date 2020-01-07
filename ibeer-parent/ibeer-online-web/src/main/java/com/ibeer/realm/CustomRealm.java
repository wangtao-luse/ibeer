package com.ibeer.realm;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ctc.wstx.util.StringUtil;
import com.ibeer.common.BaseException;
import com.ibeer.common.constant.ConstantBase;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.conector.AccountConnector;
import com.ibeer.dto.MyUsernamePasswordToken;
import com.ibeer.dto.UserV;
import com.ibeer.util.MD5;
import com.ibeer.util.PBKDF2Util;
import com.ibeer.util.SessionUtil;

public class CustomRealm extends AuthorizingRealm {
	@Autowired
   AccountConnector accountConnector;
	@Override
	//授权，即权限验证，验证某个已认证的用户是否拥有某个权限；
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
	//身份认证/登录，验证用户是不是拥有相应的身份
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo simpleAuthenticationInfo = null;
		
		try {
			MyUsernamePasswordToken authToken =(MyUsernamePasswordToken)token;
			String username = authToken.getUsername();
			char[] password = authToken.getPassword();
			String salt = PBKDF2Util.generateSalt();
			String pbkdf2 = PBKDF2Util.getEncryptedPassword(String.valueOf(password),salt);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("oauthId",username);
			jsonObject.put("credential", pbkdf2);
			 
			ResponseMessage responseMessage = accountConnector.login(jsonObject, authToken.getRequest());
			if("00".equals(responseMessage.getResultCode())&&StringUtils.isEmpty(responseMessage.getReturnResult())) {
				throw new BaseException(responseMessage.getResultMessage());
			}
			Map<String, Object> map = responseMessage.getReturnResult();
			UserV userV = new JSONObject(map).toJavaObject(UserV.class);
		    simpleAuthenticationInfo = new SimpleAuthenticationInfo(userV, pbkdf2, username);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			throw new BaseException(ConstantBase.FAILED_SYSTEM_ERROR);
		}
		
		
		return simpleAuthenticationInfo;
	}

}
