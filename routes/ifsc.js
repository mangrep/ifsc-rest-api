var mongo = require('mongodb');
var node_xj = require("xls-to-json");
var mongoServer = mongo.Server,
    mongoDb = mongo.Db,
    BSON = mongo.BSONPure;
var id="0";
var dbName = 'ifsc';
var hostName = 'localhost';
var collection = 'ifsc_dtl';
var server = new mongoServer(hostName,27017,{auto_reconnect:true});

var db = new mongoDb(dbName,server);

/*open connection to db;*/
db.open(function(err,db){
    if(!err){
        console.log("Success : Connected to ifsc Database..!!");
        db.collection(collection, {strict:true}, function(err,collection){
         if(err){
         console.log("WARN : Cannot find ifsc_dtl, So creating one..!!");
         }
         });
    } else {
        console.error("Error : "+err.toString());
    }

});

/*find documents with ifsc code*/
exports.findByIfscCode = function(req, res, next){
    id ="0";
    if(req.method === "GET"){
        id = req.params.ifscCode;
    }else if(req.method === "POST"){
        id = req.body.ifscCode;
    }
    db.collection(collection,function(err,collection){
        if(!err){
            collection.find({'IFSC': id}).toArray(function(err,items){
                if(!err){
                    res.send(items);
                }
            });
        }
    });
};

/*find documents with micr code*/
exports.findByMicrCode = function(req, res, next){
    id ="0";
    if(req.method === "GET"){
        id = req.params.micrCode;
    }else if(req.method === "POST"){
        id = req.body.micrCode;
    }
    db.collection(collection,function(err,collection){
        if(!err){
            collection.find({'MICR CODE': id}).toArray(function(err,items){
                if(!err){
                    res.send(items);
                }
            });
        }
    });
};

/*find documents with bank name*/
exports.findByBank = function(req, res, next){
    id ="0";
    if(req.method === "GET"){
        id = req.params.bank;
    }else if(req.method === "POST"){
        id = req.body.bank;
    }
    db.collection(collection,function(err,collection){
        if(!err){
            collection.find({'BANK': id}).toArray(function(err,items){
                if(!err){
                    res.send(items);
                }
            });
        }
    });
};

/*find documents with branch name*/
exports.findByBranch = function(req, res, next){
    id ="0";
    if(req.method === "GET"){
        id = req.params.branch;
    }else if(req.method === "POST"){
        id = req.body.branch;
    }
    db.collection(collection,function(err,collection){
        if(!err){
            collection.find({'BRANCH': id}).toArray(function(err,items){
                if(!err){
                    res.send(items);
                }
            });
        }
    });
};

/*insert data to ifsc_dtl collection from the.xls file*/
exports.addDataToDb = function(req, res, next){
    node_xj({
        input: "/home/arjun/Downloads/IFCB2009_76.xls",  // input xls
        output: "/home/arjun/Downloads/ifsc.json" // output json
    }, function(err, result) {
        if(err) {
            console.error(err);
        } else {
            db.collection(collection,function(err,collection){
                if(!err){
                    collection.insert(result,{safe:true},function(err,items){
                        if(!err){
                            res.send(result);
                        }
                    });
                }
            });
        }
    });
};