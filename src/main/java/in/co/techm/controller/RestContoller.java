package in.co.techm.controller;

import in.co.techm.model.LikeBranchSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.co.techm.model.Bank;
import in.co.techm.model.GenericResponse;
import in.co.techm.repository.BankRepository;
import in.co.techm.service.BankService;

import java.util.List;
import java.util.Set;

@RestController
public class RestContoller {

	@Autowired
	BankService mBankService;

	@RequestMapping(value = "/getbank/{bankName}/{branchName}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<GenericResponse<Bank>> getBank(@PathVariable(value = "bankName") String bankName,
			@PathVariable(value = "branchName") String branchName) {
		return mBankService.findByBranchAndBank(bankName, branchName);
	}


    @RequestMapping(value = "/listbanks", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<Set<String>>> listAllBankName() {
        return mBankService.listAllBankName();
    }

	@RequestMapping(value = "/listbranches/{bankName}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<GenericResponse<List<Bank>>> listBranchesByBankName(@PathVariable(value = "bankName") String bankName) {
		return mBankService.listBranchesByBankName(bankName);
	}

    @RequestMapping(value = "/v1/ifsc/{ifsc}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<Bank>> findByIfsc(@PathVariable(value = "ifsc") String ifsc) {
        return mBankService.findByIfsc(ifsc);
    }

    @RequestMapping(value = "/v1/micr/{micrcode}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<Bank>> findByMicrcode(@PathVariable(value = "micrcode") String micrcode) {
        return mBankService.findByMicrcode(micrcode);
    }

    @RequestMapping(value = "/bank/search/likeBranchName", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<GenericResponse<List<Bank>>> likeBranchNameSearch(@RequestBody LikeBranchSearch likeBranchSearch) {
        return mBankService.likeBranchNameSearch(likeBranchSearch);
    }
}
