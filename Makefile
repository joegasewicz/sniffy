create-volumes:
	docker volume create --name=sniffydb_vol

remove-volumes:
	docker rm sniffydb_vol

docker-compose:
	docker compose -f docker-compose.yaml up