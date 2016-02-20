package edu.hpc.its.area.service;

import edu.hpc.its.area.test.TestLinkEntity;

public interface LinkEntityService extends ServiceBase {

	public void insertLinkEntity(TestLinkEntity entity);

	public TestLinkEntity selectLinkEntityById(Long id);

	public void deleteLinkEntityById(Long id);
}
