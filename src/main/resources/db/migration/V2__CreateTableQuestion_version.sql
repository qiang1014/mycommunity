
use community;


CREATE TABLE question(
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100),
  description TEXT,
  gmt_create BIGINT,
  gmt_modified BIGINT,
  creator INT,
  comment_count INT DEFAULT 0,
  view_count INT DEFAULT 0,
  like_count INT DEFAULT 0,
  tag VARCHAR(256)
)

