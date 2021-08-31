package com.example.financeiro.api.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financeiro.api.config.property.FinanceiroApiProperty;

@RestController
@RequestMapping("/tokens")
public class TokenResource {
	
	@Autowired
	private FinanceiroApiProperty financeiroApiProperty;
	
	@DeleteMapping("revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refresh_token", null);
			cookie.setHttpOnly(true);
			//cookie.setSecure(false); TODO em produção será true
			cookie.setSecure(financeiroApiProperty.getSeguranca().isEnableHttps());
			cookie.setPath(req.getContextPath() + "/oauth/token");
			cookie.setMaxAge(0);
			
			resp.addCookie(cookie);
			resp.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
