MIGRATION_LABEL = "to-be-changed"
DATE_WITH_TIME := $(shell /bin/date "+%Y%m%d%H%M%S")

makeMigration:
	@echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" >> src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.xml
	@echo "<databaseChangeLog" >> src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.xml
	@echo "		xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"" >> src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.xml
	@echo "		xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" >> src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.xml
	@echo "		xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog" >> src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.xml
	@echo "			http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd\">" >> src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.xml
	@echo "</databaseChangeLog>" >> src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.xml
	mvn liquibase:diff -DdiffChangeLogFile=src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.xml