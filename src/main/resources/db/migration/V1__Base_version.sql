
use community;


CREATE TABLE USER(
  id INT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(30),
  token VARCHAR(100),
  account_id VARCHAR(50),
  gmt_create BIGINT,
  gmt_modified BIGINT
)
