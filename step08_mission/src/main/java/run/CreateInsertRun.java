package run;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.domain.entity.Player;
import model.domain.entity.VTeam;
import util.DBUtil;
//테이블 생성 및 데이터 추가
//작성전 persistence.xml 확인 <property name="hibernate.hbm2ddl.auto" value="create" />
//작성 후엔 까먹지말고 none으로 꼭 돌리기!!!!!!
public class CreateInsertRun {
	public static void main(String[] args) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		VTeam t1 = new VTeam("흥국생명", "인천");
		VTeam t2 = new VTeam("기업은행", "화성");
		VTeam t3 = new VTeam("현대건설", "수원");
		VTeam t4 = new VTeam("한국도로공사", "김천");

		//insert
		em.persist(t1);
		em.persist(t2);
		em.persist(t3);
		em.persist(t4);
		
		Player p1 = new Player();
		p1.setName("김연경");
		p1.setNo(10);
		p1.setAge(34);
		p1.setPosition("아웃사이드 히터");
		p1.setTeam(t1);
		
		Player p2 =  new Player("김다은", 1, 21, "아웃사이드 히터");
		p2.setTeam(t1);

		Player p3 =  new Player("옐레나", 13, 25, "아포짓 스파이커");
		p3.setTeam(t1);
		
		Player p4 =  new Player("김희진", 4, 31, "아포짓 스파이커");
		p4.setTeam(t2);
		
		Player p5 =  new Player("김수지", 11, 34, "미들 블로커");
		p5.setTeam(t2);
		
		Player p6 =  new Player("양유경", 16, 19, "아웃사이드 히터");
		p6.setTeam(t2);
		
		Player p7 =  new Player("양효진", 14, 33, "미들 블로커");
		p7.setTeam(t3);
		
		Player p8 =  new Player("김연견", 8, 29, "리베로");
		p8.setTeam(t3);

		Player p9 =  new Player("김다인", 3, 24, "세터");
		p9.setTeam(t3);
		
		Player p10 =  new Player("박정아", 9, 29, "아웃사이드 히터");
		p10.setTeam(t4);
		
		Player p11 =  new Player("김세인", 18, 19, "아웃사이드 히터");
		p11.setTeam(t4);
		
		Player p12 =  new Player("안예림", 3, 21, "세터");
		p12.setTeam(t4);
		
		
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(p4);
		em.persist(p5);
		em.persist(p6);
		em.persist(p7);
		em.persist(p8);
		em.persist(p9);
		em.persist(p10);
		em.persist(p11);
		em.persist(p12);
		
		tx.commit();
		em.close();
		
	}
	
	

}
