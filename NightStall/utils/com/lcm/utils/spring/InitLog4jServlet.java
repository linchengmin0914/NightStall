package com.lcm.utils.spring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitLog4jServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
		System.out.println("************tell mybatis use log4j************");
	}
}
