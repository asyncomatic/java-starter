install:
	@docker build -t queso-test-executor ./

start:
	@echo "Starting test-executor ..."
	@docker-compose up -d test-executor

stop:
	@docker kill devcloud_test_executor && docker rm devcloud_test_executor

restart: stop start

log:
	@docker-compose logs --tail=5 --follow

status:
	@docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
