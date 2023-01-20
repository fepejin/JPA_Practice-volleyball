package model;

import java.util.List;

import javax.persistence.EntityManager;

import model.domain.entity.Player;
import model.domain.entity.VTeam;
import util.DBUtil;

public class VolleyDAO {
		
	//선수 한명 정보.이름으로 검색
	public static Player getPlayerOne(String name) {
		EntityManager em = DBUtil.getEntityManager();
		Player player = (Player)em.createNamedQuery("Player.findByPlayer").setParameter("name", name).getSingleResult();
		
		em.clear();
		
		return player;
	}
	//Like 연산자 사용해서 부분일치에 체크하면 해당 선수들 정보 출력
	public static List<Player> getLikeEmp(String likeName) {
		EntityManager em = DBUtil.getEntityManager();
		List<Player> all = em.createNamedQuery("Player.findByLikePlayer").setParameter("name", "%" + likeName + "%").getResultList();
		
		em.close();
		
		return all;
	}
	
	//팀별 선수목록
	public static List<Player> getTeamPlayers(String teamName) {
		EntityManager em = DBUtil.getEntityManager();
		List<Player> all = em.createNamedQuery("Player.findByTeamPlayers").setParameter("name", teamName).getResultList();

		em.close();
		
		return all;
	}
	
	//모든 선수 정보
	public static List<Player> getPlayerAll() {
		EntityManager em = DBUtil.getEntityManager();
		String sql = "select p from Player p";
		List<Player> all = em.createQuery(sql).getResultList();
		
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
	//테스트용 main메소드
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
