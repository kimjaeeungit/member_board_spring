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
import com.kimjaeeun.vo.Attach;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Criteria;


public class BoardDao {
	// 1.글작성
	public Long insert(Board board) {
		Connection conn = DBConn.getConnection();
		Long bno = null;
		try {
			// 글번호를 먼저 발급 select
			// NEXTVAL : 해당 시퀀스 값증가 시키기
			ResultSet rs = conn.prepareStatement("SELECT SEQ_BOARD.NEXTVAL FROM DUAL").executeQuery();
			rs.next();
			bno = rs.getLong(1);

			// 글 작성 insert
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO TBL_BOARD (BNO,TITLE,CONTENT,ID,CATEGORY) VALUES (?,?,?,?,?)");
			int idx = 1;
			pstmt.setLong(idx++, bno);// 1
			pstmt.setString(idx++, board.getTitle());// 2
			pstmt.setString(idx++, board.getContent());// 3
			pstmt.setString(idx++, board.getId());// 4
			pstmt.setLong(idx++, board.getCategory());// 5
			// select : executeQuery, insert update : executeUpdate
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bno;// 글번호 반환
	}

	// 첨부파일 작성
	public void writeAttach(Attach attach) {
		Connection conn = DBConn.getConnection();
		try {
			// 글 작성
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO TBL_ATTACH VALUES (?, ?, ?, ?)");
			int idx = 1;
			pstmt.setString(idx++, attach.getUuid());// 1
			pstmt.setString(idx++, attach.getOrigin());// 2
			pstmt.setLong(idx++, attach.getBno());// 3
			pstmt.setString(idx++, attach.getPath());// 4
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 2.글수정
	public Long update(Board board) {
		Connection conn = DBConn.getConnection();
		Long bno = board.getBno();
		try {
			// 글 작성
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE TBL_BOARD SET\r\n" + "	TITLE=?,\r\n" + "	CONTENT =?\r\n" + "WHERE BNO=?");
			int idx = 1;
			pstmt.setString(idx++, board.getTitle());// 1
			pstmt.setString(idx++, board.getContent());// 2
			pstmt.setLong(idx++, board.getBno());// 4

			// select : executeQuery, insert update : executeUpdate
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bno;
	}

	// 3.단일조회
	public Board read(Long bno) {
		Connection conn = DBConn.getConnection();
		Board board = null;
		// boolean success=false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT BNO,TITLE,CONTENT,REGDATE,ID,CATEGORY\r\n" + "FROM TBL_BOARD\r\n" + "WHERE BNO=?");
			pstmt.setLong(1, bno);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int idx = 1;
				board = new Board(rs.getLong(idx++), rs.getString(idx++), rs.getString(idx++), rs.getDate(idx++),
						rs.getString(idx++), rs.getLong(idx++), null, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	// 목록조회x
	public List<Board> list() {
		Connection conn = DBConn.getConnection();
		List<Board> list = new ArrayList<>();
		// boolean success=false;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BNO,TITLE,REGDATE, ID, CATEGORY\r\n" + "FROM TBL_BOARD\r\n"
					+ "WHERE BNO >0\r\n" + "ORDER BY 1 DESC");

			while (rs.next()) {// rs에 담긴 다음 커서가 존재할경우(true일 경우)
				int idx = 1;
				Long bno = rs.getLong(idx++); // rs 1번 커서를 Long타입으로 가져옴
				String title = rs.getString(idx++);// 2
				Date regDate = rs.getDate(idx++);// 3
				String id = rs.getString(idx++);// 4
				Long category = rs.getLong(idx++);// 5

				// 생성자로 초기화
				Board board = new Board(bno, title, regDate, id, category);
				// list에 board넣음
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 4.목록조회
	public List<Board> list(Criteria cri) {
		Connection conn = DBConn.getConnection();
		List<Board> list = new ArrayList<>();
		// boolean success=false;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("WITH B AS (\r\n");
			sql.append("   SELECT\r\n");
			sql.append("/*+INDEX_DESC(TB PK_BOARD)*/ \r\n");
			sql.append("      ROWNUM RN, TB.*\r\n");
			sql.append("   FROM TBL_BOARD TB\r\n");
			sql.append("   WHERE BNO>0\r\n");
			sql.append("   AND CATEGORY =? \r\n"); // 썸네일 쿼리 추가..
			sql.append("   AND ROWNUM <=?*?\r\n");
			sql.append("   ORDER BY BNO DESC\r\n");
			sql.append(")\r\n");
			sql.append(
					"SELECT BNO,TITLE,REGDATE,ID,CATEGORY,(SELECT COUNT(*) FROM TBL_REPLY R WHERE R.BNO = B.BNO) REPLYCNT,");
			sql.append("CONTENT  FROM B\r\n");
			sql.append("WHERE RN >(? -1) *?");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			pstmt.setInt(idx++, cri.getCategory());
			pstmt.setInt(idx++, cri.getPageNum());
			pstmt.setInt(idx++, cri.getAmount());
			pstmt.setInt(idx++, cri.getPageNum());
			pstmt.setInt(idx++, cri.getAmount());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				idx = 1;
				Long bno = rs.getLong(idx++);
				String title = rs.getString(idx++);
				Date regDate = rs.getDate(idx++);
				String id = rs.getString(idx++);
				Long category = rs.getLong(idx++);

				Board board = new Board(bno, title, regDate, id, category);
				board.setReplyCnt(rs.getInt(idx++));
				board.setContent(rs.getString(idx++));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 5.글삭제
	public boolean deleteBoard(Long bno) {
		boolean result = false;
		try {
			Connection conn = DBConn.getConnection();
			conn.setAutoCommit(false); // 자동 커밋을 false로 한다.

			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM TBL_BOARD WHERE BNO=?");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, bno);

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				conn.commit(); // 완료시 커밋
			}

		} catch (Exception e) {
			try {
				Connection conn = DBConn.getConnection();
				conn.rollback(); // 오류시 롤백
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

	// 6.첨부파일 삭제
	public void deleteAttach(Long bno) {
		Connection conn = DBConn.getConnection();
		try {
			PreparedStatement pstmtAttach = conn.prepareStatement("DELETE TBL_ATTACH WHERE BNO=?");
			int idx = 1;
			pstmtAttach.setLong(idx++, bno);// 4
			pstmtAttach.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 7.댓글삭제
	public void deleteReply(Long bno) {
		Connection conn = DBConn.getConnection();
		try {
			PreparedStatement pstmtAttach = conn.prepareStatement("DELETE FROM TBL_REPLY \r\n"
					+ "WHERE RNO IN (SELECT RNO\r\n" + "FROM TBL_REPLY\r\n" + "WHERE BNO=?)");
			int idx = 1;
			pstmtAttach.setLong(idx++, bno);// 4
			pstmtAttach.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 8.삭제할 파일명 가져오기
	public String getFileName(Long bno) {
		String fileName = null;

		try {
			Connection conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT UUID FROM TBL_ATTACH WHERE BNO=?");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, bno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				fileName = rs.getString("UUID");

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return fileName;
	}

	// 9.다른날짜 사진 삭제
	public List<Attach> readAttachByPath(String path) {
		Connection conn = DBConn.getConnection();
		List<Attach> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT UUID, ORIGIN, PATH FROM TBL_ATTACH WHERE PATH=?");
			pstmt.setString(1, path);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int idx = 1;
				String uuid = rs.getString(idx++);
				String origin = rs.getString(idx++);

				Attach attach = new Attach(uuid, origin, null, path);
				list.add(attach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	// 10.첨부파일 목록만 가져오는 셀렉트 코드
	public List<Attach> readAttach(Long bno) {
		Connection conn = DBConn.getConnection();
		List<Attach> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT UUID ,ORIGIN , PATH FROM TBL_ATTACH WHERE BNO=?");
			pstmt.setLong(1, bno);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idx = 1;
				String uuid = rs.getString(idx++);
				String origin = rs.getString(idx++);
				String path = rs.getString(idx++);

				Attach attach = new Attach(uuid, origin, bno, path);
				list.add(attach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(this.getClass().getCanonicalName() + ".readAttach(" + bno
		// + ")");
		// list.forEach(System.out::println);
		return list;

	}

	// 11.원래 파일이름
	public String findOriginBy(String uuid) {
		Connection conn = DBConn.getConnection();
		String origin = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT ORIGIN FROM TBL_ATTACH WHERE UUID =?");
			pstmt.setString(1, uuid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				origin = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return origin;
	}

	// 12.글갯수 가져오기
	public int getCount(Criteria cri) {
		Connection conn = DBConn.getConnection();
		int count = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT count(*)FROM TBL_BOARD WHERE CATEGORY =?");
			pstmt.setInt(1, cri.getCategory());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 13.id로 정보 select하는 메서드
	public Board findById(String id) {
		Connection conn = DBConn.getConnection();
		Board board = null;
		// boolean success=false;
		try {
			// PreparedStatement : SQL 구문을 실행하는 역할
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT BNO,TITLE,CONTENT,REGDATE,ID,CATEGORY FROM TBL_BOARD WHERE ID=?");
			// 첫번째 물음표에 id넣겠다
			pstmt.setString(1, id);
			// ResultSet 쿼리 결과 담음
			// executeQuery()는 select문만 사용
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) { // rs안에 있는 커서가 존재(true)면
				int idx = 1;
				// Member.java의 변수들 초기화
				board = new Board(rs.getLong(idx++), // 1
						rs.getString(idx++), // 2
						rs.getString(idx++), // 3
						rs.getDate(idx++), // 4
						rs.getString(idx++), // 4
						rs.getLong(idx++)// 4
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	// 14.탈퇴한회원 게시글 아이디 수정
	public void updateWriter(Board board) {
		Connection conn = DBConn.getConnection();
		try {
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE TBL_BOARD SET\r\n" + "   ID=''\r\n" + "   WHERE ID=?");
			int idx = 1;
			pstmt.setString(idx++, board.getId());// 4
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BoardDao dao = new BoardDao();

		// 14.탈퇴한회원 게시글 아이디 수정
		// dao.updateWriter(new Board(377L,"수정글","수정내용","test3"));

		// id로 정보 select하는 메서드
		// System.out.println(dao.findById("babamba3"));

		// 글작성 테스트
//		 Long bno = dao.insert(new Board(null,
//		 "dao main에서 작성된 글 제목","dao main에서 작성된 글 내용",null,"babamba",1L));
//		 System.out.println(bno);

		// 단일조회 테스트
		// System.out.println(dao.read(5L));

		// 목록 조회 테스트
		// dao.list().forEach(System.out::println);

		// 수정 테스트
//		 dao.update(new Board(1L,"수정글","수정내용",null,null,null));
//		 System.out.println(dao.read(1L));

		// 삭제 테스트
		 System.out.println(dao.deleteBoard(384L));
		 dao.list().forEach(System.out::println);

		// 첨부파일 이름 가져오기 테스트
		// String file=dao.getFileName(365L);
		// System.out.println(file);

		// 댓글 삭제 테스트
		// dao.deleteReply(367L);

		// 첨부파일 삭제 테스트
		// dao.deleteAttach(27L);
		// dao.list().forEach(System.out::println);

		// 페이지 테스트
//		List<Board> list = dao.list(new Criteria(1, 10));
//		for (Board m : list) {
//			System.out.println(m);
//		}
		// 위에랑 같은거
		// dao.list(new Criteria(1,10)).forEach(System.out::println);
	}

}
