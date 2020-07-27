# ods-transfer-client


{
	"info": {
		"_postman_id": "4efa95d6-6dfc-4bd1-a62c-93ab5336d058",
		"name": "ODS Connector",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "List",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": ""
				},
				"url": {
					"raw": "localhost:8080/file/show",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"file",
						"show"
					]
				}
			},
			"response": []
		},
		{
			"name": "Create Files/Folder",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": ""
				},
				"url": {
					"raw": "localhost:8080/file/create/folder",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"file",
						"create",
						"folder"
					]
				},
				"description": "localhost:8080/file/create/folder"
			},
			"response": []
		},
		{
			"name": "/file/copy",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": ""
				},
				"url": {
					"raw": "localhost:8080/file/copy",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"file",
						"copy"
					]
				}
			},
			"response": []
		},
		{
			"name": "delete",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": ""
				},
				"url": {
					"raw": "localhost:8080/file/delete",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"file",
						"delete"
					]
				}
			},
			"response": []
		}
	],
	"protocolProfileBehavior": {}
}
