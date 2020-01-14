package com.ibeer.realm;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.BaseException;
import com.ibeer.common.constant.ConstantBase;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.conector.AccountConnector;
import com.ibeer.dto.MySimpleAuthorizationInfo;
import com.ibeer.dto.MyUsernamePasswordToken;
import com.ibeer.dto.UserV;
import com.ibeer.util.JMMD5;
import com.ibeer.util.SessionUtil;

public class CustomRealm extends AuthorizingRealm {
	@Autowired
   AccountConnector accountConnector;
	@Override
	//授权，即权限验证，验证某个已认证的用户是否拥有某个权限；
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		//加载用户权限
		MySimpleAuthorizationInfo simpleAuthorInfo = new MySimpleAuthorizationInfo();
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
			//得到用户信息且进行加密
			MyUsernamePasswordToken authToken =(MyUsernamePasswordToken)token;
			String username = authToken.getUsername();
			char[] password = authToken.getPassword();
			byte[] salt = null;
			Object md5 = new SimpleHash("MD5", password, ByteSource.Util.bytes(salt), 1024);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("oauthId",Base64.getEncoder().encodeToString(username.getBytes()));
			jsonObject.put("credential", md5);
			 //去数据库进行验证
			ResponseMessage responseMessage = accountConnector.login(jsonObject, authToken.getRequest());
			if("00".equals(responseMessage.getResultCode())&&StringUtils.isEmpty(responseMessage.getReturnResult())) {
				throw new BaseException(responseMessage.getResultMessage());
			}
			Map<String, Object> map = responseMessage.getReturnResult();
			Object object = map.get("result");
			UserV userV = JSONObject.parseObject(JSONObject.toJSONString(object), UserV.class);
		    simpleAuthenticationInfo = new SimpleAuthenticationInfo(userV,md5, getName());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(ConstantBase.FAILED_SYSTEM_ERROR);
		}
		
		
		return simpleAuthenticationInfo;
	}

}
