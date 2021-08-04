package board;

import java.util.List;

public interface BoardDAO {
	List<Board> selectBoard() throws Exception;

	Board selectBoardByNo(int no) throws Exception;
}
