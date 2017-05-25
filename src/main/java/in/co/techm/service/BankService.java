package in.co.techm.service;

import in.co.techm.model.Bank;
import in.co.techm.model.GenericResponse;
import in.co.techm.model.LikeBranchSearch;
import in.co.techm.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
public class BankService {

    @Autowired
    BankRepository mBankRepository;


    public ResponseEntity<GenericResponse<Bank>> findByBranchAndBank(String bankName, String branchName) {
        GenericResponse<Bank> response = new GenericResponse<>();
        Bank bank = mBankRepository.findByBranchIgnoreCaseAndBankIgnoreCase(branchName, bankName);
        if (Optional.ofNullable(bank).isPresent()) {
            response.setData(bank);
            response.setStatus("success");
        } else {
            response.setStatus("failed");
            response.setMessage("Not found");
        }
        return new ResponseEntity<GenericResponse<Bank>>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<Set<String>>> listAllBankName() {
        GenericResponse<Set<String>> response = new GenericResponse<>();
        Optional<Set<String>> bankNameList = Optional.of(new TreeSet<>());
        List<Bank> bankList = mBankRepository.findAll();
        for (Bank bank : bankList) {
            if (bank.getBank() != null && !bank.getBank().isEmpty() && !bankNameList.get().contains(bank.getBank())) {
                bankNameList.get().add(bank.getBank());
            }
        }
        if (bankNameList.get().size() > 0) {
            response.setData(bankNameList.get());
            response.setStatus("success");
        } else {
            response.setStatus("failed");
            response.setMessage("Something went wrong please try again");
            System.out.println("failed");
        }
        return new ResponseEntity<GenericResponse<Set<String>>>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<Bank>>> listBranchesByBankName(String bankName) {
        GenericResponse<List<Bank>> response = new GenericResponse<>();
        Optional<List<Bank>> bankList = mBankRepository.findByBankIgnoreCase(bankName);
        if (bankList.get().size() > 0) {
            response.setData(bankList.get());
            response.setStatus("success");
        } else {
            response.setStatus("failed");
            response.setMessage("No bank found");
            System.out.println("failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<Bank>>> findByBranch(String branchName) {
        GenericResponse<List<Bank>> response = new GenericResponse<>();
        Optional<List<Bank>> bankList = mBankRepository.findByBranchIgnoreCase(branchName);
        if (bankList.get().size() > 0) {
            response.setData(bankList.get());
            response.setStatus("success");
        } else {
            response.setStatus("failed");
            response.setMessage("No bank found");
            System.out.println("failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<Bank>> findByIfsc(String ifsc) {
        GenericResponse<Bank> response = new GenericResponse<>();
        Optional<Bank> bank = mBankRepository.findByIfsc(ifsc);
        if (bank.isPresent()) {
            response.setData(bank.get());
            response.setStatus("success");
        } else {
            response.setStatus("failed");
            response.setMessage("No bank found");
            System.out.println("failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<Bank>> findByMicrcode(String micrcode) {
        GenericResponse<Bank> response = new GenericResponse<>();
        Optional<Bank> bank = mBankRepository.findByMicrcode(micrcode);
        if (bank.isPresent()) {
            response.setData(bank.get());
            response.setStatus("success");
        } else {
            response.setStatus("failed");
            response.setMessage("No bank found");
            System.out.println("failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<Bank>>> likeBranchNameSearch(LikeBranchSearch likeBranchSearch) {
        GenericResponse<List<Bank>> response = new GenericResponse<>();
        Optional<List<Bank>> bankList = mBankRepository.findByBankIgnoreCaseAndBranch(likeBranchSearch.getBankName(), likeBranchSearch.getBranchName());
        if (bankList.get().size() > 0) {
            response.setData(bankList.get());
            response.setStatus("success");
        } else {
            response.setStatus("failed");
            response.setMessage("No bank found");
            System.out.println("failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<Bank>>> findByDistrict(String district) {
        GenericResponse<List<Bank>> response = new GenericResponse<>();
        Optional<List<Bank>> bankList = mBankRepository.findByDistrictIgnoreCase(district);
        if (bankList.get().size() > 0) {
            response.setData(bankList.get());
            response.setStatus("success");
        } else {
            response.setStatus("failed");
            response.setMessage("No bank found");
            System.out.println("failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<Bank>>> findByState(String state) {
        GenericResponse<List<Bank>> response = new GenericResponse<>();
        Optional<List<Bank>> bankList = mBankRepository.findByStateIgnoreCase(state);
        if (bankList.get().size() > 0) {
            response.setData(bankList.get());
            response.setStatus("success");
        } else {
            response.setStatus("failed");
            response.setMessage("No bank found");
            System.out.println("failed");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
