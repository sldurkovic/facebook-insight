
package com.fbinsight.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fbinsight.server.InsightServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/app-context.xml","/spring/*.xml" })
public class Test {
	
	private static String TOKEN = "AAACEdEose0cBADT6rwCD7dWPZA3YHxbqw6NG71DRATNHC5hjEvGeZAqvovOWKWbczUN0Eu0NAU7MJnzGgfDeBOG68fwNpHZBaqXqkwBlMZAEXoZBqzOdv";
	
	List<NameValuePair> qparams = new ArrayList<NameValuePair>();
	
	@Autowired
	InsightServiceImpl service;
	
	
	@org.junit.Test
	public void facebookTest(){
//		FacebookMapper mapper = new FacebookMapper(TOKEN);
//		System.out.println(mapper.getProfile("me"));
		
//		System.out.println(mapper.getStringFromContext("me", "picture"));
		
//		service.facebook(TOKEN);
//		System.out.println(		service.getLikesStatistics("AAACEdEose0cBAAIJdYqL9vBYqlapGKOxMHEMutlTre1GwuaTBHm7MWpqaXwB84BKvkw0pG54cj23k7rZC7LgpT9Btd72zwLvGqTN3hVlzSGNXh8t1"));
//		System.out.println(mapper.makeFProfil("me"));
		
		System.out.println(service.getAgeStatistics(TOKEN,"",20));
	}

}
