package in.co.techm.service;

import in.co.techm.model.Bank;
import in.co.techm.model.Banks;
import in.co.techm.model.GenericResponse;
import in.co.techm.model.LikeBranchSearch;
import in.co.techm.repository.BankRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class BankService {

    @Autowired
    BankRepository mBankRepository;

    private static String[] mBankList;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int LEVENSHTEIN_CUTOFF = 70;
    private static final String STATUS_FAILED = "failed";
    private static final String STATUS_SUCCESS = "success";


    public ResponseEntity<GenericResponse<Bank>> findByBranchAndBank(String bankName, String branchName) {
        GenericResponse<Bank> response = new GenericResponse<>();
        Bank bank = mBankRepository.findByBranchIgnoreCaseAndBankIgnoreCase(branchName, bankName);
        if (Optional.ofNullable(bank).isPresent()) {
            response.setData(bank);
            response.setStatus(STATUS_SUCCESS);
        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("Not found");
        }
        return new ResponseEntity<GenericResponse<Bank>>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<String[]>> listAllBankName() {
        if (mBankList == null || mBankList.length == 0) {
            Optional<Set<String>> bankNameList = Optional.of(new TreeSet<>());
            List<Bank> bankList = mBankRepository.findAll();
            for (Bank bank : bankList) {
                if (bank.getBank() != null && !bank.getBank().isEmpty() && !bankNameList.get().contains(bank.getBank())) {
                    bankNameList.get().add(bank.getBank());
                }
            }
            mBankList = bankNameList.get().toArray(new String[bankNameList.get().size()]);
        }
        GenericResponse<String[]> response = new GenericResponse<>();
        response.setData(mBankList);
        response.setStatus(STATUS_SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<Set<String>>> listBranchesByBankName(String bankName) {
        GenericResponse<Set<String>> response = new GenericResponse<>();
        Optional<Set<String>> branchNameList = Optional.of(new TreeSet<>());
        Optional<List<Bank>> bankList = mBankRepository.findByBankIgnoreCase(bankName);
        if (bankList.get().size() > 0) {
            for (Bank bank : bankList.get()) {
                if (bank.getBranch() != null && !bank.getBranch().isEmpty() && !branchNameList.get().contains(bank.getBranch())) {
                    branchNameList.get().add(bank.getBranch());
                }
            }
            response.setData(branchNameList.get());
            response.setStatus(STATUS_SUCCESS);
        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("No branch found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<Banks>> findByBranch(String branchName) {
        GenericResponse<Banks> response = new GenericResponse<>();
        Optional<List<Bank>> bankList = mBankRepository.findByBranchIgnoreCase(branchName);
        if (bankList.get().size() > 0) {
            response.setData(new Banks(bankList.get()));
            response.setStatus(STATUS_SUCCESS);
        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("No bank found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<Bank>> findByIfsc(String ifsc) {
        GenericResponse<Bank> response = new GenericResponse<>();
        Optional<Bank> bank = mBankRepository.findByIfsc(ifsc);
        if (bank.isPresent()) {
            response.setData(bank.get());
            response.setStatus(STATUS_SUCCESS);
        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("No bank found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<Bank>> findByMicrcode(String micrcode) {
        GenericResponse<Bank> response = new GenericResponse<>();
        Optional<Bank> bank = mBankRepository.findByMicrcode(micrcode);
        if (bank.isPresent()) {
            response.setData(bank.get());
            response.setStatus(STATUS_SUCCESS);
        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("No bank found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<Bank>>> likeBranchNameSearch(LikeBranchSearch likeBranchSearch) {
        GenericResponse<List<Bank>> response = new GenericResponse<>();
        Optional<List<Bank>> bankList = mBankRepository.findByBankIgnoreCaseAndBranch(likeBranchSearch.getBankName(), likeBranchSearch.getBranchName());
        if (bankList.get().size() > 0) {
            response.setData(bankList.get());
            response.setStatus(STATUS_SUCCESS);
        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("No bank found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<String>>> fuzzySearchBank(LikeBranchSearch likeBranchSearch) {
        GenericResponse<List<String>> response = new GenericResponse<>();
        if (mBankList != null && mBankList.length > 0) {
            List<ExtractedResult> levenshteinList = FuzzySearch.extractSorted(likeBranchSearch.getBankName(), Arrays.asList(mBankList), LEVENSHTEIN_CUTOFF);
            List<String> bankList = levenshteinList.stream().map(ExtractedResult::getString).collect(Collectors.toList());
            if (bankList.size() > 0) {
                response.setData(bankList);
                response.setStatus(STATUS_SUCCESS);
            } else {
                response.setStatus(STATUS_FAILED);
                response.setMessage("No bank found with name " + likeBranchSearch.getBankName());
            }

        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("Something went wrong. Please try again");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<String>>> fuzzySearchBranch(LikeBranchSearch likeBranchSearch) {
        GenericResponse<List<String>> response = new GenericResponse<>();
        Optional<List<Bank>> banks = mBankRepository.findByBankIgnoreCase(likeBranchSearch.getBankName());

        if (banks.isPresent()) {
            List<String> branchNameList = banks.get().stream().map(Bank::getBranch).collect(Collectors.toList());
            List<ExtractedResult> levenshteinList = FuzzySearch.extractSorted(likeBranchSearch.getBranchName(), branchNameList, LEVENSHTEIN_CUTOFF);
            List<String> branchList = levenshteinList.stream().map(ExtractedResult::getString).collect(Collectors.toList());
            if (branchList.size() > 0) {
                response.setData(branchList);
                response.setStatus(STATUS_SUCCESS);
            } else {
                response.setStatus(STATUS_FAILED);
                response.setMessage("No matching branch found ");
            }

        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("No bank found with name " + likeBranchSearch.getBankName());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<Bank>>> findByDistrict(String district) {
        GenericResponse<List<Bank>> response = new GenericResponse<>();
        Optional<List<Bank>> bankList = mBankRepository.findByDistrictIgnoreCase(district);
        if (bankList.get().size() > 0) {
            response.setData(bankList.get());
            response.setStatus(STATUS_SUCCESS);
        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("No bank found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenericResponse<List<Bank>>> findByState(String state) {
        GenericResponse<List<Bank>> response = new GenericResponse<>();
        Optional<List<Bank>> bankList = mBankRepository.findByStateIgnoreCase(state);
        if (bankList.get().size() > 0) {
            response.setData(bankList.get());
            response.setStatus(STATUS_SUCCESS);
        } else {
            response.setStatus(STATUS_FAILED);
            response.setMessage("No bank found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
