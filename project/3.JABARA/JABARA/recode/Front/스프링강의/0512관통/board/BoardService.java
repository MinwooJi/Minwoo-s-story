package board;

import java.util.List;

public interface BoardService {
	List<Board> list() throws Exception;

	Board detail(int no) throws Exception;
}
