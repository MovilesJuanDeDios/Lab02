 
connect system/manager as sysdba;
 
-- ----------------- USUARIO ----------------- 
create user servidor identified by servidor;

grant all privileges to servidor identified by servidor;

connect servidor/servidor;

-- ----------------- CREACION DE UN CURSOR -----------------
CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

-- ################################### JUGADOR ###################################
-- ----------------- TABLA DE JUGADOR -----------------
create table Jugador(
	nickname VARCHAR(9), 
	puntos NUMBER,
	CONSTRAINTS pkJugador PRIMARY KEY (nickname)
);

-- ----------------- INSERTAR JUGADOR ----------------- 
CREATE OR REPLACE PROCEDURE insertarJugador(nickname IN Jugador.nickname%TYPE, puntos IN Jugador.puntos%TYPE)
AS
BEGIN
	INSERT INTO Jugador VALUES(codigo,nickname,puntos,importado,nombreTipo);
END;
/

-- ----------------- ACTUALIZAR JUGADOR ----------------- 
CREATE OR REPLACE PROCEDURE actualizarJugador(nicknamein IN Jugador.nickname%TYPE, puntosin IN Jugador.puntos%TYPE)
AS
BEGIN
	UPDATE Jugador SET puntos=puntosin,importado=importadoin WHERE nickname=nicknamein;
END;
/

-- ----------------- BUSCAR JUGADOR ----------------- 
CREATE OR REPLACE FUNCTION buscarJugador(nicknamein IN Jugador.nickname%TYPE)
RETURN Types.ref_cursor 
AS 
        Jugador_cursor types.ref_cursor; 
BEGIN 
  OPEN Jugador_cursor FOR 
       SELECT * FROM Jugador WHERE nickname=nicknamein; 
RETURN Jugador_cursor; 
END;
/

-- ----------------- LISTAR JUGADOR ----------------- 
CREATE OR REPLACE FUNCTION listarJugador
RETURN Types.ref_cursor 
AS 
        Jugador_cursor types.ref_cursor; 
BEGIN 
  OPEN Jugador_cursor FOR 
       SELECT * FROM Jugador ; 
RETURN Jugador_cursor; 
END;
/

-- ----------------- ELIMINAR JUGADOR ----------------- 
create or replace procedure eliminarJugador(nicknamein IN Jugador.nickname%TYPE)
as
begin
    delete from Jugador where nickname=nicknamein;
end;
/

-- ################################### JUEGO ###################################
-- ----------------- TABLA DE JUEGO -----------------
create table Juego(
	codJuego VARCHAR(9),
	CONSTRAINTS pkJuego PRIMARY KEY (codJuego)
);
-- ----------------- INSERTAR JUEGO ----------------- 
CREATE OR REPLACE PROCEDURE insertarJuego(codJuego IN Juego.codJuego%TYPE)
AS
BEGIN
	INSERT INTO Juego VALUES(codJuego);
END;
/

-- ----------------- ACTUALIZAR JUEGO ----------------- 
CREATE OR REPLACE PROCEDURE actualizarJuego(codJuegoin IN Juego.codJuego%TYPE)
AS
BEGIN
	UPDATE Juego SET codJuego=codJuegoin;
END;
/

-- ----------------- BUSCAR JUEGO ----------------- 
CREATE OR REPLACE FUNCTION buscarJuego(codJuegoin IN Juego.codJuego%TYPE)
RETURN Types.ref_cursor 
AS 
        juego_cursor types.ref_cursor; 
BEGIN 
  OPEN juego_cursor FOR 
       SELECT * FROM Juego WHERE codJuego=codJuegoin; 
RETURN juego_cursor; 
END;
/

-- ----------------- LISTAR JUEGO ----------------- 
CREATE OR REPLACE FUNCTION listarJuego
RETURN Types.ref_cursor 
AS 
        juego_cursor types.ref_cursor; 
