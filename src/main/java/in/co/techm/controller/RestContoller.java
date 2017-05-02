package in.co.techm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.co.techm.service.BankService;

@RestController
public class RestContoller {
	
	@Autowired
	BankService mBankService;
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
