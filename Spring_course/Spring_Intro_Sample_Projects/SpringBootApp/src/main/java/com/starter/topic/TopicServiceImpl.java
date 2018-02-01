package com.starter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

	private List<Topic> topics = new ArrayList<Topic>(Arrays.asList( 
			new Topic("Spring","Spring Framework","Spring Framework Description"),
            new Topic("Java","Core Java","Core Java Description"),
            new Topic("Javascript","Javascript","Javascript Description")));
	
	public List<Topic> getAllTopics() {
		// TODO Auto-generated method stub
		return topics;
	}

	public Topic getTopic(String id) {
//		topics.stream().filter(t -> t.getId().equalsIgnoreCase(id)).findFirst().get();
		return null;
	}

	public void addTopic(Topic topic) {
		topics.add(topic);

	}

	public void updateTopic(Topic topic, String id) {
		for(int i= 0 ; i< topics.size();i++){
			Topic t = topics.get(i);
			if(t.getId().equalsIgnoreCase(id)){
				topics.set(i,topic);
				return;
			}
		}

	}

	public void deleteTopic(String id) {
//		topics.removeIf(t -> t.getId().equalsIgnoreCase(id));

	}

}
