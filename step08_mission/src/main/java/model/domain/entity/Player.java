package model.domain.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@NamedQuery(name = "Player.findByPlayer", query = "select p from Player p where p.name = :name")
@NamedQuery(name = "Player.findByTeamPlayers", query = "select p from Player p where p.team.tname = :name")
@NamedQuery(name = "Player.findByLikePlayer", query = "select p from Player p where p.name like :name")

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(name="player", nullable = false, length = 20)
	private String name;
	
	@NonNull
	private Integer no;
	
	@NonNull
	private Integer age;
	
	@NonNull
	@Column(nullable = false, length = 30)
	private String position;
	
	//@Temporal(TemporalType.DATE)
	//@CreationTimestamp : oracle의 sysdate 
	//insert쿼리가 동작할 당시 생성. 수정시에는 @UpdateTimestamp
	@CreationTimestamp	
	private Date regdate;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private VTeam team;
	

}
