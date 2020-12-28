CREATE TABLE takenote.users (
  user_uuid uuid NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (user_uuid));

CREATE TABLE takenote.shelf (
  shelf_id INT NOT NULL,
  shelf_name VARCHAR(45) NOT NULL,
  is_deleted BOOLEAN NULL,
  user_uuid uuid NOT NULL,
  created_on TIMESTAMP NOT NULL,
  upadted_on TIMESTAMP NOT NULL,
  PRIMARY KEY (shelf_id),
  CONSTRAINT user_shelf_uuid
    FOREIGN KEY (user_uuid)
    REFERENCES takenote.users (user_uuid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE takenote.lables (
  label_id INT NOT NULL,
  label_name VARCHAR(45) NOT NULL,
  user_uuid uuid NOT NULL,
  PRIMARY KEY (label_id),
  CONSTRAINT user_label_uuid
    FOREIGN KEY (user_uuid)
    REFERENCES takenote.users (user_uuid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE takenote.notebook (
  notebook_id INT NOT NULL,
  notebook_name VARCHAR(100) NOT NULL,
  shelf_id INT NULL,
  user_uuid uuid NOT NULL,
  is_deleted BOOLEAN,
  created_on TIMESTAMP NOT NULL,
  updated_on TIMESTAMP NOT NULL,
  PRIMARY KEY (notebook_id),
   CONSTRAINT user_notebook_uuid
    FOREIGN KEY (user_uuid)
    REFERENCES takenote.users (user_uuid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT shelf_notebook
    FOREIGN KEY (shelf_id)
    REFERENCES takenote.shelf (shelf_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE takenote.note (
  note_id INT NOT NULL,
  note_name VARCHAR(100) NOT NULL,
  text VARCHAR(5000) NULL,
  notebook_id INT NULL,
  user_uuid uuid NOT NULL,
created_on TIMESTAMP NOT NULL,
updated_on TIMESTAMP NOT NULL,
is_deleted BOOLEAN,
  CONSTRAINT note_notebook
    FOREIGN KEY (notebook_id)
    REFERENCES takenote.notebook (notebook_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT note_user_uuid
    FOREIGN KEY (user_uuid)
    REFERENCES takenote.users (user_uuid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE takenote.tags (
  tag_id INT NOT NULL,
  tag_name VARCHAR(100) NOT NULL,
  user_uuid uuid NOT NULL,
  PRIMARY KEY (tag_id),
  CONSTRAINT tag_user_uuid
    FOREIGN KEY (user_uuid)
    REFERENCES takenote.users (user_uuid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE takenote.tag_note (
  tag_id INT NOT NULL,
  note_id INT NOT NULL,
  user_uuid uuid NOT NULL,
  CONSTRAINT tag_note_user_uuid
    FOREIGN KEY (user_uuid)
    REFERENCES takenote.users (user_uuid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);




