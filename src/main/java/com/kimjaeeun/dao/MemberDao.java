package com.kimjaeeun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.quartz.utils.DBConnectionManager;

import com.kimjaeeun.util.DBConn;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Member;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class MemberDao {

	// 1.회원목록조회
	public List<Member> getMembers() {
		Connection conn = DBConn.getConnection();
		List<Member> list = new ArrayList<Member>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID,PWD,EMAIL,NAME FROM TBL_MEMBER");

			while (rs.next()) {
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String email = rs.getString("email");
				String name = rs.getString("name");

				Member member = new Member(id, pwd, email, name);
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 2.로그인 성공 확인
	public boolean login(String id, String pwd) {
		Connection conn = DBConn.getConnection();
		boolean success = false;
		try {
			Statement stmt = conn.createStatement();
			// 파라미터로 받아온 id랑 pwd를 where절에 넣어 정보를 출력함
			// 그걸 rs에담음
			ResultSet rs = stmt.executeQuery(
					"SELECT ID, PWD,EMAIL,NAME FROM TBL_MEMBER " + "WHERE ID='" + id + "' AND PWD ='" + pwd + "'");
			// success변수에 rs.next() (rs의 다음 커서가 있으면 true 없으면 false) 담음
			success = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;

	}

	// 3.회원가입
	public void join(Member member) {
		Connection conn = DBConn.getConnection();
		try {
			// PreparedStatement : SQL 구문을 실행하는 역할
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO TBL_MEMBER VALUES(?,?,?,?)");
			int idx = 1;
			pstmt.setString(idx++, member.getId());// 1
			pstmt.setString(idx++, member.getPwd());// 2
			pstmt.setString(idx++, member.getEmail());// 3
			pstmt.setString(idx++, member.getName());// 4

			// select : executeQuery, insert update : executeUpdate
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 4.회원정보수정
	public void modify(Member member) {
		Connection conn = DBConn.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE TBL_MEMBER SET \r\n" + "	PWD =?,\r\n"
					+ "	EMAIL =?,\r\n" + "	NAME =?			\r\n" + "WHERE ID =?");
			int idx = 1;
			pstmt.setString(idx++, member.getPwd());// 2
			pstmt.setString(idx++, member.getEmail());// 3
			pstmt.setString(idx++, member.getName());// 4
			pstmt.setString(idx++, member.getId());// 1

			// select : executeQuery, insert update : executeUpdate
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 5.id로 정보 select하는 메서드
	public Member findBy(String id) {
		Connection conn = DBConn.getConnection();
		Member member = null;
		// boolean success=false;
		try {
			// PreparedStatement : SQL 구문을 실행하는 역할
			PreparedStatement pstmt = conn.prepareStatement("SELECT ID, PWD,EMAIL,NAME FROM TBL_MEMBER WHERE ID=?");
			// 첫번째 물음표에 id넣겠다
			pstmt.setString(1, id);
			// ResultSet 쿼리 결과 담음
			// executeQuery()는 select문만 사용
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) { // rs안에 있는 커서가 존재(true)면
				int idx = 1;
				// Member.java의 변수들 초기화
				member = new Member(rs.getString(idx++), // 1
						rs.getString(idx++), // 2
						rs.getString(idx++), // 3
						rs.getString(idx++)// 4
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	// 6.email로 정보 select하는 메서드
	public Member findById(String email) {
		Connection conn = DBConn.getConnection();
		Member member = null;
		// boolean success=false;
		try {
			// PreparedStatement : SQL 구문을 실행하는 역할
			PreparedStatement pstmt = conn.prepareStatement("SELECT ID,PWD,EMAIL,NAME FROM TBL_MEMBER WHERE EMAIL=?");
			// 첫번째 물음표에 id넣겠다
			pstmt.setString(1, email);
			// ResultSet 쿼리 결과 담음
			// executeQuery()는 select문만 사용
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) { // rs안에 있는 커서가 존재(true)면
				int idx = 1;
				// Member.java의 변수들 초기화
				member = new Member(rs.getString(idx++), // 1
						rs.getString(idx++), // 2
						rs.getString(idx++), // 3
						rs.getString(idx++)// 4
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	// 7.email과 name으로 정보 select하는 메서드
	public Member findById(String email, String name) {
		Connection conn = DBConn.getConnection();
		Member member = null;
		// boolean success=false;
		try {
			// PreparedStatement : SQL 구문을 실행하는 역할
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT ID,PWD,EMAIL,NAME FROM TBL_MEMBER WHERE EMAIL=? AND NAME=?");
			// 첫번째 물음표에 id넣겠다
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			// ResultSet 쿼리 결과 담음
			// executeQuery()는 select문만 사용
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) { // rs안에 있는 커서가 존재(true)면
				int idx = 1;
				// Member.java의 변수들 초기화
				member = new Member(rs.getString(idx++), // 1
						rs.getString(idx++), // 2
						rs.getString(idx++), // 3
						rs.getString(idx++)// 4
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	// 8.회원 탈퇴
	public int deleteMember(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpw = ""; // DB상의 비밀번호를 담아둘 변수
		int x = -1;

		try {
			// 비밀번호 조회
			StringBuffer query1 = new StringBuffer();
			query1.append("SELECT PWD FROM TBL_MEMBER WHERE ID=?");

			// 회원 삭제
			StringBuffer query2 = new StringBuffer();
			query2.append("DELETE FROM TBL_MEMBER WHERE ID=?");

			conn = DBConn.getConnection();

			// 자동 커밋을 false로 한다.
			conn.setAutoCommit(false);

			// 1. 아이디에 해당하는 비밀번호를 조회한다.
			pstmt = conn.prepareStatement(query1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbpw = rs.getString("pwd");
				if (dbpw.equals(pw)) // 입력된 비밀번호와 DB비번 비교
				{
					// 같을경우 회원삭제 진행
					pstmt = conn.prepareStatement(query2.toString());
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					conn.commit();
					x = 1; // 삭제 성공
				} else {
					x = 0; // 비밀번호 비교결과 - 다름
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	public static void main(String[] args) {
		MemberDao dao = new MemberDao();
//		Member member=new Member();
//		Member id =dao.findBy(member.getId());
//		System.out.println(id);
		// System.out.println(dao.deleteMember("sda","1234"));
		// System.out.println(dao.deleteMember("babamba2","수정4"));
		// System.out.println(dao.findById("dmjf12@naver.com"));
		// System.out.println(dao.getMembers());
		// dao.join(new Member("bts","1234","asdsa","방탄"));
		dao.modify(new Member("bts", "1234", "asdsa2", "방탄2"));
		// dao.pwdUpdate2(new Member("babamba2","수6334","수4","수정"));
		// System.out.println(dao.getMembers());
		// 수정 테스트
		// dao.modify(new Member("babamba2","수정4","수정4","수정4"));
		// System.out.println(dao.getMembers());
		// System.out.println(dao.read(7L));
		// System.out.println(dao.login("javaman","5678"));
		// System.out.println(dao.login("javaman1","1234"));
	}

}
