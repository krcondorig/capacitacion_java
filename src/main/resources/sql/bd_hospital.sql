USE [master]
GO
/****** Object:  Database [Hospital]    Script Date: 13/09/2024 22:14:41 ******/
CREATE DATABASE [Hospital]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Hospital', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\Hospital.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Hospital_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\Hospital_log.ldf' , SIZE = 1072KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Hospital] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Hospital].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Hospital] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Hospital] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Hospital] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Hospital] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Hospital] SET ARITHABORT OFF 
GO
ALTER DATABASE [Hospital] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Hospital] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Hospital] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Hospital] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Hospital] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Hospital] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Hospital] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Hospital] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Hospital] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Hospital] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Hospital] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Hospital] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Hospital] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Hospital] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Hospital] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Hospital] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Hospital] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Hospital] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Hospital] SET  MULTI_USER 
GO
ALTER DATABASE [Hospital] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Hospital] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Hospital] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Hospital] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Hospital] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Hospital]
GO
/****** Object:  Table [dbo].[historial_medico]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[historial_medico](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[paciente_id] [int] NULL,
	[fecha] [varchar](25) NULL,
	[descripcion] [varchar](100) NULL,
	[diagnostico] [varchar](100) NULL,
	[tratamiento] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[medicos]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[medicos](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NULL,
	[apellido] [varchar](50) NULL,
	[especialidad] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[pacientes]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[pacientes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](30) NULL,
	[apellido] [varchar](30) NULL,
	[telefono] [varchar](20) NULL,
	[genero] [char](1) NULL,
	[estaEliminado] [bit] NOT NULL DEFAULT ((0)),
 CONSTRAINT [PK_pacientes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[pacientes_medicos]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pacientes_medicos](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[paciente_id] [int] NULL,
	[medico_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[historial_medico] ON 

INSERT [dbo].[historial_medico] ([id], [paciente_id], [fecha], [descripcion], [diagnostico], [tratamiento]) VALUES (1, 1, N'2022-01-01', N'Consulta inicial', N'Gripe', N'Medicación')
INSERT [dbo].[historial_medico] ([id], [paciente_id], [fecha], [descripcion], [diagnostico], [tratamiento]) VALUES (8, 3, N'2022-01-01', N'Consulta inicial', N'Gripe', N'Medicación')
INSERT [dbo].[historial_medico] ([id], [paciente_id], [fecha], [descripcion], [diagnostico], [tratamiento]) VALUES (9, 7, N'2022-01-15', N'Seguimiento', N'Mejora', N'Reposo')
INSERT [dbo].[historial_medico] ([id], [paciente_id], [fecha], [descripcion], [diagnostico], [tratamiento]) VALUES (10, 11, N'2022-02-01', N'Consulta inicial', N'Fiebre', N'Antibióticos')
SET IDENTITY_INSERT [dbo].[historial_medico] OFF
SET IDENTITY_INSERT [dbo].[medicos] ON 

INSERT [dbo].[medicos] ([id], [nombre], [apellido], [especialidad]) VALUES (1, N'Juan', N'Pérez', N'Cardiología')
INSERT [dbo].[medicos] ([id], [nombre], [apellido], [especialidad]) VALUES (2, N'Ana', N'García', N'Pediatría')
INSERT [dbo].[medicos] ([id], [nombre], [apellido], [especialidad]) VALUES (3, N'Luis', N'Rodríguez', N'Oncología')
SET IDENTITY_INSERT [dbo].[medicos] OFF
SET IDENTITY_INSERT [dbo].[pacientes] ON 

INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (1, N'carlos', N'contreras', N'11111111', N'M', 0)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (3, N'jose', N'gonzales', N'44444444', N'M', 0)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (7, N'jeferson', N'farman', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (9, N'jeferson2', N'farman', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (10, N'jeferson3', N'farman', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (11, N'paolo', N'guerrero', N'343434343', N'M', 0)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (12, N'claudio', N'pizarro', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (13, N'jose', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (14, N'jose', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (15, N'jose3', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (16, N'jose33', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (17, N'juan33', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (18, N'juan33', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (19, N'juan33', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (20, N'juan33', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (21, N'juan33', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (22, N'juan33', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (23, N'juan33', N'galvez', N'343434343', N'M', 1)
INSERT [dbo].[pacientes] ([id], [nombre], [apellido], [telefono], [genero], [estaEliminado]) VALUES (24, N'renzo', N'guerra', N'935321847', N'M', 1)
SET IDENTITY_INSERT [dbo].[pacientes] OFF
SET IDENTITY_INSERT [dbo].[pacientes_medicos] ON 

INSERT [dbo].[pacientes_medicos] ([id], [paciente_id], [medico_id]) VALUES (1, 1, 1)
INSERT [dbo].[pacientes_medicos] ([id], [paciente_id], [medico_id]) VALUES (2, 3, 2)
INSERT [dbo].[pacientes_medicos] ([id], [paciente_id], [medico_id]) VALUES (3, 3, 3)
INSERT [dbo].[pacientes_medicos] ([id], [paciente_id], [medico_id]) VALUES (4, 3, 2)
INSERT [dbo].[pacientes_medicos] ([id], [paciente_id], [medico_id]) VALUES (5, 3, 3)
INSERT [dbo].[pacientes_medicos] ([id], [paciente_id], [medico_id]) VALUES (6, 11, 2)
INSERT [dbo].[pacientes_medicos] ([id], [paciente_id], [medico_id]) VALUES (7, 7, 2)
SET IDENTITY_INSERT [dbo].[pacientes_medicos] OFF
ALTER TABLE [dbo].[historial_medico]  WITH CHECK ADD FOREIGN KEY([paciente_id])
REFERENCES [dbo].[pacientes] ([id])
GO
ALTER TABLE [dbo].[pacientes_medicos]  WITH CHECK ADD FOREIGN KEY([medico_id])
REFERENCES [dbo].[medicos] ([id])
GO
ALTER TABLE [dbo].[pacientes_medicos]  WITH CHECK ADD FOREIGN KEY([paciente_id])
REFERENCES [dbo].[pacientes] ([id])
GO
/****** Object:  StoredProcedure [dbo].[SP_ACTUALIZAR_PACIENTE]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_ACTUALIZAR_PACIENTE]
    @id INT,
    @nombre VARCHAR(30),
    @apellido VARCHAR(30),
    @telefono VARCHAR(20),
    @genero CHAR(1)
AS
BEGIN
    UPDATE Pacientes SET nombre = @nombre, apellido = @apellido, telefono = @telefono, genero = @genero
    WHERE id = @id;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_BUSCAR_PACIENTE_POR_ID]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_BUSCAR_PACIENTE_POR_ID]
    @id INT
AS
BEGIN
    SELECT * FROM Pacientes 
			 WHERE id = @id AND estaEliminado = 0;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_CREAR_PACIENTE]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_CREAR_PACIENTE]
    @nombre VARCHAR(30),
    @apellido VARCHAR(30),
    @telefono VARCHAR(20),
    @genero CHAR(1)
AS
BEGIN
    INSERT INTO Pacientes ( nombre, 
							apellido, 
							telefono, 
							genero)
					VALUES (@nombre, 
							@apellido, 
							@telefono, 
							@genero)
END

GO
/****** Object:  StoredProcedure [dbo].[SP_ELIMINAR_PACIENTE]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_ELIMINAR_PACIENTE]
    @id INT
AS
BEGIN
    UPDATE Pacientes
    SET estaEliminado = 1
    WHERE id = @id;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_LISTAR_PACIENTES]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_LISTAR_PACIENTES]
AS
BEGIN
    SELECT * FROM Pacientes
	WHERE estaEliminado = 0;
END

GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_PACIENTES_CON_HISTORIAL_MEDICO]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_PACIENTES_CON_HISTORIAL_MEDICO]
AS
BEGIN
    SELECT p.id, p.nombre, p.apellido, hm.fecha, hm.descripcion, hm.diagnostico, hm.tratamiento
    FROM pacientes p
    INNER JOIN historial_medico hm ON p.id = hm.paciente_id
    ORDER BY p.id;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_OBTENER_PACIENTES_CON_MEDICOS]    Script Date: 13/09/2024 22:14:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_OBTENER_PACIENTES_CON_MEDICOS]
AS
BEGIN
    SELECT p.id, p.nombre, p.apellido, m.id, m.nombre, m.apellido
    FROM pacientes p
    INNER JOIN pacientes_medicos pm ON p.id = pm.paciente_id
    INNER JOIN medicos m ON pm.medico_id = m.id
    ORDER BY p.apellido, p.nombre;
END

GO
USE [master]
GO
ALTER DATABASE [Hospital] SET  READ_WRITE 
GO
