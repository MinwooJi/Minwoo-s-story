package fselectbox.dao;

import java.util.ArrayList;

import fselectbox.dto.HouseInfoDTO;
import fselectbox.dto.SidoCodeDTO;

public interface FSelectBoxDAO {

	ArrayList<SidoCodeDTO> selectSido() throws Exception;

	ArrayList<SidoCodeDTO> selectGugun(String sido) throws Exception;

	ArrayList<HouseInfoDTO> selectDong(String gugun) throws Exception;

	ArrayList<HouseInfoDTO> selectApt(String dong) throws Exception;

}
