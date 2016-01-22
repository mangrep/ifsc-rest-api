var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
    res.render('index', {title: 'Express'});
});

router.get('/api/ifsc/:ifsc', function (req, res, next) {
    console.log("IFSC passed is " + req.params.ifsc);
    
});

module.exports = router;
