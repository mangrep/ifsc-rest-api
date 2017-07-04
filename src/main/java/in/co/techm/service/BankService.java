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

    private static final String[] mBankList = new String[]{"ABHYUDAYA COOPERATIVE BANK LIMITED", "ABU DHABI COMMERCIAL BANK", "AHMEDABAD MERCANTILE COOPERATIVE BANK", "AIRTEL PAYMENTS BANK LIMITED", "AKOLA JANATA COMMERCIAL COOPERATIVE BANK", "ALLAHABAD BANK", "ALMORA URBAN COOPERATIVE BANK LIMITED", "ANDHRA BANK", "ANDHRA PRAGATHI GRAMEENA BANK", "APNA SAHAKARI BANK LIMITED", "AUSTRALIA AND NEW ZEALAND BANKING GROUP LIMITED", "AXIS BANK", "B N P PARIBAS", "BANDHAN BANK LIMITED", "BANK INTERNASIONAL INDONESIA", "BANK OF AMERICA", "BANK OF BAHARAIN AND KUWAIT BSC", "BANK OF BARODA", "BANK OF CEYLON", "BANK OF INDIA", "BANK OF MAHARASHTRA", "BANK OF TOKYO MITSUBISHI LIMITED", "BARCLAYS BANK", "BASSEIN CATHOLIC COOPERATIVE BANK LIMITED", "BHARAT COOPERATIVE BANK MUMBAI LIMITED", "CANARA BANK", "CAPITAL SMALL FINANCE BANK LIMITED", "CATHOLIC SYRIAN BANK LIMITED", "CENTRAL BANK OF INDIA", "CHINATRUST COMMERCIAL BANK LIMITED", "CITI BANK", "CITIZEN CREDIT COOPERATIVE BANK LIMITED", "CITY UNION BANK LIMITED", "COMMONWEALTH BANK OF AUSTRALIA", "CORPORATION BANK", "CREDIT AGRICOLE CORPORATE AND INVESTMENT BANK CALYON BANK", "CREDIT SUISEE AG", "DCB BANK LIMITED", "DENA BANK", "DEOGIRI NAGARI SAHAKARI BANK LTD. AURANGABAD", "DEPOSIT INSURANCE AND CREDIT GUARANTEE CORPORATION", "DEUSTCHE BANK", "DEVELOPMENT BANK OF SINGAPORE", "DHANALAKSHMI BANK", "DOHA BANK", "DOHA BANK QSC", "DOMBIVLI NAGARI SAHAKARI BANK LIMITED", "EQUITAS SMALL FINANCE BANK LIMITED", "ESAF SMALL FINANCE BANK LIMITED", "EXPORT IMPORT BANK OF INDIA", "FEDERAL BANK", "FIRSTRAND BANK LIMITED", "G P PARSIK BANK", "GURGAON GRAMIN BANK", "HDFC BANK", "HIMACHAL PRADESH STATE COOPERATIVE BANK LTD", "HSBC BANK", "HSBC BANK OMAN SAOG", "ICICI BANK LIMITED", "IDBI BANK", "IDFC BANK LIMITED", "IDUKKI DISTRICT CO OPERATIVE BANK LTD", "INDIAN BANK", "INDIAN OVERSEAS BANK", "INDUSIND BANK", "INDUSTRIAL AND COMMERCIAL BANK OF CHINA LIMITED", "INDUSTRIAL BANK OF KOREA", "JALGAON JANATA SAHAKARI BANK LIMITED", "JAMMU AND KASHMIR BANK LIMITED", "JANAKALYAN SAHAKARI BANK LIMITED", "JANASEVA SAHAKARI BANK BORIVLI LIMITED", "JANASEVA SAHAKARI BANK LIMITED", "JANATA SAHAKARI BANK LIMITED", "JP MORGAN BANK", "KALLAPPANNA AWADE ICHALKARANJI JANATA SAHAKARI BANK LIMITED", "KALUPUR COMMERCIAL COOPERATIVE BANK", "KALYAN JANATA SAHAKARI BANK", "KAPOL COOPERATIVE BANK LIMITED", "KARNATAKA BANK LIMITED", "KARNATAKA VIKAS GRAMEENA BANK", "KARUR VYSYA BANK", "KEB Hana Bank", "KERALA GRAMIN BANK", "KOTAK MAHINDRA BANK LIMITED", "LAXMI VILAS BANK", "MAHANAGAR COOPERATIVE BANK", "MAHARASHTRA STATE COOPERATIVE BANK", "MASHREQBANK PSC", "MIZUHO BANK LTD", "Maharashtra Gramin Bank", "NAGAR URBAN CO OPERATIVE BANK", "NAGPUR NAGARIK SAHAKARI BANK LIMITED", "NATIONAL AUSTRALIA BANK LIMITED", "NATIONAL BANK OF ABU DHABI PJSC", "NEW INDIA COOPERATIVE BANK LIMITED", "NKGSB COOPERATIVE BANK LIMITED", "NORTH MALABAR GRAMIN BANK", "NUTAN NAGARIK SAHAKARI BANK LIMITED", "OMAN INTERNATIONAL BANK SAOG", "ORIENTAL BANK OF COMMERCE", "PRAGATHI KRISHNA GRAMIN BANK", "PRATHAMA BANK", "PRIME COOPERATIVE BANK LIMITED", "PT BANK MAYBANK INDONESIA TBK", "PUNJAB AND MAHARSHTRA COOPERATIVE BANK", "PUNJAB AND SIND BANK", "PUNJAB NATIONAL BANK", "RABOBANK INTERNATIONAL", "RAJGURUNAGAR SAHAKARI BANK LIMITED", "RAJKOT NAGRIK SAHAKARI BANK LIMITED", "RBL Bank Limited", "RESERVE BANK OF INDIA, PAD", "SAHEBRAO DESHMUKH COOPERATIVE BANK LIMITED", "SAMARTH SAHAKARI BANK LTD", "SARASWAT COOPERATIVE BANK LIMITED", "SBER BANK", "SBM BANK MAURITIUS LIMITED", "SHIKSHAK SAHAKARI BANK LIMITED", "SHINHAN BANK", "SHIVALIK MERCANTILE CO OPERATIVE BANK LTD", "SHRI CHHATRAPATI RAJASHRI SHAHU URBAN COOPERATIVE BANK LIMITED", "SOCIETE GENERALE", "SOLAPUR JANATA SAHAKARI BANK LIMITED", "SOUTH INDIAN BANK", "STANDARD CHARTERED BANK", "STATE BANK OF BIKANER AND JAIPUR", "STATE BANK OF HYDERABAD", "STATE BANK OF INDIA", "STATE BANK OF MAURITIUS LIMITED", "STATE BANK OF MYSORE", "STATE BANK OF PATIALA", "STATE BANK OF TRAVANCORE", "SUMITOMO MITSUI BANKING CORPORATION", "SURAT NATIONAL COOPERATIVE BANK LIMITED", "SURYODAY SMALL FINANCE BANK LIMITED", "SUTEX COOPERATIVE BANK LIMITED", "SYNDICATE BANK", "TAMILNAD MERCANTILE BANK LIMITED", "TELANGANA STATE COOP APEX BANK", "THE A.P. MAHESH COOPERATIVE URBAN BANK LIMITED", "THE AKOLA DISTRICT CENTRAL COOPERATIVE BANK", "THE ANDHRA PRADESH STATE COOPERATIVE BANK LIMITED", "THE BANK OF NOVA SCOTIA", "THE BARAMATI SAHAKARI BANK LTD", "THE COSMOS CO OPERATIVE BANK LIMITED", "THE DELHI STATE COOPERATIVE BANK LIMITED", "THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LIMITED", "THE GREATER BOMBAY COOPERATIVE BANK LIMITED", "THE GUJARAT STATE COOPERATIVE BANK LIMITED", "THE HASTI COOP BANK LTD", "THE JALGAON PEOPELS COOPERATIVE BANK LIMITED", "THE KANGRA CENTRAL COOPERATIVE BANK LIMITED", "THE KANGRA COOPERATIVE BANK LIMITED", "THE KARAD URBAN COOPERATIVE BANK LIMITED", "THE KARANATAKA STATE COOPERATIVE APEX BANK LIMITED", "THE KURMANCHAL NAGAR SAHAKARI BANK LIMITED", "THE MEHSANA URBAN COOPERATIVE BANK", "THE MUMBAI DISTRICT CENTRAL COOPERATIVE BANK LIMITED", "THE MUNICIPAL COOPERATIVE BANK LIMITED", "THE NAINITAL BANK LIMITED", "THE NASIK MERCHANTS COOPERATIVE BANK LIMITED", "THE NAVNIRMAN CO-OPERATIVE BANK LIMITED", "THE PANDHARPUR URBAN CO OP. BANK LTD. PANDHARPUR", "THE RAJASTHAN STATE COOPERATIVE BANK LIMITED", "THE ROYAL BANK OF SCOTLAND N V", "THE SEVA VIKAS COOPERATIVE BANK LIMITED", "THE SHAMRAO VITHAL COOPERATIVE BANK", "THE SURAT DISTRICT COOPERATIVE BANK LIMITED", "THE SURATH PEOPLES COOPERATIVE BANK LIMITED", "THE TAMIL NADU STATE APEX COOPERATIVE BANK", "THE THANE BHARAT SAHAKARI BANK LIMITED", "THE THANE DISTRICT CENTRAL COOPERATIVE BANK LIMITED", "THE VARACHHA COOPERATIVE BANK LIMITED", "THE VISHWESHWAR SAHAKARI BANK LIMITED", "THE WEST BENGAL STATE COOPERATIVE BANK", "THE ZOROASTRIAN COOPERATIVE BANK LIMITED", "TJSB SAHAKARI BANK LIMITED", "TUMKUR GRAIN MERCHANTS COOPERATIVE BANK LIMITED", "UCO BANK", "UNION BANK OF INDIA", "UNITED BANK OF INDIA", "UNITED OVERSEAS BANK LIMITED", "UTKARSH SMALL FINANCE BANK", "Ujjivan Small Finance Bank Limited", "VASAI VIKAS SAHAKARI BANK LIMITED", "VIJAYA BANK", "WESTPAC BANKING CORPORATION", "WOORI BANK", "YES BANK", "ZILA SAHAKRI BANK LIMITED GHAZIABAD"};
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
