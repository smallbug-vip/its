package edu.hpc.its.area.dao.mapper;

import edu.hpc.its.area.exception.MyBatisException;
import edu.hpc.its.area.test.TestLinkEntity;

public interface TestLinkEntityMapper {

	/**
	 * 测试保存
	 * 
	 * @timestamp Feb 17, 2016 1:19:57 PM
	 * @param testLink
	 * @throws MyBatisException
	 */
	public void insertTestLinkEntity(TestLinkEntity testLinkEntity) throws MyBatisException;

	/**
	 * 测试查询
	 * 
	 * @timestamp Feb 19, 2016 7:14:04 PM
	 * @param id
	 * @return
	 */
	public TestLinkEntity selectTestLinkEntityById(Long id);

	/**
	 * 测试删除
	 * 
	 * @timestamp Feb 19, 2016 8:04:57 PM
	 * @param id
	 */
	public void deleteTestLinkEntityById(Long id);
}
