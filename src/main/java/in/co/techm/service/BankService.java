package in.co.techm.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

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

	public ResponseEntity<GenericResponse<Set<String>>> listAllBankName() {
		GenericResponse<Set<String>> response = new GenericResponse<>();
        Optional<Set<String>> bankNameList = Optional.of(new TreeSet<>());
		List<Bank> bankList = mBankRepository.findAll();
        for(Bank bank: bankList){
          if(bank.getBank() != null && !bank.getBank().isEmpty() && !bankNameList.get().contains(bank.getBank())){
              bankNameList.get().add(bank.getBank());
          }
        }
		if( bankNameList.get().size() > 0){
			response.setData(bankNameList.get());
			response.setStatus("success");
		}else{
			response.setStatus("failed");
			response.setMessage("Something went wrong please try again");
            System.out.println("failed");
		}
        return new ResponseEntity<GenericResponse<Set<String>>>(response, HttpStatus.OK);
	}
	
}
