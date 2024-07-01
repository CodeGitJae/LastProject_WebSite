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
public class Starspot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	private String address;
	
	@OneToMany(mappedBy = "starspot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StarspotImages> images = new ArrayList<>();
		
	private String createdate;
	private String updatedate;
	
	private int views;
	
	private String writer;
	
	@OneToMany(mappedBy = "starspot", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StarspotReply> replies = new ArrayList<>();
	
	@OneToMany(mappedBy = "starspot", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StarspotLikes> likes = new ArrayList<>();
	
}
