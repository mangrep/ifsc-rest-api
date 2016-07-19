var express = require('express');
var ifsc = require('./ifsc');
var router = express.Router();

/* GET home page. */
router.get('/api/ifsc/:ifscCode', ifsc.findByIfscCode);
router.get('/api/micr/:micrCode', ifsc.findByMicrCode);
router.get('/api/bank/:bank', ifsc.listByBankName);
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

module.exports = router;
