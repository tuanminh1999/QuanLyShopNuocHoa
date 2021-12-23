package edu.sgu.bll;

import java.util.ArrayList;
import java.util.List;

import edu.sgu.dal.TrangThaiDonHangDAL;
import edu.sgu.dto.TrangThaiDonHang;



public class TrangThaiDonHangBLL {
	private List<TrangThaiDonHang> lstTrangThaiDonHang = new ArrayList<TrangThaiDonHang>();
	private TrangThaiDonHangDAL trangThaiDonHangDAL = new TrangThaiDonHangDAL();

	public TrangThaiDonHangBLL() {
		lstTrangThaiDonHang = trangThaiDonHangDAL.dsTrangThaiDonHang();
	}

	public List<TrangThaiDonHang> dsTrangThaiDonHang() {
		return lstTrangThaiDonHang;
	}
	
	public String hienThiTenTrangThai(int id_trangThai) {
		for (TrangThaiDonHang ttdh : dsTrangThaiDonHang())
			if (id_trangThai == ttdh.getId())
				return ttdh.getTenTrangThai();
		return null;
	}

}
