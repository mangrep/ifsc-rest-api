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

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	BankService mBankService;

	@RequestMapping(value = "/api/getbank/{bankName}/{branchName}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<GenericResponse<Bank>> getBank(@PathVariable(value = "bankName") String bankName,
			@PathVariable(value = "branchName") String branchName) {
		return mBankService.findByBranchAndBank(bankName, branchName);
	}

    //TODO: Similar to like search. Might be not useful
    @RequestMapping(value = "/api/getbank", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<GenericResponse<Bank>> getBank(@RequestBody LikeBranchSearch searchByBankBranch) {
        return mBankService.findByBranchAndBank(searchByBankBranch.getBankName(), searchByBankBranch.getBranchName());
    }

    @RequestMapping(value = "/api/listbanks", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<Set<String>>> listAllBankName() {
        return mBankService.listAllBankName();
    }

	@RequestMapping(value = "/api/listbranches/{bankName}", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<GenericResponse<List<Bank>>> listBranchesByBankName(@PathVariable(value = "bankName") String bankName) {
		return mBankService.listBranchesByBankName(bankName);
	}

    @RequestMapping(value = "/api/branch/{branch}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<List<Bank>>> branchesByBranchName(@PathVariable(value = "branch") String branchName) {
        return mBankService.findByBranch(branchName);
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

    @RequestMapping(value = "/api/bank/search/likeBranchName", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<GenericResponse<List<Bank>>> likeBranchNameSearch(@RequestBody LikeBranchSearch likeBranchSearch) {
        return mBankService.likeBranchNameSearch(likeBranchSearch);
    }

    @RequestMapping(value = "/api/district/{district}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<List<Bank>>> findByDistrict(@PathVariable(value = "district") String district) {
        return mBankService.findByDistrict(district);
    }

    @RequestMapping(value = "/api/state/{state}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<List<Bank>>> findByState(@PathVariable(value = "state") String state) {
        return mBankService.findByState(state);
    }
}
