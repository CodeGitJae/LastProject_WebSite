package com.flower.star.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="board")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	@Id    // pk 키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//컬럼 글자수 설정
	@Column(length = 100)
	private String title;
	
	// 글잦수 제한 없애기
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private String createDate;
	private String updateDate;
	private int views;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StarspotImages> images = new ArrayList<>();
	
	
}
