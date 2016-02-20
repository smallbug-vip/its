package edu.hpc.its.area.factory;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * c3p0数据源工厂类
 * 
 * @timestamp Feb 17, 2016 1:13:13 PM
 * @author smallbug
 */
public class C3P0DataSourceFactory extends UnpooledDataSourceFactory {
	public C3P0DataSourceFactory() {
		this.dataSource = new ComboPooledDataSource();
	}
}
