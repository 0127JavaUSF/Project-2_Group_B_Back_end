package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ApplicationAnswer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="question_id", nullable=false)
	private TemplateQuestion question;
	
	@Column(nullable = false)
	private String answer;

	public ApplicationAnswer() {}

	public ApplicationAnswer(Integer id, TemplateQuestion question, String answer) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TemplateQuestion getQuestion() {
		return question;
	}

	public void setQuestion(TemplateQuestion question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}