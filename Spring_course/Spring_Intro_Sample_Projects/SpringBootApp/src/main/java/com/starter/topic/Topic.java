package com.starter.topic;

public class Topic {

	private String Id;
	private String name;
	private String desc;
	
	public Topic(){
		
	}
	
	public Topic(String id, String name, String desc) {
		super();
		Id = id;
		this.name = name;
		this.desc = desc;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
