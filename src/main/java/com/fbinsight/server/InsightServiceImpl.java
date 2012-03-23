package com.fbinsight.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gwtwidgets.server.spring.GWTRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import com.fbinsight.shared.FType;
import com.fbinsight.shared.Item;
import com.fbinsight.shared.Statistics;
import com.fbinsight.shared.config.Configuration;
import com.fbinsight.shared.services.InsightService;

/**
 * The server side implementation of the RPC service.
 */
@Service
@GWTRequestMapping("/fbinsight/gwt.rpc")
public class InsightServiceImpl implements InsightService {

	private static Log log = LogFactory.getLog(InsightServiceImpl.class);

	@Autowired
	Configuration config;
	
	public InsightServiceImpl() {
	}

	public Configuration getConfig() {
		return config;
	}
	
	public void facebook(String accessToken){
		log.debug(":: Access Token :: "+accessToken);
		Facebook facebook = new FacebookTemplate(accessToken);
		
//		FacebookProfile profile = facebook.userOperations().getUserProfile();
		List<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
		for (FacebookProfile friend : friends) {
			List<org.springframework.social.facebook.api.Page> pages = facebook.likeOperations().getPagesLiked(friend.getId());
			for (org.springframework.social.facebook.api.Page page : pages) {
				log.debug("::::"+page.getCategory());
			}
		}
	}

	public Statistics getLikesStatistics(String token, String query, int limit) {
		log.debug(":: Getting likes statistics :: token="+token+", limit ="+limit+", query ="+query);
		Statistics statistics = new Statistics();
		if(token != null){
			Facebook facebook = new FacebookTemplate(token);
			List<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
			
			for (int i = 0; i < friends.size(); i++) {
				if(i== limit){
					break;
				}
				FacebookProfile friend = friends.get(i);
				List<Page> pages = facebook.likeOperations().getPagesLiked(friend.getId());
				for (Page page : pages) {
					if(FType.isSupportedType(page.getCategory())){
						if(page.getName().contains(query)){
							statistics.addItem(new Item(FType.getTypeForFBType(page.getCategory())));
						}
					}
				}
			}
		}
		
		return statistics;
	}

	public Statistics getAgeStatistics(String token, String query, int limit) {
		log.debug(":: Getting age statistics :: token="+token+", limit ="+limit+", query ="+query);
		Statistics statistics = new Statistics();
		if(token != null){
			Facebook facebook = new FacebookTemplate(token);
			List<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			for (int i = 0; i < friends.size(); i++) {
				if(i== limit){
					break;
				}
				FacebookProfile friend = friends.get(i);
				if(friend.getBirthday()	!= null){
					List<Page> pages = facebook.likeOperations().getPagesLiked(friend.getId());
					for (Page page : pages) {
						if(FType.isSupportedType(page.getCategory())){
							if(page.getName().contains(query)){
								try {
										Date date = sdf.parse(friend.getBirthday());
										statistics.addItem(new Item(getAgeRange(date)));
										if(friend.getGender()	!= null){
											statistics.addItem(new Item(friend.getGender()));
										}
								} catch (ParseException e) {
	//								e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		
		return statistics;
	}

	public Statistics getLocationStatistics(String token, String query,
			int limit) {
		log.debug(":: Getting location statistics :: token="+token+", limit ="+limit+", query ="+query);
		Statistics statistics = new Statistics();
		if(token != null){
			Facebook facebook = new FacebookTemplate(token);
			List<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
			
			for (int i = 0; i < friends.size(); i++) {
				if(i== limit){
					break;
				}
				FacebookProfile friend = friends.get(i);
				if(friend.getLocation() != null && friend.getLocation().getName() != null){
					List<Page> pages = facebook.likeOperations().getPagesLiked(friend.getId());
					for (Page page : pages) {
						if(FType.isSupportedType(page.getCategory())){
							if(page.getName().contains(query)){
								statistics.addItem(new Item(friend.getLocation().getName()));
							}
						}
					}
				}
			}
		}
		
		return statistics;
	}

	
	public String getAgeRange(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		GregorianCalendar friend = new GregorianCalendar();
		friend.setTime(date);
		int year = friend.get(GregorianCalendar.YEAR);
		int currentYear = calendar.get(GregorianCalendar.YEAR);
		int difference = currentYear - year;
//		System.out.println(year);
//		System.out.println(currentYear);
		if(difference <18){
			return "under 18";
		}
		if(difference >=18 && difference <=29){
			return "Aged 18-29";
		}
		if(difference >=30 && difference <=44){
			return "Aged 30-44";
		}
		if(difference >=45){
			return "Aged 45+";
		}
		
		return null;
	}
}