BEGIN 
  OPEN juego_cursor FOR 
       SELECT * FROM Juego; 
RETURN juego_cursor; 
END;
/

-- ----------------- ELIMINAR JUEGO ----------------- 
create or replace procedure eliminarJuego(codJuegoin IN Juego.codJuego%TYPE)
as
begin
    delete from Juego where codJuego=codJuegoin;
end;
/

-- ################################### FICHA ###################################
-- ----------------- TABLA DE FICHA -----------------
create table Ficha(
	total NUMBER, 
	valorDer NUMBER, 
	valorIzq NUMBER,
	CONSTRAINTS pkFicha PRIMARY KEY (total)
);
-- ----------------- INSERTAR FICHA ----------------- 
CREATE OR REPLACE PROCEDURE insertarFicha(total IN Ficha.total%TYPE, valorDer IN Ficha.valorDer%TYPE, valorIzq IN Ficha.valorIzq%TYPE)
AS
BEGIN
	INSERT INTO Ficha VALUES(codigo,total,valorDer,valorIzq,nombreTipo);
END;
/

-- ----------------- ACTUALIZAR FICHA ----------------- 
CREATE OR REPLACE PROCEDURE actualizarFicha(totalin IN Ficha.total%TYPE, valorDerin IN Ficha.valorDer%TYPE,valorIzqin IN Ficha.valorIzq%TYPE)
AS
BEGIN
	UPDATE Ficha SET valorDer=valorDerin,valorIzq=valorIzqin WHERE total=totalin;
END;
/

-- ----------------- BUSCAR FICHA ----------------- 
CREATE OR REPLACE FUNCTION buscarFicha(totalin IN Ficha.total%TYPE)
RETURN Types.ref_cursor 
AS 
        Ficha_cursor types.ref_cursor; 
BEGIN 
  OPEN Ficha_cursor FOR 
       SELECT * FROM Ficha WHERE total=totalin; 
RETURN Ficha_cursor; 
END;
/

-- ----------------- LISTAR FICHA ----------------- 
CREATE OR REPLACE FUNCTION listarFicha
RETURN Types.ref_cursor 
AS 
        Ficha_cursor types.ref_cursor; 
BEGIN 
  OPEN Ficha_cursor FOR 
       SELECT * FROM Ficha ; 
RETURN Ficha_cursor; 
END;
/

-- ----------------- ELIMINAR FICHA ----------------- 
create or replace procedure eliminarFicha(totalin IN Ficha.total%TYPE)
as
begin
    delete from Ficha where total=totalin;
end;
/

-- ################################### JUGADOR-JUEGO ###################################
-- ----------------- TABLA DE JUGADOR-JUEGO -----------------
create table JugadorJuego(
	nickname VARCHAR(9), 
	codJuego VARCHAR(9),
	CONSTRAINTS pkJugadorJuego PRIMARY KEY (codigo)
);
-- ################################### FICHA-JUEGO ###################################
-- ----------------- TABLA DE FICHA-JUEGO -----------------
create table FichaJuego(
	total NUMBER, 
	codJuego VARCHAR(9),
	CONSTRAINTS pkFichaJuego PRIMARY KEY (codigo)
);
-- ################################### JUGADOR-FICHA ###################################
-- ----------------- TABLA DE JUGADOR-FICHA -----------------
create table JugadorFicha(
	nickname VARCHAR(9), 
	total NUMBER,
	CONSTRAINTS pkJugadorFicha PRIMARY KEY (codigo)
);



-- #############################################################################

-- PRUEBAS NO EJECUTAR

-- -----------------  DROPS -----------------
DROP PROCEDURE ;
DROP PROCEDURE ;
DROP FUNCTION ;
DROP FUNCTION ;
DROP PROCEDURE ;
DROP TABLE ;
DROP PACKAGE types;
DROP USER servidor CASCADE; 


-- #############################################################################














