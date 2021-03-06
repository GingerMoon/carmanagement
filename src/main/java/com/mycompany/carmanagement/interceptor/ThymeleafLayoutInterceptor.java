package com.mycompany.carmanagement.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.mycompany.carmanagement.annotaion.Layout;

public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

	private static final String DEFAULT_LAYOUT = "blank";
	private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

	private String defaultLayout = DEFAULT_LAYOUT;
	private String viewAttributeName = DEFAULT_VIEW_ATTRIBUTE_NAME;

	public void setDefaultLayout(String defaultLayout) {
		Assert.hasLength(defaultLayout);
		this.defaultLayout = defaultLayout;
	}

	public void setViewAttributeName(String viewAttributeName) {
		Assert.hasLength(defaultLayout);
		this.viewAttributeName = viewAttributeName;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		if (modelAndView == null || !modelAndView.hasView()) {
			return;
		}
		String originalViewName = modelAndView.getViewName();
		if (isRedirectOrForward(originalViewName)) {
			return;
		}
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			String layoutName = getLayoutName(handlerMethod);
			modelAndView.setViewName(layoutName);
			modelAndView.addObject(this.viewAttributeName, originalViewName);
		}
	}

	private boolean isRedirectOrForward(String viewName) {
		return viewName.startsWith("redirect:") || viewName.startsWith("forward:");
	}

	private String getLayoutName(HandlerMethod handlerMethod) {
		Layout layout = getMethodOrTypeAnnotation(handlerMethod);
		if (layout == null) {
			return this.defaultLayout;
		} else {
			return layout.value();
		}
	}

	private Layout getMethodOrTypeAnnotation(HandlerMethod handlerMethod) {
		Layout layout = handlerMethod.getMethodAnnotation(Layout.class);
		if (layout == null) {
			return handlerMethod.getBeanType().getAnnotation(Layout.class);
		}
		return layout;
	}
}