var express = require('express')
const path = require('path');
const http = require('http');
const bodyParser = require('body-parser');
const config = require('./config');
const fs = require('fs');
const SwaggerParser = require('swagger-parser');
var $RefParser = require('json-schema-ref-parser');
const app = express();
var request = require('request');

app.use(express.static(__dirname + '/'))
app.use(bodyParser.json());


function getType(type) {
    switch (type) {
        case 'integer':
            return 'number';
        case 'float':
            return 'number';
        case 'double':
            return 'number';
        case 'long':
            return 'number';
        case 'number':
            return 'number';
        case 'string':
            return 'text';
        case 'boolean':
            return 'radio';
        case 'date':
            return 'datePicker';
        case 'email':
            return 'email';
        case 'pan':
            return 'pan';
        case 'pinCode':
            return 'pinCode';
        case 'mobileNumber':
            return 'mobileNumber';
        case 'autoComplete':
            return 'autoCompelete';
        case 'aadhar':
            return 'aadhar';
        case 'checkbox':
            return 'checkbox';
        case 'singleValueList':
            return 'singleValueList';
        case 'multiValueList':
            return 'multiValueList';
        default:
            return '';
    }
}

var getFieldsFromInnerObject = function(fields, header, properties, module, master, jPath, isArray, required) {
    // console.log("Iner object called with - " + jPath);
    for (let key in properties) {
        if (["id", "tenantId", "auditDetails", "assigner"].indexOf(key) > -1) continue;
        if(properties[key].properties) 	{
        	//its an inner object - should be another MDMS object - make UI paint a singleValueList with appropriate URL
            fields.push({
                "name": key,
                "jsonPath": (isArray ? "MdmsMetadata.masterData[0]" : "MdmsMetadata.masterData") + "." + key,
                "label": module + ".create." + key,
                "type": "singleValueList",
                "isRequired": (properties[key].required || (required && required.constructor == Array && required.indexOf(key) > -1) ? true : false),
                "defaultValue": properties[key].default || "",
                "url": "/egov-mdms-service/v1/_get?&moduleName=" +  module + "&masterName=" 
                	+ master + "|$.MdmsRes." + module + "." + key + ".*.id|$.MdmsRes." + module + "." + key + ".*.name",
                "isStateLevel":true, 
                "apiKey": jPath + "." + key							
            });
            header.push({
            	"label": jPath + "." + module + "." + master + "." + key
            })
        } else if(properties[key].items && properties[key].items.properties) {
            if(jPath == "WasteSubType") console.log(jPath + " is an array");
            if(jPath.search("." + key) < 2)
                getFieldsFromInnerObject(fields, header, properties[key].items.properties, module, master, (isArray ? (jPath + "[0]") : jPath) + "." + key, true, (properties[key].items.properties.required || []));
        } else {
            fields.push({
                "name": key,
                "jsonPath": (isArray ? "MdmsMetadata.masterData[0]" : "MdmsMetadata.masterData") + "." + key,
                "label": module + ".create." + key,
                "pattern": properties[key].pattern || "",
                "type": properties[key].enum ? "singleValueList" : properties[key].format && ["number", "integer", "double", "long", "float"].indexOf(properties[key].type) == -1 ? getType(properties[key].format) : getType(properties[key].type),
                "isRequired": (properties[key].required || (required && required.constructor == Array && required.indexOf(key) > -1) ? true : false),
                "isDisabled": properties[key].readOnly ? true : false,
                "defaultValue": properties[key].default || "",
                "maxLength": properties[key].maxLength,
                "minLength": properties[key].minLength,
                "patternErrorMsg": properties[key].pattern ? (module + ".create.field.message." + key) : ""
            });
            header.push({
            	"label": jPath + "." + module + "." + master + "." + key
            })
            

        }
        
    }
    return {
        fields,
        header
    };
}

var configData = config.data;
var mainObj = {};
var completed_requests = 0;
var finalSpecs = {};
var urls = [];
var modules = [];

for(module in configData){
	urls.push(configData[module].url);
	modules.push(module);
}


// for(module in modules){
	for(var i = 0; i < urls.length; i++){
		SwaggerParser.dereference(urls[i])
        .then(function(yamlJSON) {
        	// console.log(yamlJSON.definitions.WasteSubType.properties)
        	let module = yamlJSON["x-module"];
        	if(module){
        		mainObj[module] = yamlJSON.definitions;	
        	}
        	else{
	        	let basePath = [];
	        	basePath = yamlJSON.basePath.split("-")[0].split("");		// /asset-services type pattern should be in basepath
	        	let index = basePath.indexOf("/");
	        	if(index > -1){
        			basePath.splice(index, 1);
        		}			

        		mainObj[basePath.join("")] = yamlJSON.definitions;
        	}
        	
        	completed_requests++;

        	if (completed_requests == urls.length) {
	            // All downloads done, process responses array
	            // console.log(mainObj);

	            for(moduleName in mainObj){
	            	// console.log("Main Object module- " + moduleName);
	            	console.log("module name is - " + moduleName);
	            	finalSpecs[moduleName.toLowerCase()] = {};
	            	for(master in mainObj[moduleName.toLowerCase()]){
	            		// console.log(property);
	            		// console.log("Master in Object module- " + master);
	            		if(!finalSpecs[moduleName].masters) finalSpecs[moduleName].masters = {};

	            		if(!finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()]) finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()] = {};
	            		if(!finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()]) finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()] = {};
	            		

	            		finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()].name = "";
	            		finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()].label = "";
	            		finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()].type = "multiFieldAddToTable";
	            		finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()].jsonPath = "";
	            		
	            		var header = [];
	            		var fields = [];
	            		var spec = getFieldsFromInnerObject(fields, header, mainObj[moduleName][master].properties, moduleName, master, master, true, mainObj[moduleName][master].required || []);

	            		finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()].header = spec.header;
	            		finalSpecs[moduleName.toLowerCase()].masters[master.toLowerCase()].values = spec.fields;
	            		// console.log("Break-------------------------------------------");
	            		// console.log(finalSpecs.swm.masters.CollectionPoint);
	            	}
	            	
	            }

	            console.log(finalSpecs.swm.masters.wastesubtype);
	            // console.log(finalSpecs.swm);
	        
	        }
		})
		.catch(function(err) {
            console.log(err);
        });
	}
	
// }


app.post('/spec-directory/:module/:master', function(req, res, next) {
	for (var key in req.params){ 
		req.params[key] = req.params[key].toLowerCase();
	}
	console.log(req.params);
	console.log(req.params.master);
	var master = req.params.master;
	var module = req.params.module;
	if(finalSpecs[module] && finalSpecs[module].masters[master]){
		res.status(200).json(finalSpecs[module].masters[master]);
	}
	else{
		res.status(400).json({
            message: "Invalid parameters"
        })
	}
	

	next();
});

app.get('/spec-directory', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'));
});



const port = process.env.PORT || '4022';
app.listen(port, function() {
    console.log('Parser listening on port: ' + port);
})