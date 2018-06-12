-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 10-Jun-2018 às 12:40
-- Versão do servidor: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `management-sys`
--
--
-- Extraindo dados da tabela `State`
--

INSERT INTO `State` (`nameState`) VALUES
('Acre'),
('Alagoas'),
('Amapá'),
('Amazonas'),
('Bahia'),
('Ceará'),
('Distrito Federal'),
('Espírito Santo'),
('Goiás'),
('Maranhão'),
('Mato Grosso'),
('Mato Grosso do Sul'),
('Minas Gerais'),
('Pará'),
('Paraíba'),
('Paraná'),
('Pernambuco'),
('Piauí'),
('Rio de Janeiro'),
('Rio Grande do Norte'),
('Rio Grande do Sul'),
('Rondônia'),
('Roraima'),
('Santa Catarina'),
('São Paulo'),
('Sergipe'),
('Tocantins');

--
-- Extraindo dados da tabela `City`
--

INSERT INTO `City` (`nameCity`, `State_nameState`) VALUES
('Abadia de Goiás', 'Goiás'),
('Abadia dos Dourados', 'Minas Gerais'),
('Abadiânia', 'Goiás'),
('Abaeté', 'Minas Gerais'),
('Abaetetuba', 'Pará'),
('Abaiara', 'Ceará'),
('Abaíra', 'Bahia'),
('Abaré', 'Bahia'),
('Abatiá', 'Paraná'),
('Abdon Batista', 'Santa Catarina'),
('Abel Figueiredo', 'Pará'),
('Abelardo Luz', 'Santa Catarina'),
('Abre Campo', 'Minas Gerais'),
('Abreu e Lima', 'Pernambuco'),
('Abreulândia', 'Tocantins'),
('Acaiaca', 'Minas Gerais'),
('Açailândia', 'Maranhão'),
('Acajutiba', 'Bahia'),
('Acará', 'Pará'),
('Acarape', 'Ceará'),
('Acaraú', 'Ceará'),
('Acari', 'Rio Grande do Norte'),
('Acauã', 'Piauí'),
('Aceguá', 'Rio Grande do Sul'),
('Acopiara', 'Ceará'),
('Acorizal', 'Mato Grosso'),
('Acrelândia', 'Acre');

--
-- Extraindo dados da tabela `Brand`
--

INSERT INTO `Brand` (`idBrand`, `nameBrand`, `isActiveBrand`) VALUES
(4, 'AMD', 1),
(5, 'NVIDIA', 1),
(6, 'Intel', 1);

--
-- Extraindo dados da tabela `ServiceStatus`
--

INSERT INTO `ServiceStatus` (`idServiceStatus`, `nameServiceStatus`) VALUES
(1, 'ON_ESTIMATE'),
(2, 'WAITING_FOR_APPROVAL'),
(3, 'WAITING_PAYMENT'),
(4, 'WAITING_FOR_WITHDRAWAL'),
(5, 'REFUSED'),
(6, 'RUNNING'),
(7, 'DONE');

--
-- Extraindo dados da tabela `EmployeeType`
--

INSERT INTO `EmployeeType` (`idEmployeeType`, `nameEmployeeType`) VALUES
(1, 'MANAGER'),
(2, 'COMMON');

--
-- Extraindo dados da tabela `Product`
--

INSERT INTO `Product` (`idProduct`, `barCodeProduct`, `nameProduct`, `priceProduct`, `quantityProduct`, `Brand_nameBrand`, `isActiveProduct`) VALUES
(1, '00', 'Produto 01', 1, 0, 'AMD', 1),
(2, '01', 'Produto 01', 2, 0, 'NVIDIA', 1),
(3, '02', 'Produto 02', 3, 0, 'Intel', 1);

--
-- Extraindo dados da tabela `Person`
--

