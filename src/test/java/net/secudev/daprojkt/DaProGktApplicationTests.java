package net.secudev.daprojkt;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.secudev.daprojkt.utils.PopulateAndRegistry;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaProGktApplicationTests {

	@Autowired
	private PopulateAndRegistry populate;

	@Before
	public void before() {
		populate.cleanAll();
	}

	@Test
	public void testRoleAndUser() {

		populate.CreateUserAndRoles();
		assertTrue(populate.users.count() == 3);
		assertTrue(populate.roles.count() == 3);
		assertTrue(populate.users.findByName("admin").getRoles().size() == 1);
		assertTrue(populate.users.findByName("vip").getRoles().size() == 2);
	}
}