package org.cpu.pethotel.biz.impl;

import java.util.List;

import org.cpu.pethotel.biz.IPetsBiz;
import org.cpu.pethotel.dao.IPetsDao;
import org.cpu.pethotel.dao.impl.PetsDaoImpl;
import org.cpu.pethotel.po.Pets;

public class PetsBizImpl implements IPetsBiz {
	private IPetsDao petsDao;
	

	public PetsBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.petsDao = new PetsDaoImpl();
	}

	@Override
	public boolean add(Pets pets) {
		// TODO Auto-generated method stub
		return this.petsDao.insert(pets)>0?true:false;
	}

	@Override
	public List<Pets> findAllByUid(int uid) {
		// TODO Auto-generated method stub
		return this.petsDao.selectAllByUid(uid);
	}

	@Override
	public boolean delete(int pid) {
		// TODO Auto-generated method stub
		return this.petsDao.delete(pid)>0?true:false;
	}

	@Override
	public Pets findById(int pid) {
		// TODO Auto-generated method stub
		return this.petsDao.selectByPid(pid);
	}

	@Override
	public boolean modify(Pets pets) {
		// TODO Auto-generated method stub
		return this.petsDao.update(pets)>0?true:false;
	}

}