INSERT INTO `Person` (`idPerson`, `idDocumentPerson`, `namePerson`, `tel1Person`, `tel2Person`, `isActivePerson`) VALUES
(1, '000.000.000-00', 'Cliente 01', '9999999999', '', 1),
(2, '000.000.000-01', 'Cliente 02', '9999999999', '', 1),
(3, '000.000.000-02', 'Cliente 03', '9999999999', '', 1),
(4, '0000-00', 'Fornecedor 01', '9999999999', '', 1),
(5, '0000-01', 'Fornecedor 02', '7788878887', '', 1),
(6, '0000-02', 'Fornecedor 02', '9987875252', '', 1),
(8, '0000-03', 'Cliente 04', '9987874152', '', 1),
(10, '0000-04', 'Cliente 05', '7896854152', '', 1),
(12, '0000-05', 'Cliente 06', '7485964152', '', 1),
(13, '000.000.000-03', 'Funcionário 01', '9685744152', '', 1),
(14, '000.000.000-04', 'Funcionário 02', '9674854152', '', 1),
(15, '000.000.000-05', 'Funcionário 03', '1245789663', '', 1);

--
-- Extraindo dados da tabela `Address`
--

INSERT INTO `Address` (`Person_idPerson`, `streetAddress`, `numberAddress`, `districtAddress`, `City_nameCity`, `City_State_nameState`) VALUES
('000.000.000-00', 'rua', 1, 'bairro', 'Acrelândia', 'Acre'),
('000.000.000-01', 'rua', 1, 'bairro', 'Acrelândia', 'Acre'),
('000.000.000-02', 'rua', 1, 'bairro', 'Acrelândia', 'Acre'),
('000.000.000-03', 'rua', 1, 'bairro', 'Acrelândia', 'Acre'),
('000.000.000-04', 'rua', 123, 'bairro', 'Acrelândia', 'Acre'),
('000.000.000-05', 'rua', 123, 'bairro', 'Acrelândia', 'Acre'),
('0000-00', 'rua', 1, 'bairro', 'Acrelândia', 'Acre'),
('0000-01', 'rua', 123, 'bairro', 'Acrelândia', 'Acre'),
('0000-02', 'rua', 789, 'bairro', 'Acrelândia', 'Acre'),
('0000-03', 'rua', 1, 'bairro', 'Acrelândia', 'Acre'),
('0000-04', 'rua', 5, 'bairro', 'Acrelândia', 'Acre'),
('0000-05', 'rua', 1, 'bairro', 'Acrelândia', 'Acre');


--
-- Extraindo dados da tabela `LegalPerson`
--

INSERT INTO `LegalPerson` (`rgLegalPerson`, `Person_idPerson`) VALUES
('00.000.000-0', '000.000.000-00'),
('00.000.000-0', '000.000.000-01'),
('00.000.000-0', '000.000.000-02'),
('00.000.000-0', '000.000.000-03'),
('00.000.000-0', '000.000.000-04'),
('00.000.000-0', '000.000.000-05');



--
-- Extraindo dados da tabela `Employee`
--

INSERT INTO `Employee` (`loginEmployee`, `passwordEmployee`, `EmployeeType_idEmployeeType`, `LegalPerson_Person_idPerson`) VALUES
('func01', '123456', 2, '000.000.000-03'),
('func02', '123456', 2, '000.000.000-04'),
('func03', '123456', 2, '000.000.000-05');

--
-- Extraindo dados da tabela `JuridicalPerson`
--

INSERT INTO `JuridicalPerson` (`Person_idPerson`) VALUES
('0000-00'),
('0000-01'),
('0000-02'),
('0000-03'),
('0000-04'),
('0000-05');

--
-- Extraindo dados da tabela `Supplier`
--

INSERT INTO `Supplier` (`JuridicalPerson_Person_idPerson`) VALUES
('0000-00'),
('0000-01'),
('0000-02');

--
-- Extraindo dados da tabela `Brand_has_Supplier`
--

INSERT INTO `Brand_has_Supplier` (`Brand_nameBrand`, `Supplier_JuridicalPerson_Person_idPerson`) VALUES
('AMD', '0000-01'),
('AMD', '0000-02'),
('Intel', '0000-02'),
('NVIDIA', '0000-02');

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
