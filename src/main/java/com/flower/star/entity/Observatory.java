package com.flower.star.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Observatory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	private String address;
	private String homepage;
	private String tel;
	private int views;
	private String image;
	
	private String createdate;
	
	@OneToMany(mappedBy = "observatory", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ObservatoryReply> replies = new ArrayList<>();
	
}
