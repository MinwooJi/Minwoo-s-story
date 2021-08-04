package fselectbox.service;

import java.util.ArrayList;

import fselectbox.dao.FSelectBoxDAO;
import fselectbox.dao.FSelectBoxDAOImpl;
import fselectbox.dto.HouseInfoDTO;
import fselectbox.dto.SidoCodeDTO;

public class FSelectBoxServiceImpl implements FSelectBoxService {

	private FSelectBoxDAO dao;

	public FSelectBoxServiceImpl() {
		dao = new FSelectBoxDAOImpl();
	}

	@Override
	public ArrayList<SidoCodeDTO> selectSido() throws Exception {
		ArrayList<SidoCodeDTO> list = dao.selectSido();
		return list;
	}

	@Override
	public ArrayList<SidoCodeDTO> selectGugun(String sido) throws Exception {
		ArrayList<SidoCodeDTO> list = dao.selectGugun(sido);
		return list;
	}

	@Override
	public ArrayList<HouseInfoDTO> selectDong(String gugun) throws Exception {
		ArrayList<HouseInfoDTO> list = dao.selectDong(gugun);
		return list;
	}

	@Override
	public ArrayList<HouseInfoDTO> selectApt(String dong) throws Exception {
		ArrayList<HouseInfoDTO> list = dao.selectApt(dong);
		return list;
	}

}
