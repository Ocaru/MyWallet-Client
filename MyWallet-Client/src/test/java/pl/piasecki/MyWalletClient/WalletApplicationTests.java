package pl.piasecki.MyWalletClient;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.piasecki.MyWalletClient.controller.ExpenditureController;
import pl.piasecki.MyWalletClient.controller.HomeController;
import pl.piasecki.MyWalletClient.controller.UserController;

@SpringBootTest
class WalletApplicationTests {

	@Autowired
	private HomeController homeController;
	@Autowired
	private ExpenditureController expenditureController;
	@Autowired
	private UserController userController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(homeController).isNotNull();
		assertThat(expenditureController).isNotNull();
		assertThat(userController).isNotNull();
		
	}

}
