package com.HealthPoint.util.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class GetValueFromSession {
	public Map<String, Object> getSessionUserMap(HttpSession session) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", session.getAttribute("name"));
        userMap.put("email", session.getAttribute("email"));
        userMap.put("userName", session.getAttribute("userName"));
        return userMap;
    }
}
