package in.co.techm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.co.techm.model.Bank;
import in.co.techm.model.GenericResponse;
import in.co.techm.repository.BankRepository;

@Service
public class BankService {

	@Autowired
	BankRepository mBankRepository;

	
	public ResponseEntity<GenericResponse<Bank>> findByBranchAndBank(String bankName, String branchName) {
		GenericResponse<Bank> response = new GenericResponse<>();
		Bank bank = mBankRepository.findByBranchAndBank(branchName, bankName);
		if(Optional.ofNullable(bank).isPresent()){
			response.setData(bank);
			response.setStatus("success");
		}else{
			response.setStatus("failed");
			response.setMessage("Not found");
		}
		
		return new ResponseEntity<GenericResponse<Bank>>(response, HttpStatus.OK);
		
	}
	
}
