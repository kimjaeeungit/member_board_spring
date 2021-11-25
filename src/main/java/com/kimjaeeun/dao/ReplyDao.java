package com.kimjaeeun.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kimjaeeun.util.DBConn;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Reply;

public class ReplyDao {
	//1.댓글작성
	public void insert(Reply reply) {
		Connection conn = DBConn.getConnection();
		try {
			// 글 작성
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO TBL_REPLY(RNO,TITLE,CONTENT,ID,BNO) VALUES(SEQ_REPLY.NEXTVAL,?,?,?,?) ");
			int idx = 1;
			pstmt.setString(idx++, reply.getTitle());// 4
			pstmt.setString(idx++, reply.getContent());// 4
			pstmt.setString(idx++, reply.getId());// 4
			pstmt.setLong(idx++, reply.getBno());// 4
			// select : executeQuery, insert update : executeUpdate
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//2.댓글조회
	public List<Reply> list(Long bno) {
		Connection conn = DBConn.getConnection();
		List<Reply> list = new ArrayList<>();
		// boolean success=false;
		try {
			String sql="SELECT \r\n" + 
					"	RNO, TITLE, CONTENT, TO_CHAR(REGDATE,'YY/MM/DD')REGDATE, ID\r\n" + 
					"FROM TBL_REPLY WHERE RNO>0 AND BNO=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			ResultSet rs = pstmt.executeQuery();
					

			while (rs.next()) {
				int idx = 1;
				Long rno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				String content=rs.getString(idx++);
				String regDate = rs.getString(idx++);
				String id = rs.getString(idx++);

				Reply reply = new Reply(rno, title, content,regDate, id, bno);
				list.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	//3.댓글 단일 조회
	public Reply select(Long rno) {
		Connection conn = DBConn.getConnection();
		Reply reply=null;
		try {
			String sql="SELECT \r\n" + 
					"	RNO, TITLE, CONTENT, TO_CHAR(REGDATE,'YY/MM/DD')REGDATE, ID, BNO\r\n" + 
					"FROM TBL_REPLY WHERE RNO=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, rno);
			ResultSet rs = pstmt.executeQuery();
					

			while (rs.next()) {
				int idx = 1;
				rno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				String content=rs.getString(idx++);
				String regDate = rs.getString(idx++);
				String id = rs.getString(idx++);
				Long bno = rs.getLong(idx++);
				
				reply = new Reply(rno, title, content,regDate, id, bno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reply;
	}
	
		//4.댓글 삭제
		public void delete(Long rno) {
			Connection conn = DBConn.getConnection();
			Reply reply=null;
			try {
				// 글 작성
				PreparedStatement pstmt = conn.prepareStatement("DELETE TBL_REPLY WHERE RNO=?");
				int idx = 1;
				pstmt.setLong(idx++, rno);// 4

				// select : executeQuery, insert update : executeUpdate
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		//5.id로 정보 select하는 메서드
		public Reply findById(String id) {
			Connection conn =DBConn.getConnection();
			Reply reply=null;
			//boolean success=false;
			try {
				//PreparedStatement : SQL 구문을 실행하는 역할
				PreparedStatement pstmt=conn.prepareStatement("SELECT RNO,TITLE,CONTENT,REGDATE,ID,BNO FROM TBL_REPLY WHERE ID=?");
				//첫번째 물음표에 id넣겠다
				pstmt.setString(1,id);	
				//ResultSet 쿼리 결과 담음
				//executeQuery()는 select문만 사용
				ResultSet rs=pstmt.executeQuery();
				
				if(rs.next()) { //rs안에 있는 커서가 존재(true)면
					int idx = 1;
					//Member.java의 변수들 초기화
					reply = new Reply(
							rs.getLong(idx++),//1
							rs.getString(idx++),//2
							rs.getString(idx++),//3
							rs.getString(idx++),//4
							rs.getString(idx++),//4
							rs.getLong(idx++)//4
						);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return reply;
		}
		
		//6.탈퇴회원 댓글 아이디 수정
		public void updateWriter(Reply reply) {
			Connection conn = DBConn.getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement(
						" UPDATE TBL_REPLY SET ID='',TITLE='' WHERE ID=?");
				int idx = 1;
				pstmt.setString(idx++, reply.getId());// 4
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void main(String[] args) {
			ReplyDao dao = new ReplyDao();
			// System.out.println(dao.findById("tes2"));
			 dao.updateWriter(new Reply(86L,"ghj","ghj","jhj",385L));
		}
		
}

