package in.co.techm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.techm.repository.BankRepository;

@Service
public class BankService {

	@Autowired
	BankRepository mBankRepository;
}
