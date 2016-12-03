var express = require('express');
var ifsc = require('./ifsc');
var router = express.Router();

/* GET home page. */
router.get('/api/ifsc/:ifscCode', ifsc.findByIfscCode);
router.get('/api/micr/:micrCode', ifsc.findByMicrCode);
router.get('/api/branch/:branch', ifsc.findByBranch);
router.get('/api/listbanks', ifsc.listBanks);
router.get('/api/listbranches/:bankname', ifsc.listbranches);
router.get('/api/getbank/:bankname/:branchname', ifsc.findbyBankBranch);
router.get('/api/state/:state', ifsc.getListOfBankByState);
router.get('/api/district/:district', ifsc.getListOfBankByDistrict);

/* POST home page. */
router.post('/api/ifsc', ifsc.findByIfscCode);
router.post('/api/micr', ifsc.findByMicrCode);
router.post('/api/bank', ifsc.listByBankName);
router.post('/api/branch', ifsc.findByBranch);

// v1 apis
router.get('/api/v1/ifsc/:ifscCode', ifsc.findByIfscCode_v1);
router.get('/api/v1/micr/:micrCode', ifsc.findByMicrCode_v1);

//Handle invalid routes
router.get('*', function(req, res) {
	res.status(404) // HTTP status 404: NotFound
	.send({
		"status" : "failed",
		"message" : "Invalid route"
	});
});
module.exports = router;
