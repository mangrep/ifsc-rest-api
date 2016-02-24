# Rest API to get details of  NEFT enabled bank Branches (Bank-wise IFS Codes).
> Note: All details are taken from  [RBI website](https://www.rbi.org.in/Scripts/bs_viewcontent.aspx?Id=2009) last update Jan 22, 2016.

###Features provided 

 1. Get bank by IFSC code.
 2. Get bank by MICR code.
 3. Get banks by bank name.
 4. Get banks by branch location.
 
 ###Get bank by IFSC code.

####URI : http://techm.co.in:3000/api/ifsc/{IFSC CODE IN UPPERCASE}
Request Type: GET

Example:
 http://techm.co.in:3000/api/ifsc/SBIN0000138
Sample response:

    [{
    	"_id": "56a4e61d277fdd0a3417ebbb",
    	"STATE": "BIHAR",
    	"BANK": "STATE BANK OF INDIA",
    	"IFSC": "SBIN0000138",
    	"MICR CODE": "842002002",
    	"BRANCH": "MUZAFFARPUR",
    	"CONTACT": "0",
    	"ADDRESS": "MUZAFFARPUR, BIHAR, PIN 842001",
    	"CITY": "MUZAFFARPUR",
    	"DISTRICT": "MUZAFFARPUR"
    }]
###Get bank by MICR code
####URI : http://techm.co.in:3000/api/micr/{MICR CODE}
Request Type: GET

Example: 
http://techm.co.in:3000/api/micr/842002002

    [{
    	"_id": "56a4e61d277fdd0a3417ebbb",
    	"STATE": "BIHAR",
    	"BANK": "STATE BANK OF INDIA",
    	"IFSC": "SBIN0000138",
    	"MICR CODE": "842002002",
    	"BRANCH": "MUZAFFARPUR",
    	"CONTACT": "0",
    	"ADDRESS": "MUZAFFARPUR, BIHAR, PIN 842001",
    	"CITY": "MUZAFFARPUR",
    	"DISTRICT": "MUZAFFARPUR"
    }]

###Get banks by bank name name

####URI : http://techm.co.in:3000/api/bank/{Bank NAME in upper case}
Request Type: GET

Example:
http://techm.co.in:3000/api/bank/ABU%20DHABI%20COMMERCIAL%20BANK
Sample response:

    [{
    	"_id": "56a4e606277fdd0a341698cb",
    	"STATE": "MAHARASHTRA",
    	"BANK": "ABU DHABI COMMERCIAL BANK",
    	"IFSC": "ADCB0000001",
    	"MICR CODE": "400269002",
    	"BRANCH": "RTGS-HO",
    	"CONTACT": "39534100",
    	"ADDRESS": "75, REHMAT MANZIL, V. N. ROAD, CURCHGATE, MUMBAI - 400020",
    	"CITY": "MUMBAI",
    	"DISTRICT": "MUMBAI CITY"
    }, {
    	"_id": "56a4e606277fdd0a341698cc",
    	"STATE": "KARNATAKA",
    	"BANK": "ABU DHABI COMMERCIAL BANK",
    	"IFSC": "ADCB0000002",
    	"MICR CODE": "560269002",
    	"BRANCH": "BANGALORE",
    	"CONTACT": "25582000",
    	"ADDRESS": "CITI CENTRE, 28, CHURCH STREET, OFF M. G. ROAD BANGALORE 560001",
    	"CITY": "BANGALORE",
    	"DISTRICT": "BANGALORE URBAN"
    }]
####Get banks by branch location
URI : http://techm.co.in:3000/api/branch/
Request type: GET

Example:
http://techm.co.in:3000/api/branch/KASHMIR

    [{
    	"_id": "56a4e624277fdd0a34186909",
    	"": "",
    	"BANK": "UCO BANK",
    	"IFSC": "UCBA0001539",
    	"MICR CODE": "177028054",
    	"BRANCH": "KASHMIR",
    	"CONTACT": "239021",
    	"ADDRESS": "R V & PO KASHMIRDISTT-HAMIRPUR 177006",
    	"CITY": "KASHNIUR",
    	"DISTRICT": "HAMIRPUR",
    	"STATE": "HIMACHAL PRADESH"
    }]

