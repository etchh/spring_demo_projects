package coursedataapi.coursedataapi.topic;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String> {
	
	Topic findById(String id);
	
	Topic findTopicByName(String name);
	
	Topic findByTopicId(String id);

}
