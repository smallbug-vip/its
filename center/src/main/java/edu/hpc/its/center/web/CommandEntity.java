package edu.hpc.its.center.web;

/**
 * 界面传来的命令将转换为该对象
 * 
 * @timestamp Feb 22, 2016 3:30:03 PM
 * @author smallbug
 */
public class CommandEntity implements Command {

	private String command;// 命令
	private String[] value;// 参数

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String[] getValue() {
		return value;
	}

	public void setValue(String[] value) {
		this.value = value;
	}

}
