package controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.VolleyDAO;
import model.domain.entity.Player;
import model.domain.entity.VTeam;

@WebServlet("/volley")
public class VolleyController extends HttpServlet {
	
	//4개의 요청
	//모든 직원 정보 검색, 모든 부서 정보 검색, 한 직원의 정보 검색
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");
		try {
			if(command.equals("players")) {	//모든 직원 정보 검색 처리
				allPlayer(request, response);
			}else if(command.equals("teams")) {	//모든 부서 정보 검색 처리
				allVTeam(request, response);
			}else if(command.equals("playerOne")) { //직원 한명의 정보 검색 처리 
				onePlayer(request, response);
			}else if(command.equals("playerLike")) { //이름으로 부분검색
				oneLikePlayer(request, response);
			}else if(command.equals("teamPlayer")) { //팀 소속 선수들 조회
				oneTeamPlayers(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//모든 직원 정보 검색 
	public void allPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String jsp = "step08_error.jsp";
		try {
			List<Player> plist = VolleyDAO.getPlayerAll();
			JSONObject player = null;
			JSONArray players = new JSONArray();
			
			for(int i = 0; i < plist.size(); i++) {
				player = new JSONObject();
				player.put("선수명", plist.get(i).getName()); 
				player.put("번호", plist.get(i).getNo()); 
				player.put("나이", plist.get(i).getAge()); 
				player.put("포지션", plist.get(i).getPosition()); 
				player.put("등록일", plist.get(i).getRegdate()); 
				player.put("소속팀", plist.get(i).getTeam().getTname()); 
				players.put(player);
			}
			request.setAttribute("data", players);
			jsp = "step08_res.jsp";
		}catch(JSONException s) {
			s.printStackTrace();
			request.setAttribute("errorMsg", "내부적인 오류로 리스트를 불러오지 못했습니다.");
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	//모든 팀 정보 검색
	public void allVTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String jsp = "step08_error.jsp";
		try {
			List<VTeam> tlist = VolleyDAO.getTeamAll();
			JSONObject team = null;
			JSONArray teams = new JSONArray();
			
			for(int i = 0; i < tlist.size(); i++) {
				team = new JSONObject();
				team.put("팀번호", tlist.get(i).getId()); 
				team.put("팀명", tlist.get(i).getTname()); 
				team.put("지역", tlist.get(i).getLoc()); 
				
				teams.put(team);
			}
			request.setAttribute("data", teams);
			jsp = "step08_res.jsp";
		}catch(JSONException s) {
			s.printStackTrace();
			request.setAttribute("errorMsg", "내부적인 오류로 리스트를 불러오지 못했습니다.");
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	
	//직원 한명 검색
	public void onePlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String jsp = "step08_error.jsp";
		try {
			String name = request.getParameter("name");
			System.out.println(name);
			Player one = VolleyDAO.getPlayerOne(name);
			JSONObject player = new JSONObject();
			player.put("선수명", one.getName()); 
			player.put("번호", one.getNo()); 
			player.put("나이", one.getAge()); 
			player.put("포지션", one.getPosition()); 
			player.put("등록일", one.getRegdate()); 
			player.put("소속팀", one.getTeam().getTname()); 
			System.out.println(player);
			//JSONArray안쓰니까 대괄호붙여서 JSONArray처럼 보이게 만듦
			request.setAttribute("data", "["+player+"]");
			jsp = "step08_res.jsp";
		}catch(JSONException s) {
			s.printStackTrace();
			request.setAttribute("errorMsg", "내부적인 오류로 검색하지 못했습니다.");
		}catch(NoResultException ne) {
			ne.printStackTrace();
			request.setAttribute("errorMsg", "검색된 회원이 없습니다.");
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	
	//직원번호 부분일치 검색
	public void oneLikePlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String jsp = "step08_error.jsp";
		try {
			String likeName = request.getParameter("name");
			List<Player> plist = VolleyDAO.getLikeEmp(likeName);
			//배열이 비어있으면 String으로 예외던지기
			if(plist.isEmpty()) { 
				throw new NullPointerException();
			}
			JSONObject player = null;
			JSONArray players = new JSONArray();
			for(int i = 0; i < plist.size(); i++) {
				player = new JSONObject();
				player.put("선수명", plist.get(i).getName()); 
				player.put("번호", plist.get(i).getNo()); 
				player.put("나이", plist.get(i).getAge()); 
				player.put("포지션", plist.get(i).getPosition()); 
				player.put("등록일", plist.get(i).getRegdate()); 
				player.put("소속팀", plist.get(i).getTeam().getTname()); 

				players.put(player);
			}
			request.setAttribute("data", players);
			jsp = "step08_res.jsp";
		}catch(JSONException s) {
			request.setAttribute("errorMsg", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		}catch(NullPointerException ne) { 
			ne.printStackTrace();
			request.setAttribute("errorMsg", "검색된 회원이 없습니다.");
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	
	//팀 소속 선수들목록
	public void oneTeamPlayers(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		String jsp = "step08_error.jsp";
		try {
			String team = request.getParameter("name");
			List<Player> plist = VolleyDAO.getTeamPlayers(team);
			//배열이 비어있으면 예외던지기
			if(plist.isEmpty()) { 
				throw new NullPointerException();
			}
			JSONObject player = null;
			JSONArray players = new JSONArray();
			for(int i = 0; i < plist.size(); i++) {
				player = new JSONObject();
				player.put("선수명", plist.get(i).getName()); 
				player.put("번호", plist.get(i).getNo()); 
				player.put("나이", plist.get(i).getAge()); 
				player.put("포지션", plist.get(i).getPosition()); 
				player.put("등록일", plist.get(i).getRegdate()); 
				player.put("소속팀", plist.get(i).getTeam().getTname()); 

				players.put(player);
			}
			request.setAttribute("data", players);
			jsp = "step08_res.jsp";
		}catch(JSONException s) {
			request.setAttribute("errorMsg", "내부적인 오류로 검색하지 못했습니다.");
			s.printStackTrace();
		}catch(NullPointerException ne) { 
			ne.printStackTrace();
			request.setAttribute("errorMsg", "검색된 회원이 없습니다.");
		}
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	
}
