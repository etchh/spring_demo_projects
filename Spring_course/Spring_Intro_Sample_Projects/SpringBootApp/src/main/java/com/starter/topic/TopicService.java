package com.starter.topic;

import java.util.List;

import org.springframework.stereotype.Service;

public interface TopicService {

	List<Topic> getAllTopics();
	
	Topic getTopic(String id);
	
	void addTopic(Topic topic);
	
	void updateTopic(Topic topic, String id);
	
	void deleteTopic(String id);
}
