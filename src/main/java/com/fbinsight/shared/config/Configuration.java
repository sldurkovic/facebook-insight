package com.fbinsight.shared.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component
public class Configuration implements Serializable{

	@Autowired
	private SocialParameters socialParams;

	public Configuration() {
	}
	
	public SocialParameters getSocialParams() {
		return socialParams;
	}

}
