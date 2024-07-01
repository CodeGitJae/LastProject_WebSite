package com.flower.star.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="comment")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="board_id")
	private Board board;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
}
