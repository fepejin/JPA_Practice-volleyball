package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.domain.entity.Player;
import model.domain.entity.VTeam;
import util.DBUtil;

public class VolleyDAO {
		
	//선수 한명 정보
	public static Player getPlayerOne(String name) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Player p = (Player)em.createNamedQuery("Player.findByPlayer").setParameter("name", name).getSingleResult();
		
//		System.out.println(p);
		
		tx.commit();
		
		return p;
	}
	//Like 연산자 사용해서 부분일치에 체크하면 해당 직원들 정보가 나오는 것
	public static List<Player> getLikeEmp(String likeName) {
		EntityManager em = DBUtil.getEntityManager();
		List<Player> all = em.createNamedQuery("Player.findByLikePlayer").setParameter("name", "%" + likeName + "%").getResultList();
//		System.out.println(all);
		em.close();
		
		return all;
	}
	
	//팀별 선수목록
	public static List<Player> getTeamPlayers(String teamName) {
		EntityManager em = DBUtil.getEntityManager();
		List<Player> all = em.createNamedQuery("Player.findByTeamPlayers").setParameter("name", teamName).getResultList();
//		System.out.println(all);
		em.close();
		
		return all;
	}
	
	//모든 선수 정보
	public static List<Player> getPlayerAll() {
		EntityManager em = DBUtil.getEntityManager();
		String sql = "select p from Player p";
//		VTeam searchTeam = em.find(VTeam.class, 1l);
		List<Player> all = em.createQuery(sql).getResultList();
//		System.out.println(all);
		
		em.close();
		
		return all;
	}
	
	//모든 팀 정보
	public static List<VTeam> getTeamAll() {
		EntityManager em = DBUtil.getEntityManager();
		String sql = "select v from VTeam v";
		List<VTeam> all = em.createQuery(sql).getResultList();
		
		em.close();
		
		return all;
	}
	
//	public static void main(String[] args) {
////		List<Player> all =  getPlayerAll();
////		System.out.println(all);
////		Player p = getPlayerOne("김연경");
////		System.out.println(p);
////		getLikeEmp("김");
//		System.out.println(getTeamPlayers("흥국생명"));
//		
//	}
	
}
