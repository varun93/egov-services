var dat = {
  "swm.search": {
    "numCols": 4,
    "useTimestamp": true,
    "objectName":"sourceSegregations",
    "url": "/swm-services/sourcesegregations/_search",
    "groups": [
      {
        "name": "search",
        "label": "swm.sourcesegregation.search.title",
        "fields": [
          {
            "name": "sourceSegregationDate",
            "jsonPath": "sourceSegregationDate",
            "label": "swm.create.sourceSegregationDate",
            "type": "datePicker",
            "isDisabled": false,
            "patternErrorMsg": "swm.create.field.message.sourceSegregationDate"
          },
          {
            "name": "dumpingGroundCode",
            "jsonPath": "dumpingGroundCode",
            "label": "swm.create.dumpingGround",
            "type": "singleValueList",
            "isDisabled": false,
            "patternErrorMsg": "swm.create.field.message.dumpingGroundCode",
	    "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=DumpingGround|$..code|$..name"
          }
        ]
      }
    ],
    "result": {
      "header": [
        {
          "label": "swm.search.result.dumpingGround"
        },
        {
          "label": "swm.search.result.sourceSegregationDate",
	  "isDate": true
        }
      ],
      "values": [
        "dumpingGround.code",
        "sourceSegregationDate"
      ],
      "resultPath": "sourceSegregations",
      "rowClickUrlUpdate": "/update/swm/sourcesegregations/{code}",
      "rowClickUrlView": "/view/swm/sourcesegregations/{code}"
    }
  },
  "swm.create": {
    "numCols": 4,
    "useTimestamp": true,
    "objectName": "sourceSegregations",
    "idJsonPath": "sourceSegregations[0].code",
    "groups": [
      {
        "name": "SourceSegregationDetails",
        "label": "swm.create.group.title.SourceSegregationDetails",
        "fields": [
          {
            "name": "code",
            "jsonPath": "sourceSegregations[0].dumpingGround.code",
            "label": "swm.create.dumpingGround",
            "pattern": "",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": "",
	    "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=DumpingGround|$..code|$..name"
          },
          {
            "name": "sourceSegregationDate",
            "jsonPath": "sourceSegregations[0].sourceSegregationDate",
            "label": "swm.create.sourceSegregationDate",
            "pattern": "",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "patternErrorMsg": ""
          }
        ]
      },
      {
        "name": "CollectionTypeDetails",
        "label": "swm.create.group.title.CollectionTypeDetails",
	"jsonPath": "sourceSegregations[0].collectionDetails[0]",
	"multiple":true,
        "fields": [
          {
            "name": "code",
            "jsonPath": "sourceSegregations[0].collectionDetails[0].collectionType.code",
            "label": "swm.create.collectionType",
            "pattern": "",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "maxLength": 128,
            "minLength": 1,
            "patternErrorMsg": "",
	    "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=CollectionType|$..code|$..name"
          },
          {
            "name": "wetWasteCollected",
            "jsonPath": "sourceSegregations[0].collectionDetails[0].wetWasteCollected",
            "label": "swm.create.collectionDetails.wetWasteCollected",
            "pattern": "",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "patternErrorMsg": ""
          },
          {
            "name": "dryWasteCollected",
            "jsonPath": "sourceSegregations[0].collectionDetails[0].dryWasteCollected",
            "label": "swm.create.collectionDetails.dryWasteCollected",
            "pattern": "",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "patternErrorMsg": ""
          }
        ]
      }
    ],
    "url": "/swm-services/sourcesegregations/_create",
    "tenantIdRequired": true
  },
  "swm.view": {
    "numCols": 4,
    "useTimestamp": true,
    "objectName": "sourceSegregations",
    "groups": [
      {
        "name": "SourceSegregationDetails",
        "label": "swm.create.group.title.SourceSegregationDetails",
        "fields": [
          {
            "name": "code",
            "jsonPath": "sourceSegregations[0].dumpingGround.code",
            "label": "swm.create.dumpingGround",
            "pattern": "",
            "type": "text",
            "isRequired": false,
            "isDisabled": false,
            "defaultValue": "",
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "sourceSegregationDate",
            "jsonPath": "sourceSegregations[0].sourceSegregationDate",
            "label": "swm.create.sourceSegregationDate",
            "pattern": "",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "patternErrorMsg": ""
          }
        ]
      },
      {
        "name": "CollectionTypeDetails",
        "label": "swm.create.group.title.CollectionTypeDetails",
	"jsonPath": "sourceSegregations[0].collectionDetails[0]",
	"multiple":true,
        "fields": [
          {
            "name": "code",
            "jsonPath": "sourceSegregations[0].collectionDetails[0].collectionType.code",
            "label": "swm.create.collectionType",
            "pattern": "",
            "type": "text",
            "isRequired": false,
            "isDisabled": false,
            "defaultValue": "",
            "maxLength": 128,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "wetWasteCollected",
            "jsonPath": "sourceSegregations[0].collectionDetails[0].wetWasteCollected",
            "label": "swm.create.collectionDetails.wetWasteCollected",
            "pattern": "",
            "type": "number",
            "isRequired": false,
            "isDisabled": false,
            "defaultValue": "",
            "patternErrorMsg": ""
          },
          {
            "name": "dryWasteCollected",
            "jsonPath": "sourceSegregations[0].collectionDetails[0].dryWasteCollected",
            "label": "swm.create.collectionDetails.dryWasteCollected",
            "pattern": "",
            "type": "number",
            "isRequired": false,
            "isDisabled": false,
            "defaultValue": "",
            "patternErrorMsg": ""
          }
        ]
      }
    ],
    "tenantIdRequired": true,
    "url": "/swm-services/sourcesegregations/_search?code={code}"
  },
  "swm.update": {
    "numCols": 4,
    "useTimestamp": true,
    "objectName": "sourceSegregations",
    "idJsonPath": "sourceSegregations[0].code",
    "groups": [
      {
        "name": "SourceSegregationDetails",
        "label": "swm.create.group.title.SourceSegregationDetails",
        "fields": [
          {
            "name": "code",
            "jsonPath": "sourceSegregations[0].dumpingGround.code",
            "label": "swm.create.dumpingGround",
            "pattern": "",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": "",
	    "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=DumpingGround|$..code|$..name"
          },
          {
            "name": "sourceSegregationDate",
            "jsonPath": "sourceSegregations[0].sourceSegregationDate",
            "label": "swm.create.sourceSegregationDate",
            "pattern": "",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "patternErrorMsg": ""
          }
        ]
      },
      {
        "name": "CollectionTypeDetails",
        "label": "swm.create.group.title.CollectionTypeDetails",
	"jsonPath": "sourceSegregations[0].collectionDetails[0]",
	"multiple":true,
        "fields": [
          {
            "name": "code",
            "jsonPath": "sourceSegregations[0].collectionDetails[0].collectionType.code",
            "label": "swm.create.collectionType",
            "pattern": "",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "maxLength": 128,
            "minLength": 1,
            "patternErrorMsg": "",
	    "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=CollectionType|$..code|$..name"
          },
          {
            "name": "wetWasteCollected",
            "jsonPath": "sourceSegregations[0].collectionDetails[0].wetWasteCollected",
            "label": "swm.create.collectionDetails.wetWasteCollected",
            "pattern": "",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "patternErrorMsg": ""
          },
          {
            "name": "dryWasteCollected",
            "jsonPath": "sourceSegregations[0].collectionDetails[0].dryWasteCollected",
            "label": "swm.create.collectionDetails.dryWasteCollected",
            "pattern": "",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "defaultValue": "",
            "patternErrorMsg": ""
          }
        ]
      }
    ],
    "url": "/swm-services/sourcesegregations/_update",
    "tenantIdRequired": true,
    "searchUrl": "/swm-services/sourcesegregations/_search?code={code}"
  }
}
 export default dat;