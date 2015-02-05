/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.carfinance.core.filter;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class HiddenHttpMethodFilter extends OncePerRequestFilter {

	/** Default method parameter: <code>_method</code> */
	public static final String DEFAULT_METHOD_PARAM = "_method";

	private String methodParam = DEFAULT_METHOD_PARAM;

	/**
	 * Set the parameter name to look for HTTP methods.
	 * 
	 * @see #DEFAULT_METHOD_PARAM
	 */
	public void setMethodParam(String methodParam) {
		Assert.hasText(methodParam, "'methodParam' must not be empty");
		this.methodParam = methodParam;
	}

	@Override  
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		logger.debug("@@@@@@@@@@@@@@@@@@@进入浏览器请求方法设置流程@@@@@@@@@@@@@@@@@@@");
		logger.debug("@@@@@@@@@@@@@@@@@@@原始的请求方法 "+ request.getMethod().toUpperCase() + "@@@@@@@@@@@@@@@@@@@");
		String paramValue = request.getParameter(this.methodParam);
		if ("POST".equals(request.getMethod()) && StringUtils.hasLength(paramValue)) {
			String method = paramValue.toUpperCase(Locale.ENGLISH);
			logger.debug("@@@@@@@@将当前的浏览器请求方法转换为" + method + "请求方法@@@@@@@@");
			HttpServletRequest wrapper = new HttpMethodRequestWrapper(request, method);
			filterChain.doFilter(wrapper, response);
		} else {
			logger.debug("@@@@@@@@@@@@@@@@@@@@本次请求不需要进行方法转换@@@@@@@@@@@@@@@@@@@@");
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * Simple {@link HttpServletRequest} wrapper that returns the supplied
	 * method for {@link HttpServletRequest#getMethod()}.
	 */
	private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

		private final String method;

		public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
			super(request);
			this.method = method;
		}

		@Override
		public String getMethod() {
			return this.method;
		}
	}

}
