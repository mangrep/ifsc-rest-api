package in.co.techm;

import in.co.techm.repository.BankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankApIsApplicationTests {
    @Autowired
    BankRepository mBankRepository;

    @Test
    public void contextLoads() {
        System.out.println("size is" + mBankRepository.findAll().size());
    }

    @Test
    public void getByBankBranch() {
        System.out.println("Result is" + mBankRepository.findByBranchIgnoreCaseAndBankIgnoreCase("STATE BANK OF INDIA", "MUZAFFARPUR"));
    }
}
