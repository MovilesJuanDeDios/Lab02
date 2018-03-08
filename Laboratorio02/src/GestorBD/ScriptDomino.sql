 
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

66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666

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
-- ----------------- INSERTAR PRODUCTO ----------------- 
CREATE OR REPLACE PROCEDURE insertarProducto(codigo IN Producto.codigo%TYPE, nombreProducto IN Producto.nombreProducto%TYPE, precio IN Producto.precio%TYPE, importado IN Producto.importado%TYPE, nombreTipo IN Producto.nombreTipo%TYPE)
AS
BEGIN
	INSERT INTO Producto VALUES(codigo,nombreProducto,precio,importado,nombreTipo);
END;
/

-- ----------------- ACTUALIZAR PRODUCTO ----------------- 
CREATE OR REPLACE PROCEDURE actualizarProducto(nombreProductoin IN Producto.nombreProducto%TYPE, precioin IN Producto.precio%TYPE,importadoin IN Producto.importado%TYPE)
AS
BEGIN
	UPDATE Producto SET precio=precioin,importado=importadoin WHERE nombreProducto=nombreProductoin;
END;
/

-- ----------------- BUSCAR PRODUCTO ----------------- 
CREATE OR REPLACE FUNCTION buscarProducto(nombreProductoin IN Producto.nombreProducto%TYPE)
RETURN Types.ref_cursor 
AS 
        producto_cursor types.ref_cursor; 
BEGIN 
  OPEN producto_cursor FOR 
       SELECT * FROM Producto WHERE nombreProducto=nombreProductoin; 
RETURN producto_cursor; 
END;
/

-- ----------------- LISTAR PRODUCTO ----------------- 
CREATE OR REPLACE FUNCTION listarProducto
RETURN Types.ref_cursor 
AS 
        producto_cursor types.ref_cursor; 
BEGIN 
  OPEN producto_cursor FOR 
       SELECT * FROM Producto ; 
RETURN producto_cursor; 
END;
/

-- ----------------- ELIMINAR PRODUCTO ----------------- 
create or replace procedure eliminarProducto(nombreProductoin IN Producto.nombreProducto%TYPE)
as
begin
    delete from Producto where nombreProducto=nombreProductoin;
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
DROP PROCEDURE insertarProducto;
DROP PROCEDURE actualizarProducto;
DROP FUNCTION buscarProducto;
DROP FUNCTION listarProducto;
DROP PROCEDURE eliminarProducto;
DROP TABLE Producto;
DROP PACKAGE types;
DROP USER servidor CASCADE; 


-- #############################################################################














