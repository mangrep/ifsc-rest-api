var mongo = require('mongodb');
var mongoServer = mongo.Server, mongoDb = mongo.Db, BSON = mongo.BSONPure;
var id = "0";
var dbName = 'ifsc';
var hostName = 'localhost';
var collection = 'ifsc_dtl';
var server = new mongoServer(hostName, 27017, {
	auto_reconnect : true
});

var db = new mongoDb(dbName, server);

/* open connection to db; */
db.open(function(err, db) {
	if (!err) {
		console.log("Success : Connected to ifsc Database..!!");
	} else {
		console.error("Error : Unable to connect to MonoDB" + err.toString());
	}

});

/* find documents with ifsc code */
exports.findByIfscCode = function(req, res, next) {
	id = "0";
	res.header("Access-Control-Allow-Origin", "*");
	if (req.method === "GET") {
		id = req.params.ifscCode;
	} else if (req.method === "POST") {
		id = req.body.ifscCode;
	}
	db.collection(collection, function(err, collection) {
		if (!err) {
			collection.find({
				'IFSC' : id
			}).toArray(function(err, items) {
				if (!err) {
					res.send(items);
				}
			});
		}
	});
};

/* get bank list with state name */
exports.getListOfBankByState = function(req, res, next) {
	state = "0";
	res.header("Access-Control-Allow-Origin", "*");
	if (req.method === "GET") {
		state = req.params.state;
	} else if (req.method === "POST") {
		state = req.body.state;
	}
	db.collection(collection, function(err, collection) {
		if (!err) {
			collection.find({
				'STATE' : state.toUpperCase()
			}).toArray(function(err, items) {
				if (!err && items.length > 0) {
					res.send({
						"status" : "success",
						"data" : items
					});
				} else {
					res.send({
						"status" : "failed",
						"message" : "No data found"
					});
				}
			});
		} else {
			res.send({
				"status" : "failed",
				"message" : "Somthing went wrong! Please try again"
			});
		}
	});
};
/* get bank list with district name */
exports.getListOfBankByDistrict = function(req, res, next) {
	district = "0";
	res.header("Access-Control-Allow-Origin", "*");
	if (req.method === "GET") {
		district = req.params.district;
	} else if (req.method === "POST") {
		district = req.body.state;
	}
	db.collection(collection, function(err, collection) {
		if (!err) {
			collection.find({
				'DISTRICT' : district.toUpperCase()
			}).toArray(function(err, items) {
				if (!err && items.length > 0) {
					res.send({
						"status" : "success",
						"data" : items
					});
				} else {
					res.send({
						"status" : "failed",
						"message" : "No data found"
					});
				}
			});
		} else {
			res.send({
				"status" : "failed",
				"message" : "Somthing went wrong! Please try again"
			});
		}
	});
};
/* find documents with micr code */
exports.findByMicrCode = function(req, res, next) {
	micrCode = "0";
	res.header("Access-Control-Allow-Origin", "*");
	if (req.method === "GET") {
		micrCode = req.params.micrCode;
	} else if (req.method === "POST") {
		micrCode = req.body.micrCode;
	}
	micrCode = micrCode.toUpperCase();
	db.collection(collection, function(err, collection) {
		if (!err) {
			collection.find({
				'MICR CODE' : micrCode
			}).toArray(function(err, items) {
				if (!err) {
					res.send(items);
				}
			});
		}
	});
};

/* find documents with bank name */
exports.listbranches = function(req, res, next) {
	bankName = req.params.bankname;
	res.header("Access-Control-Allow-Origin", "*");
	db.collection(collection, function(err, collection) {
		if (!err) {
			collection.distinct("BRANCH", {
				"BANK" : bankName.toUpperCase()
			}, {
				"BRANCH" : 1
			}, function(err, items) {
				if (!err) {
					if (items.length > 0)
						res.send({
							"status" : "success",
							"data" : items.sort()
						});
					else
						res.send({
							"status" : "failed",
							"message" : "No data found"
						});
				} else {
					res.send({
						"status" : "failed",
						"message" : "Somthing went wrong. Please try again."
					});
				}
			});
		}
	});
};

/* find documents with find Bank name */
exports.listByBankName = function(req, res, next) {
	bankname = "";
	res.header("Access-Control-Allow-Origin", "*");
	if (req.method === "GET") {
		bankname = req.params.bankname;
	} else if (req.method === "POST") {
		bankname = req.body.bankname;
	}

	db.collection(collection, function(err, collection) {
		if (!err) {
			collection.find({
				'BANK' : bankname.toUpperCase()
			}).toArray(function(err, items) {
				if (!err) {
					if (items.length > 0)
						res.send({
							"status" : "success",
							"data" : items
						});
					else
						res.send({
							"status" : "failed",
							"message" : "No data found"
						});
				} else {
					res.send({
						"status" : "failed",
						"message" : "Somthing went wrong. Please try again."
					});
				}
			});
		}
	});
};

/* find documents with branch name */
exports.findByBranch = function(req, res, next) {
	branchName = "0";
	res.header("Access-Control-Allow-Origin", "*");
	if (req.method === "GET") {
		branchName = req.params.branch;
	} else if (req.method === "POST") {
		branchName = req.body.branch;
	}
	branchName = branchName.toUpperCase();
	db.collection(collection, function(err, collection) {
		if (!err) {
			collection.find({
				'BRANCH' : branchName
			}).toArray(function(err, items) {
				if (!err) {
					if (items.length > 0)
						res.send(items);
					else
						res.send({
							"status" : "failed",
							"message" : "No data found"
						});
				} else {
					res.send({
						"status" : "failed",
						"message" : "Somthing went wrong. Please try again."
					});
				}
			});
		}
	});
};

/* get list of bank name */
exports.listBanks = function(req, res, next) {
	id = "0";
	res.header("Access-Control-Allow-Origin", "*");
	db.collection(collection, function(err, collection) {
		if (!err) {
			collection.distinct("BANK", function(err, items) {
				if (!err) {
					if (items.length > 0)
						res.send({
							"status" : "success",
							"data" : items.sort()
						});
					else
						res.send({
							"status" : "failed",
							"message" : "No data found"
						});
				} else {
					res.send({
						"status" : "failed",
						"message" : "Somthing went wrong. Please try again."
					});
				}
			});
		}
	});
};

exports.findbyBankBranch = function(req, res, next) {
	bankName = req.params.bankname;
	branchName = req.params.branchname;
	res.header("Access-Control-Allow-Origin", "*");
	db.collection(collection, function(err, collection) {
		if (!err) {
			collection.findOne({
				"BANK" : bankName,
				"BRANCH" : branchName
			}, function(err, items) {
				if (!err) {
					if (items) {
						items.MICRCODE = items['MICR CODE']
						res.send({
							"status" : "success",
							"data" : items
						});
					} else {
						res.send({
							"status" : "failed",
							"message" : "No data found"
						});
					}
				} else {
					res.send({
						"status" : "failed",
						"message" : "Somthing went wrong. Please try again."
					});
				}
			});
		}
	});
};
