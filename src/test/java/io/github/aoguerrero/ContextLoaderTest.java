package io.github.aoguerrero;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.junit.jupiter.api.Test;

class ContextLoaderTest {

	@Test
	void test() throws IOException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("test_config.json");
		String json = FileUtils.readFileToString(new File(resource.toURI()), "utf-8");
		
		ContextLoader loader = new ContextLoader();
		VelocityContext context = loader.load(json);
		assertTrue(context.get("one").toString().equals("1"));
		assertTrue(context.get("two").toString().equals("2"));
		Map<String, Object> map = (Map<String, Object>)context.get("three");
		assertTrue(map.get("four").toString().equals("4"));
		assertTrue(((List)map.get("six")).size() == 3);
		assertTrue(((List)context.get("anArray")).size() == 2);
	}

}
