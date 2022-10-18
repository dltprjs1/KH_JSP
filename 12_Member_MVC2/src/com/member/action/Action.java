package com.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	// 추상 메서드
	String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
