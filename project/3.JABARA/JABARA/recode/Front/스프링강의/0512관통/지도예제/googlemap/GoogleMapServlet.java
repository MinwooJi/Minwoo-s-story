package googlemap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/GoogleMapServlet")
public class GoogleMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<LocationDto> list = new ArrayList<LocationDto>();
		list.add(new LocationDto("사직동", "광화문풍림스페이스본(101동~105동)", "37.5743822", "126.9688505"));
		list.add(new LocationDto("사직동", "광화문풍림스페이스본(106동)", "37.57348", "126.967792"));
		list.add(new LocationDto("내수동", "경희궁의아침4단지", "37.5722444", "126.9722071"));
		list.add(new LocationDto("견지동", "대성스카이렉스", "37.5724163", "126.9824791"));
		list.add(new LocationDto("효제동", "포레스트힐시티", "37.5740221", "127.0018054"));
		list.add(new LocationDto("명륜1가", "송림아마레스아파트", "37.59062120000001", "126.9976818"));
		list.add(new LocationDto("명륜2가", "아남1", "37.5855698", "126.9994671"));
		list.add(new LocationDto("명륜2가", "아남3", "37.5855655", "127.0002337"));
		list.add(new LocationDto("명륜3가", "한빛", "37.5893352", "126.9950098"));
		list.add(new LocationDto("창신동", "동대문", "37.5722279", "127.0151863"));
		list.add(new LocationDto("숭인동", "삼전솔하임2차", "37.5724383", "127.0167548"));
		list.add(new LocationDto("숭인동", "주건축물제1동", "37.5736666", "127.0226401"));
		list.add(new LocationDto("숭인동", "숭인한양LEEPS", "37.57357390000001", "127.0223266"));
		list.add(new LocationDto("숭인동", "종로동광모닝스카이", "37.5734074", "127.0223446"));
		list.add(new LocationDto("평창동", "에지앙빌", "37.6069685", "126.9675741"));
		list.add(new LocationDto("평창동", "롯데낙천대", "37.6091837", "126.9756868"));
		list.add(new LocationDto("평창동", "삼성", "37.6109493", "126.9786561"));
		list.add(new LocationDto("평창동", "벽산블루밍평창힐스", "37.6080961", "126.9737175"));
		list.add(new LocationDto("익선동", "현대뜨레비앙", "37.5750883", "126.9900786"));
		list.add(new LocationDto("연건동", "이화에수풀", "37.57539370000001", "127.0018312"));
		list.add(new LocationDto("명륜1가", "아마레스", "37.5869019", "126.9988775"));
		list.add(new LocationDto("창신동", "MID그린(3동)", "37.5779087", "127.0099886"));
		list.add(new LocationDto("창신동", "성용", "37.57500479999999", "127.0094655"));
		list.add(new LocationDto("창신동", "창신쌍용2", "37.5804209", "127.0117238"));
		list.add(new LocationDto("숭인동", "삼전솔하임4차", "37.5713672", "127.0167802"));
		list.add(new LocationDto("평동", "경희궁자이(3단지)", "37.5680134", "126.966708"));
		list.add(new LocationDto("홍파동", "경희궁자이(2단지)", "37.5709038", "126.9639262"));
		list.add(new LocationDto("명륜2가", "아남2", "37.5861655", "126.9987424"));
		list.add(new LocationDto("창신동", "창신쌍용1", "37.580401", "127.0139816"));
		list.add(new LocationDto("숭인동", "종로중흥S클래스", "37.5736023", "127.0217228"));
		list.add(new LocationDto("숭인동", "종로유케이201", "37.5738414", "127.0213337"));
		list.add(new LocationDto("평창동", "롯데캐슬 로잔", "37.6098407", "126.9774707"));
		list.add(new LocationDto("평창동", "갑을", "37.6046031", "126.9637494"));
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(new Gson().toJson(list));
	}
}
