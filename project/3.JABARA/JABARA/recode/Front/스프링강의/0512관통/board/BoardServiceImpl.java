package board;

import java.util.List;

public class BoardServiceImpl implements BoardService {

	private BoardDAO dao;
	
	public BoardServiceImpl() {
		dao = new BoardDAOImpl();
	}
	
	@Override
	public List<Board> list() throws Exception {
		return dao.selectBoard();
	}

	@Override
	public Board detail(int no) throws Exception {
		return dao.selectBoardByNo(no);
	}

}
