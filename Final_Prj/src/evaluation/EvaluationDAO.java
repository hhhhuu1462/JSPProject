package evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class EvaluationDAO {
	// 강의평가와 관련된 db
	
	public int write(EvaluationDTO evaluationDTO) {
		String sql = "insert into EVALUATION values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
		// null 값인 이유 : auto_increment 지정했기 때문에 자동 1씩 증가

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, evaluationDTO.getUserID());
			pstmt.setString(2, evaluationDTO.getLectureName());
			pstmt.setString(3, evaluationDTO.getProfessorName());
			pstmt.setInt(4, evaluationDTO.getLectureYear());
			pstmt.setString(5, evaluationDTO.getSemesterDivide());
			pstmt.setString(6, evaluationDTO.getLectureDivide());
			pstmt.setString(7, evaluationDTO.getEvaluationTitle());
			pstmt.setString(8, evaluationDTO.getEvaluationContent());
			pstmt.setString(9, evaluationDTO.getTotalScore());
			pstmt.setString(10, evaluationDTO.getCreditScore());
			pstmt.setString(11, evaluationDTO.getComfortableScore());
			pstmt.setString(12, evaluationDTO.getLectureScore());
			return pstmt.executeUpdate(); // 1 반환
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(pstmt != null) pstmt.close();} catch (Exception e) { e.printStackTrace(); }
			try {if(rs != null) rs.close();} catch (Exception e) { e.printStackTrace(); }
		}
		return -1; //db 오류
	}
}
