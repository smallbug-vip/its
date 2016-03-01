package edu.hpc.its.center;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hpc.its.center.util.CommandQueue;
import edu.hpc.its.center.web.CommandEntity;

public class TestQuery {

	private CommandQueue<CommandEntity> query = new CommandQueue<>();

//	@Test
	public void testQuery() {
		String json = "[{\"command\":\"AREACHOOSE\",\"value\":[\"NUM001\"]}]";
		parseMessage(json);
		System.out.println(query.deQueue());
		System.out.println(query.size());
	}

	private void parseMessage(String message) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			CommandEntity[] list = mapper.readValue(message, CommandEntity[].class);
			if (list != null && list.length > 0) {
				for (CommandEntity l : list) {
					query.enQueue(l);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
