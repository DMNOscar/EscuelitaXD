DROP DATABASE  IF EXISTS  escuela;

CREATE DATABASE IF NOT EXISTS escuela;

USE escuela;

CREATE TABLE categoria(
	
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre  VARCHAR (100) UNIQUE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE

);

INSERT INTO categoria(nombre) VALUES ('Alumno');
INSERT INTO categoria(nombre) VALUES ('Profesor');


CREATE TABLE usuario(

	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre  VARCHAR (100) UNIQUE NOT NULL,
    nick VARCHAR (20) UNICODE NOT NULL,
    pass VARCHAR (100) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    categoria_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);


INSERT INTO usuario(nombre, nick, pass, categoria_id) VALUES
('Fabi','fabi','MSgB9ssRj5k6rd/EqTDK7wxGRzwUhKFo2/+ZfKxU42A=',1);
INSERT INTO usuario(nombre, nick, pass, categoria_id) VALUES
('Adi','adi','C4PymHLEe/hUP4ZFZBRPb+M7P6rx11FgJi14sAQOFRs=',2);

INSERT INTO usuario(nombre, nick, pass, categoria_id) VALUES
('Oscar','oscar','EZzMGTaNSiipIYK/Uu4OpM3yikS1naF2xPFqTTvzwrY=',1);

INSERT INTO usuario(nombre, nick, pass, categoria_id) VALUES
('Mateo','mateo','jJt8mPvc31Q9ObObxJCY3MWU9r4tKyNvg5akVeuLOtc=',1);

INSERT INTO usuario(nombre, nick, pass, categoria_id) VALUES
('Javi','javi','6/Bjn9sziMxlKxkuVDb4yhr8nJkEl/S32uN7sIWpzkQ=',2);


CREATE TABLE  materia (
 id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
 clave VARCHAR (20) UNIQUE NOT NULL,
 nombre  VARCHAR (500) UNIQUE NOT NULL,
 activo BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO materia(nombre, clave) VALUES ('ADR01','ANDORID');
INSERT INTO materia(nombre, clave) VALUES ('JVA02','JAVA'); 
INSERT INTO materia(nombre, clave) VALUES ('C++03','C++');
INSERT INTO materia(nombre, clave) VALUES ('PHP04','PHP');

CREATE TABLE evaluacion(
	
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    calificacion DOUBLE NOT NULL CHECK(calificacion > 0 AND calificacion < 10),
    fecha DATETIME NOT NULL DEFAULT now(),
    usuario_id INT UNSIGNED NOT NULL,
    materia_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (materia_id) REFERENCES materia(id)
    
);


INSERT INTO evaluacion (calificacion, usuario_id, materia_id)
VALUES (8,1,2);

INSERT INTO evaluacion (calificacion, usuario_id, materia_id)
VALUES (10,3,1);

INSERT INTO evaluacion (calificacion, usuario_id, materia_id)
VALUES (7,4,4);

INSERT INTO evaluacion (calificacion, usuario_id, materia_id)
VALUES (7,5,1);

INSERT INTO evaluacion (calificacion, usuario_id, materia_id)
VALUES (9,3,3);

INSERT INTO evaluacion (calificacion, usuario_id, materia_id)
VALUES (8,3,2);
