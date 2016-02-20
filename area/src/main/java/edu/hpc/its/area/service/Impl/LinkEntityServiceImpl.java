package edu.hpc.its.area.service.Impl;

import org.apache.ibatis.session.SqlSession;

import edu.hpc.its.area.dao.mapper.TestLinkEntityMapper;
import edu.hpc.its.area.service.LinkEntityService;
import edu.hpc.its.area.test.TestLinkEntity;

public class LinkEntityServiceImpl implements LinkEntityService {

	private TestLinkEntityMapper mapper = null;

	@Override
	public void insertLinkEntity(TestLinkEntity entity) {
		mapper.insertTestLinkEntity(entity);
	}

	public TestLinkEntity selectLinkEntityById(Long id) {
		return mapper.selectTestLinkEntityById(id);
	}

	@Override
	public void setMapper(SqlSession session) {
		this.mapper = session.getMapper(TestLinkEntityMapper.class);
	}

	@Override
	public void deleteLinkEntityById(Long id) {
		this.mapper.deleteTestLinkEntityById(id);
	}

}
