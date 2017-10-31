package com.retail.asales.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Auto-generated method stub
		// place your code here

		try {
			// check whether session variable is set
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);
			// allow user to proceed if URL is login.xhtml or user logged in or
			// user is accessing any page in //public folder
			String reqURI = req.getRequestURI();
			if (reqURI.indexOf("/login.xhtml") >= 0 || reqURI.indexOf("/register.xhtml") >= 0 || (ses != null && ses.getAttribute("user") != null)
					|| reqURI.contains("javax.faces.resource")) {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			} else { // user didn't log in but asking for a page that is not
				// allowed so take user to login page
				res.sendRedirect(req.getContextPath() + "/faces/login.xhtml"); // Anonymous
				// user.
				// Redirect
				// to
				// login
				// page
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// Auto-generated method stub
	}

}
