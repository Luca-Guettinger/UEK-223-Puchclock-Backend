INSERT INTO CATEGORY (name) VALUES ('Support');
INSERT INTO CATEGORY (name) VALUES ('Entwicklung');
INSERT INTO CATEGORY (name) VALUES ('Administration');

INSERT INTO APPLICATION_USER (username, password) VALUES ('mhe', '$2a$10$jtDlWv9JR3AxXUUsH4Lvqu.eKkYavTowfjFeVJSyYtVdcRCR1Uci2');
INSERT INTO APPLICATION_USER (username, password) VALUES ('lgu', '$2a$10$jtDlWv9JR3AxXUUsH4Lvqu.eKkYavTowfjFeVJSyYtVdcRCR1Uci2');

INSERT INTO LOCATION (street_name, street_number, city, postal_code) VALUES ('Hauptstrasse', 1, 'Zürich', 8000);
INSERT INTO LOCATION (street_name, street_number, city, postal_code) VALUES ('Hauptstrasse', 1, 'Bern', 3005);

INSERT INTO ENTRY (USER_ID, CATEGORY_ID, LOCATION_ID, CHECK_IN, CHECK_OUT) VALUES (1, 1, 1,  '2011-03-12',  '2011-03-13');
INSERT INTO ENTRY (USER_ID, CATEGORY_ID, LOCATION_ID, CHECK_IN, CHECK_OUT) VALUES (2, 2, 2,  '2011-04-12',  '2011-04-13');
INSERT INTO ENTRY (USER_ID, CATEGORY_ID, LOCATION_ID, CHECK_IN, CHECK_OUT) VALUES (1, 2, 1,  '2011-03-12',  '2011-03-13');



