package in.co.techm.controller;

import in.co.techm.model.Bank;
import in.co.techm.model.Banks;
import in.co.techm.model.GenericResponse;
import in.co.techm.model.LikeBranchSearch;
import in.co.techm.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    BankService mBankService;

    /**
     * @api {get} /api/getbank/:bankName/:branchName by bank and branch name
     * @apiDescription If you have both valid bank and branch name. Use this API get the matching bank object.
     * @apiName getBank
     * @apiVersion 0.0.1
     * @apiGroup Bank lookup
     * @apiParam {String} bankName You must use bank name provided in response of listbanks api.
     * @apiParam {String} branchName You must use branch name provided in response of listbranches api.
     * @apiExample Sample
     * https://api.techm.co.in/api/getbank/YES%20BANK/ASSOCIATE%20COOP%20BANK%20UMARWADA
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {"status":"success","data":{"STATE":"state name","BANK":"bank name","IFSC":"ifsc code","BRANCH":"branch name","ADDRESS":"address","CONTACT":"bank contact number","CITY":"city","DISTRICT":"district","MICRCODE":"micrcode"}}
     * 
     * * @apiErrorExample Error-Response:
     * HTTP/1.1 200 OK
     * {"status": "failed", "message": "Failure cause"}
     */
    @RequestMapping(value = "/api/getbank/{bankName:.+}/{branchName:.+}", method = RequestMethod.GET)
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

    /**
     * @api {get} /api/listbanks get list of supported banks
     * @apiDescription Get list of supported banks. Use it as input to other APIs.
     * @apiName listAllBankName
     * @apiGroup Supported banks
     * @apiVersion 0.0.1
     * @apiExample Sample
     * https://api.techm.co.in/api/listbanks
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {"status": "success", "data":["Bank A","Bank B", "Bank C", .............]}
     * 
     * * @apiErrorExample Error-Response:
     * HTTP/1.1 200 OK
     * {"status": "failed", "message": "Failure cause"}
     */
    @RequestMapping(value = "/api/listbanks", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<String[]>> listAllBankName() {
        return mBankService.listAllBankName();
    }

    /**
     * @api {get} /api/listbranches/:bankName get list of branches
     * @apiDescription Get list of branches by bank name. Use it as input to other APIs.
     * @apiName listBranchesByBankName
     * @apiVersion 0.0.1
     * @apiGroup Supported banks
     * @apiParam {String} bankName You must use bank name provided in response of listbanks api.
     * @apiExample Sample
     * https://api.techm.co.in/api/listbranches/Yes%20bank
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {"status":"success","data":["ABIDS, HYDERABAD","ABU ROAD", ....]}
     *
     * * @apiErrorExample Error-Response:
     * HTTP/1.1 200 OK
     * {"status": "failed", "message": "Failure cause"}
     */
    @RequestMapping(value = "/api/listbranches/{bankName:.+}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<Set<String>>> listBranchesByBankName(@PathVariable(value = "bankName") String bankName) {
        return mBankService.listBranchesByBankName(bankName);
    }

    /**
     * @api {get} /api/branch/:branchName by branch name
     * @apiDescription If you just have branch name, use this API to get list of banks objects by matching branch name.
     * @apiName branchesByBranchName
     * @apiVersion 0.0.1
     * @apiGroup Bank lookup
     * @apiParam {String} branchName You must use branch name provided in response of listbranches api.
     * @apiExample Sample
     * https://api.techm.co.in/api/branch/KASHMIR
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {"status":"success","data":[{"STATE":"HIMACHAL PRADESH","BANK":"UCO BANK","IFSC":"UCBA0001539","BRANCH":"KASHMIR","ADDRESS":"R V & PO KASHMIRDISTT-HAMIRPUR 177006","CONTACT":"239021","CITY":"KASHNIUR","DISTRICT":"HAMIRPUR","MICRCODE":"177028054"}],"message":null}
     * 
     * * @apiErrorExample Error-Response:
     * HTTP/1.1 200 OK
     * {"status": "failed", "message": "Failure cause"}
     */
    @RequestMapping(value = "/api/branch/{branch:.+}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<Banks>> branchesByBranchName(@PathVariable(value = "branch") String branchName) {
        return mBankService.findByBranch(branchName);
    }

    /**
     * @api {get} /api/v1/ifsc/:ifsCode by IFS Code
     * @apiName findByIfsc
     * @apiGroup Bank lookup
     * @apiVersion 0.0.1
     * @apiParam {String} ifsCode Pass IFS Code
     * @apiExample Sample
     * https://api.techm.co.in/api/v1/ifsc/ANDB0001154
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {"status":"success","data":{"STATE":"ANDHRA PRADESH","BANK":"ANDHRA BANK","IFSC":"ANDB0001154","BRANCH":"SAROORNAGAR","CONTACT":"0","ADDRESS":"11-3-25&11-3-26 ABHITEJA HOMES, ROAD NO 11SRI VENKATESWARA COLONY, SAROOR NAGAR, HYD","CITY":"SAROORNAGAR","DISTRICT":"HYDERABAD URBAN","MICRCODE":"500011075"}}
     * 
     * * @apiErrorExample Error-Response:
     * HTTP/1.1 200 OK
     * {"status": "failed", "message": "Failure cause"}
     */
    @RequestMapping(value = "/api/v1/ifsc/{ifsc}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<Bank>> findByIfsc(@PathVariable(value = "ifsc") String ifsc) {
        return mBankService.findByIfsc(ifsc);
    }

    /**
     * @api {get} /api/v1/micr/:micrCode by MICR code
     * @apiName micrCode
     * @apiVersion 0.0.1
     * @apiGroup Bank lookup
     * @apiParam {String} micrCode Pass MICR code
     * @apiExample Sample
     * https://api.techm.co.in/api/v1/micr/500011075
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {"status":"success","data":{"_id":"5925da7185059ea58005a4ef","STATE":"ANDHRA PRADESH","BANK":"ANDHRA BANK","IFSC":"ANDB0001154","BRANCH":"SAROORNAGAR","ADDRESS":"11-3-25&11-3-26 ABHITEJA HOMES, ROAD NO 11SRI VENKATESWARA COLONY, SAROOR NAGAR, HYD","CONTACT":"0","CITY":"SAROORNAGAR","DISTRICT":"HYDERABAD URBAN","MICRCODE":"500011075"},"message":null}
     * 
     * * @apiErrorExample Error-Response:
     * HTTP/1.1 200 OK
     * {"status": "failed", "message": "Failure cause"}
     */
    @RequestMapping(value = "/api/v1/micr/{micrcode}", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<GenericResponse<Bank>> findByMicrcode(@PathVariable(value = "micrcode") String micrcode) {
        return mBankService.findByMicrcode(micrcode);
    }

    /**
     * @api {post} /api/bank/search/likeBranchName Search by incomplete bank name and branch name
     * @apiDescription You have incomplete bank name and branch name and want to check if there is any match available use this api.
     * @apiGroup Bank lookup
     * @apiVersion 0.0.1
     * @apiParam {String} bankName Fuzzy bank name
     * @apiParam {String} branchName Fuzzy bank name
     * @apiParamExample {json} Input
     *    {
     *      "bankName": "AnDhRa BA",
     *      "branchName" : "GHAN"
     *    }
     * @apiSuccessExample {json} Success-Response:
     *    HTTP/1.1 200 OK
     *    {"status":"success","data":[{"_id":"5925da7185059ea580059e15","STATE":"TELANGANA","BANK":"ANDHRA BANK","IFSC":"ANDB0002958","BRANCH":"STATION GHANPUR","ADDRESS":"H NO 1 TO 12,STATION ROAD,STATION GHANPUR,WARANGAL DIST,506144","CONTACT":"2551058","CITY":"WARANGAL","DISTRICT":"WARANGAL","MICRCODE":"506011547"},{"_id":"5925da7185059ea580059eb0","STATE":"ANDHRA PRADESH","BANK":"ANDHRA BANK","IFSC":"ANDB0002801","BRANCH":"GHANI","ADDRESS":"MAIN ROAD,GHANI VILLAGE,GADIVEMULA MANDAL,KURNOOL DIST,518010","CONTACT":"9908764545","CITY":"GADIVEMULA","DISTRICT":"KURNOOL","MICRCODE":"NA"},{"_id":"5925da7185059ea58005a310","STATE":"MAHARASHTRA","BANK":"ANDHRA BANK","IFSC":"ANDB0001649","BRANCH":"GHANSOLI","ADDRESS":"SHOP-7,8,9,SURYA KIRAN,PLOT-12 &13,SEC-5,GHANSOLI N MUMBAI-400701","CONTACT":"0","CITY":"GHANSOLI","DISTRICT":"GREATER MUMBAI","MICRCODE":"400011034"},{"_id":"5925da7185059ea58005a605","STATE":"ANDHRA PRADESH","BANK":"ANDHRA BANK","IFSC":"ANDB0000849","BRANCH":"MULUG GHANPUR","ADDRESS":"MULUGU GHANPURWARANGAL DIST,AP","CONTACT":"0","CITY":"WARANGAL","DISTRICT":"WARANGAL","MICRCODE":"506011522"},{"_id":"5925da7185059ea58005a88b","STATE":"ANDHRA PRADESH","BANK":"ANDHRA BANK","IFSC":"ANDB0000169","BRANCH":"GHANPUR","ADDRESS":"MAIN RD., KHILLA GHANPUR, WANAPARTHY TQ.","CONTACT":"0","CITY":"WANAPARTHI","DISTRICT":"MAHBUBNAGAR","MICRCODE":"509011682"},{"_id":"5925da7185059ea58005a8a5","STATE":"ANDHRA PRADESH","BANK":"ANDHRA BANK","IFSC":"ANDB0000143","BRANCH":"GHANTASALA","ADDRESS":"MAIN ROAD,GHANTASALA,KRISHNA DIST,AP","CONTACT":"0","CITY":"GHANTASALA","DISTRICT":"KRISHNA","MICRCODE":"520011663"}],"message":null}
     *
     * @apiErrorExample {json} Error-Response:
     *    HTTP/1.1 200
     *    {"status": "failed", "message": "Failure cause"}
     */
    @RequestMapping(value = "/api/bank/search/likeBranchName", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<GenericResponse<List<Bank>>> likeBranchNameSearch(@RequestBody LikeBranchSearch likeBranchSearch) {
        return mBankService.likeBranchNameSearch(likeBranchSearch);
    }

    /**
     * @api {post} /api/fuzzySearchBank Fuzzy search by bank name
     * @apiDescription Get list of banks names matching fuzzy bank name provided. Internally levenshtein distance algorithm is used.
     * @apiGroup Fuzzy Search
     * @apiVersion 0.0.1
     * @apiParam {String} bankName Fuzzy bank name
     * @apiParamExample {json} Input
     *    {
     *      "bankName": "stat b"
     *    }
     * @apiSuccessExample {json} Success-Response:
     *    HTTP/1.1 200 OK
     *    {"status":"success","data":["B N P PARIBAS","HIMACHAL PRADESH STATE COOPERATIVE BANK LTD","STATE BANK OF BIKANER AND JAIPUR","STATE BANK OF HYDERABAD","STATE BANK OF INDIA","STATE BANK OF MAURITIUS LIMITED","STATE BANK OF MYSORE","STATE BANK OF PATIALA","STATE BANK OF TRAVANCORE","TELANGANA STATE COOP APEX BANK","THE ANDHRA PRADESH STATE COOPERATIVE BANK LIMITED","THE DELHI STATE COOPERATIVE BANK LIMITED","THE GUJARAT STATE COOPERATIVE BANK LIMITED","THE KARANATAKA STATE COOPERATIVE APEX BANK LIMITED","THE TAMIL NADU STATE APEX COOPERATIVE BANK","MAHARASHTRA STATE COOPERATIVE BANK","THE WEST BENGAL STATE COOPERATIVE BANK"],"message":null}
     *
     * @apiErrorExample {json} Error-Response:
     *    HTTP/1.1 200
     *    {"status": "failed", "message": "Failure cause"}
     */
    @RequestMapping(value = "/api/fuzzySearchBank", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<GenericResponse<List<String>>> fuzzySearchBank(@RequestBody LikeBranchSearch likeBranchSearch) {
        return mBankService.fuzzySearchBank(likeBranchSearch);
    }

    /**
     * @api {post} /api/fuzzySearchBranch Fuzzy Search by branch name
     * @apiDescription  Get list of branch names matching fuzzy branch name provided. Internally levenshtein distance algorithm is used.
     * @apiName fuzzySearchBranch
     * @apiVersion 0.0.1
     * @apiGroup Fuzzy Search
     * @apiHeader {String} Content-Type Pass application/json.
     * @apiParam {String} bankName Pass bank name. Make sure bank name is valid.
     * @apiParam {String} branchName Pass fuzzy branch name
     * @apiParamExample {json} Request-Example:
     *                      {
     *                           "bankName":"ANDHRA BANK",
     *                            "branchName":"GHANT"
     *                      }
     *
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {"status":"success","data":["NOGHANVADAR","SINGHANIA COMPLEX, SHAHDOL","GHANTA GHAR, HARDA","GHANTAGHAR, BUDAUN","SINGHANA ROAD, NARNAUL","GHANAHATTI  PARHECH","GHANSOLI","STATION GHANPUR","GHANTASALA","MADHOSINGHANA","PRACHI (GHANTIA)","GHANGHARI","MEGHANINAGAR","GHANSHYAMPUR","GHANTAGHAR, KATNI","CHAUSARI GHANTA","GHANSORE","GHANSALI","GHANAD","GOHANA","KALPA-KAMDEOBIGHA","SAHIJPUR BOGHA","BARBIGHA","JANDUSINGHA","GHAT","KALMEGHA BAZAR","TELESINGHA NTPC CAMPUS","GHARUAN","GARHANI"],"message":null}
     *
     * @apiErrorExample Error-Response:
     * HTTP/1.1 200 OK
     * {"status": "failed", "message": "Failure cause"}
     */
    @RequestMapping(value = "/api/fuzzySearchBranch", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<GenericResponse<List<String>>> fuzzySearchBranch(@RequestBody LikeBranchSearch likeBranchSearch) {
        return mBankService.fuzzySearchBranch(likeBranchSearch);
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
